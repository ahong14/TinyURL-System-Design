package com.tinyurl_system_design.tinyurl.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
public class URL {
    @Id
    private String id;

    private String originalUrl;

    private String shortUrl;

    private String completeShortUrl;

    private String userId;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    public URL(String id, String originalUrl, String shortUrl, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public URL(String originalUrl, String shortUrl, LocalDateTime dateCreated, LocalDateTime dateUpdated) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public URL() {};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getCompleteShortUrl() {
        return completeShortUrl;
    }

    public void setCompleteShortUrl(String completeShortUrl) {
        this.completeShortUrl = completeShortUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "URL{" +
                "id='" + id + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", completeShortUrl='" + completeShortUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
