package com.tinyurl_system_design.tinyurl.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinyurl_system_design.tinyurl.models.URL;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

public interface TinyURLService {
    URL createShortUrl(String originalUrl) throws UnknownHostException;

    URL getOriginalUrl(String shortUrl) throws JsonProcessingException;
}
