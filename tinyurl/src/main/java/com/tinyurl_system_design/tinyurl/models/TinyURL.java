package com.tinyurl_system_design.tinyurl.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TinyURL {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String originalUrl;

    private String shortUrl;

    private String completeShortUrl;

    private String userId;

    private LocalDateTime dateCreated;

    private LocalDateTime dateUpdated;

    private LocalDateTime expirationTime;

    public TinyURL() {

    }

    public TinyURL(UUID id, String originalUrl, String shortUrl, String completeShortUrl, String userId, LocalDateTime dateCreated, LocalDateTime dateUpdated, LocalDateTime expirationTime) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.completeShortUrl = completeShortUrl;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.expirationTime = expirationTime;
    }

    public TinyURL(String originalUrl, String shortUrl, LocalDateTime currentDate, LocalDateTime currentDate1) {
    }

    public TinyURL(String originalUrl, String shortUrl, LocalDateTime currentDate, LocalDateTime currentDate1, LocalDateTime expirationDate) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "TinyURL{" +
                "id=" + id +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", completeShortUrl='" + completeShortUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", expirationTime=" + expirationTime +
                '}';
    }
}
