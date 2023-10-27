package com.tinyurl_system_design.key_generator_service.repositories;

import com.tinyurl_system_design.key_generator_service.models.ConsumedGeneratedKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumedKeyRepo extends JpaRepository<ConsumedGeneratedKey, Long> {
}
