package com.tinyurl_system_design.key_generator_service.services;

import com.tinyurl_system_design.key_generator_service.models.ConsumedGeneratedKey;
import com.tinyurl_system_design.key_generator_service.models.GeneratedKey;

public interface GenerateKeyService {
    GeneratedKey generateKey();

    ConsumedGeneratedKey getGeneratedKey();
}
