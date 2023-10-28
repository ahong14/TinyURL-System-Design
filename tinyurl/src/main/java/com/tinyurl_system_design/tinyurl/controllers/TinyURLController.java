package com.tinyurl_system_design.tinyurl.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinyurl_system_design.tinyurl.models.URL;
import com.tinyurl_system_design.tinyurl.services.TinyURLServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller that handles short urls
 */
@RestController
public class TinyURLController {
    private TinyURLServiceImpl tinyURLService;

    @Autowired
    TinyURLController(TinyURLServiceImpl tinyURLService) {
        this.tinyURLService = tinyURLService;
    }

    // GET request to redirect to original url from short url
    @GetMapping(path = "/{shortUrl}")
    public ResponseEntity getOriginalUrl(@PathVariable String shortUrl) throws JsonProcessingException {
        // find mapping of short url to original url
        URL originalUrl = this.tinyURLService.getOriginalUrl(shortUrl);

        // add HTTP headers for 302 status code and Location of redirect
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", originalUrl.getOriginalUrl());
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }
}
