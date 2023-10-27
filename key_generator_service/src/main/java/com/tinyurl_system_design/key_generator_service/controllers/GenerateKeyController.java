package com.tinyurl_system_design.key_generator_service.controllers;

import com.tinyurl_system_design.key_generator_service.models.ConsumedGeneratedKey;
import com.tinyurl_system_design.key_generator_service.services.GenerateKeyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/keyService")
public class GenerateKeyController {
    @Autowired
    private GenerateKeyServiceImpl generateKeyService;

    @GetMapping
    public ResponseEntity getGeneratedKeyString() {
        ConsumedGeneratedKey generatedKey = this.generateKeyService.getGeneratedKey();
        return ResponseEntity.ok(generatedKey);
    }
}
