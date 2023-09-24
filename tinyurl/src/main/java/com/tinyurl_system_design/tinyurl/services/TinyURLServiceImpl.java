package com.tinyurl_system_design.tinyurl.services;

import com.tinyurl_system_design.tinyurl.models.URL;
import com.tinyurl_system_design.tinyurl.repositories.URLRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;

@Service
public class TinyURLServiceImpl implements TinyURLService {

    @Autowired
    private URLRepository urlRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param originalUrl
     * @return
     */
    @Override
    public URL createShortUrl(String originalUrl) {
        // TODO determine of originalUrl already has mapping
        LocalDate currentDate = LocalDate.now();
        String shortUrl = Base64.getEncoder().encodeToString(originalUrl.getBytes()).substring(0, 7);
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
    public URL getOriginalUrl(String shortUrl) {
        URL originalUrl = this.urlRepository.findByShortUrl(shortUrl);
        return originalUrl;
    }
}
