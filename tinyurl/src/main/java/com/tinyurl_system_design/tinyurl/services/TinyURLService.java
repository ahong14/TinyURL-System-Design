package com.tinyurl_system_design.tinyurl.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinyurl_system_design.tinyurl.models.URL;

import java.security.NoSuchAlgorithmException;

public interface TinyURLService {
    URL createShortUrl(String originalUrl) throws NoSuchAlgorithmException;

    URL getOriginalUrl(String shortUrl) throws JsonProcessingException;
}
