package com.tinyurl_system_design.tinyurl.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tinyurl_system_design.tinyurl.models.URL;
import com.tinyurl_system_design.tinyurl.repositories.URLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Base64;
import java.util.NoSuchElementException;

@Service
public class TinyURLServiceImpl implements TinyURLService {

    @Autowired
    private URLRepository urlRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Method to create short url mapping
     * @param originalUrl
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String shortenOriginalUrl(String originalUrl) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(originalUrl.getBytes());
        byte[] digest = messageDigest.digest();
        String hash = Base64.getEncoder().encodeToString(digest);
        logger.info("hash: " + hash);
        return hash.substring(0, 7);
    }

    /**
     *
     * @param originalUrl
     * @return
     */
    @Override
    public URL createShortUrl(String originalUrl) throws NoSuchAlgorithmException {
        // determine if originalUrl already has mapping
        URL existingOriginalUrl = this.urlRepository.findByOriginalUrl(originalUrl);
        if (existingOriginalUrl != null) {
            String errorMessage = "short url mapping found for original url: " + originalUrl;
            throw new IllegalArgumentException(errorMessage);
        }
        LocalDate currentDate = LocalDate.now();
        String shortUrl = this.shortenOriginalUrl(originalUrl);
        logger.info("original url: " + originalUrl);
        logger.info("short url base64 encoded: " + shortUrl);
        URL createUrl = new URL(originalUrl, shortUrl, currentDate, currentDate);
        return this.urlRepository.save(createUrl);
    }

    /**
     *
     * @param shortUrl
     * @return
     */
    @Override
    public URL getOriginalUrl(String shortUrl) throws JsonProcessingException {
        String originalUrlCacheObject = this.redisTemplate.opsForValue().get(shortUrl);

        // if found from cache
        if (originalUrlCacheObject != null) {
            logger.info("cache hit for short url: " + shortUrl);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            URL originalUrlFromCache = mapper.readValue(originalUrlCacheObject, URL.class);
            return originalUrlFromCache;
        }

        // cache miss
        logger.info("cache miss for short url: " + shortUrl);
        URL originalUrl = this.urlRepository.findByShortUrl(shortUrl);
        if (originalUrl == null) {
            String errorMessage = "original url not found for short url: " + shortUrl;
            logger.error(errorMessage);
            throw new NoSuchElementException(errorMessage);
        }
        // update cache key to 1 hour
        ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule()).writer().withDefaultPrettyPrinter();
        String originalUrlJson = ow.writeValueAsString(originalUrl);
        redisTemplate.opsForValue().set(shortUrl, originalUrlJson, Duration.ofHours(1));
        return originalUrl;
    }
}
