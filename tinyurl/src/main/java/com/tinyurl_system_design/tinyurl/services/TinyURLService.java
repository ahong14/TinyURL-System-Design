package com.tinyurl_system_design.tinyurl.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinyurl_system_design.tinyurl.models.TinyURL;


public interface TinyURLService {
    TinyURL createShortUrl(String originalUrl, String userId);

    TinyURL getOriginalUrl(String shortUrl) throws JsonProcessingException;

    String shortenUrl(String originalUrl);
}
