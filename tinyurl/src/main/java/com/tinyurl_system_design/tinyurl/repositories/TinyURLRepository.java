package com.tinyurl_system_design.tinyurl.repositories;

import com.tinyurl_system_design.tinyurl.models.TinyURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TinyURLRepository extends JpaRepository<TinyURL, UUID> {
    TinyURL findByShortUrl(String shortUrl);
    TinyURL findByOriginalUrlAndId(String originalUrl, UUID userId);
}
