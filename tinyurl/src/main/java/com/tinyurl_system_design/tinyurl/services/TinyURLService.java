package com.tinyurl_system_design.tinyurl.services;

import com.tinyurl_system_design.tinyurl.models.URL;

public interface TinyURLService {
    URL createShortUrl(String originalUrl);

    URL getOriginalUrl(String shortUrl);
}
