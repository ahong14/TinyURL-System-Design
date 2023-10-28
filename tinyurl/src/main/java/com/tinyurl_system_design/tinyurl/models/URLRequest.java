package com.tinyurl_system_design.tinyurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class URLRequest {
    private String originalUrl;

    private String userId;

    @JsonCreator
    public URLRequest(String originalUrl, String userId) {
        this.originalUrl = originalUrl;
        this.userId = userId;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "URLRequest{" +
                "originalUrl='" + originalUrl + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
