package com.tinyurl_system_design.tinyurl.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.tinyurl_system_design.tinyurl.models.TinyURL;
import com.tinyurl_system_design.tinyurl.repositories.TinyURLRepository;
import com.tinyurl_system_design.tinyurl.utils.Base62;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TinyURLServiceImpl implements TinyURLService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final TinyURLRepository urlRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final String GLOBAL_COUNTER_KEY = "url_counter";

    public TinyURLServiceImpl(TinyURLRepository urlRepository, RedisTemplate redisTemplate) {
        this.urlRepository = urlRepository;
        this.redisTemplate = redisTemplate;
    }

    /**
     *
     * @param originalUrl
     * @return
     */
    @Override
    public String shortenUrl(String originalUrl) {
        // get counter value from global redis key
        String globalCounter = this.redisTemplate.opsForValue().get(GLOBAL_COUNTER_KEY);
        if (globalCounter == null) {
            throw new RuntimeException("Redis counter returned invalid value for key");
        }
        int globalCounterConverted = Integer.parseInt(globalCounter);

        return Base62.encode(globalCounterConverted);
    }

    /**
     *
     * @param originalUrl - original TinyURL being shortened
     * @return - TinyURL object created
     */
    @Override
    public TinyURL createShortUrl(String originalUrl, String userId) {
        // determine if originalUrl already has mapping for user
        TinyURL existingOriginalUrl = this.urlRepository.findByOriginalUrlAndId(originalUrl, UUID.fromString(userId));
        if (existingOriginalUrl != null) {
            String errorMessage = "short url mapping found for original url: " + originalUrl;
            throw new IllegalArgumentException(errorMessage);
        }

        // set creation date
        // expiration date 1 week ahead
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime expirationDate = currentDate.plusWeeks(1);

        // get base 62 encoding from redis counter, short url value
        String shortUrl = this.shortenUrl(originalUrl);
        logger.info("original url: " + originalUrl);
        logger.info("short url: " + shortUrl);

        // get host url
        String hostUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        logger.info("host url: " + hostUrl);

        // add host url and base62 short url to create full URL
        String completeShortUrl = hostUrl + "/" + shortUrl;
        TinyURL createUrl = new TinyURL(originalUrl, shortUrl, currentDate, expirationDate);
        createUrl.setCompleteShortUrl(completeShortUrl);
        createUrl.setUserId(userId);
        this.urlRepository.save(createUrl);
        this.redisTemplate.opsForValue().increment(GLOBAL_COUNTER_KEY);
        return createUrl;
    }

    /**
     *
     * @param shortUrl
     * @return
     */
    @Override
    public TinyURL getOriginalUrl(String shortUrl) throws JsonProcessingException {
        String originalUrlCacheObject = this.redisTemplate.opsForValue().get(shortUrl);

        // if found from cache
        if (originalUrlCacheObject != null) {
            logger.info("cache hit for short url: " + shortUrl);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            TinyURL originalUrlFromCache = mapper.readValue(originalUrlCacheObject, TinyURL.class);
            return originalUrlFromCache;
        }

        // cache miss
        logger.info("cache miss for short url: " + shortUrl);
        TinyURL originalUrl = this.urlRepository.findByShortUrl(shortUrl);
        if (originalUrl == null) {
            String errorMessage = "original url not found for short url: " + shortUrl;
            logger.error(errorMessage);
            throw new NoSuchElementException(errorMessage);
        }
        // update cache key to 1 hour
        ObjectWriter ow = new ObjectMapper().registerModule(new JavaTimeModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME))).writer().withDefaultPrettyPrinter();
        String originalUrlJson = ow.writeValueAsString(originalUrl);
        redisTemplate.opsForValue().set(shortUrl, originalUrlJson, Duration.ofHours(1));
        return originalUrl;
    }
}
