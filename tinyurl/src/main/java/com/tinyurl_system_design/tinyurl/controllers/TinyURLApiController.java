package com.tinyurl_system_design.tinyurl.controllers;

import com.tinyurl_system_design.tinyurl.models.TinyURL;
import com.tinyurl_system_design.tinyurl.models.URLRequest;
import com.tinyurl_system_design.tinyurl.services.TinyURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles creating/modifying short urls from original urls
 */
@RestController
@RequestMapping(path = "/api/url")
public class TinyURLApiController {
    private final TinyURLService tinyURLService;

    @Autowired
    TinyURLApiController(TinyURLService tinyURLService) {
        this.tinyURLService = tinyURLService;
    }

    /**
     * POST route to create short urls from original urls
     * @param urlRequest - contains request params needed to create shortened url, original url and user id
     * @return - return created URL object
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TinyURL> createShortUrl(@RequestBody URLRequest urlRequest) {
        TinyURL createdUrl = this.tinyURLService.createShortUrl(urlRequest.getOriginalUrl(), urlRequest.getUserId());
        return ResponseEntity.ok(createdUrl);
    }
}
