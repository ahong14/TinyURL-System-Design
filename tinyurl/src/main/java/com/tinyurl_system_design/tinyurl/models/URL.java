package com.tinyurl_system_design.tinyurl.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "urls")
public class URL {
    @Id
    private String id;

    private String originalUrl;

    private String shortUrl;

    private LocalDate dateCreated;

    private LocalDate dateUpdated;

    public URL(String id, String originalUrl, String shortUrl, LocalDate dateCreated, LocalDate dateUpdated) {
        this.id = id;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public URL(String originalUrl, String shortUrl, LocalDate dateCreated, LocalDate dateUpdated) {
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "URL{" +
                "id='" + id + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
