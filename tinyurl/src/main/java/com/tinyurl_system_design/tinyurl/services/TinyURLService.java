package com.tinyurl_system_design.tinyurl.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinyurl_system_design.tinyurl.models.URL;


public interface TinyURLService {
    URL createShortUrl(String originalUrl, String userId);

    URL getOriginalUrl(String shortUrl) throws JsonProcessingException;
}
