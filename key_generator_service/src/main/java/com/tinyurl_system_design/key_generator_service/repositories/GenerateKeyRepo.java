package com.tinyurl_system_design.key_generator_service.repositories;

import com.tinyurl_system_design.key_generator_service.models.GeneratedKey;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenerateKeyRepo extends JpaRepository<GeneratedKey, Integer> {
    GeneratedKey findGeneratedKeyByKey(String key);

    GeneratedKey findFirstByConsumedIsFalse();
}
