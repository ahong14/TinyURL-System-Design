package com.tinyurl_system_design.tinyurl.repositories;

import com.tinyurl_system_design.tinyurl.models.URL;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepository extends MongoRepository<URL, String> {
    // method to find document by short url
    URL findByShortUrl(String shortUrl);
}
