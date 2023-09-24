package com.tinyurl_system_design.tinyurl.controllers;

import com.tinyurl_system_design.tinyurl.models.URL;
import com.tinyurl_system_design.tinyurl.models.URLRequest;
import com.tinyurl_system_design.tinyurl.services.TinyURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;


/**
 * Controller that handles creating/modifying short urls from original urls
 */
@RestController
@RequestMapping(path = "/api/url")
public class TinyURLApiController {
    @Autowired
    private TinyURLService tinyURLService;

    // POST route to create short urls from original urls
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createShortUrl(@RequestBody URLRequest urlRequest) throws NoSuchAlgorithmException {
        URL createdUrl = this.tinyURLService.createShortUrl(urlRequest.getOriginalUrl());
        return ResponseEntity.ok(createdUrl);
    }
}
