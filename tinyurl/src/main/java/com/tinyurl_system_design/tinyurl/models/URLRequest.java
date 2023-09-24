package com.tinyurl_system_design.tinyurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class URLRequest {
    private String originalUrl;

    @JsonCreator
    public URLRequest(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
