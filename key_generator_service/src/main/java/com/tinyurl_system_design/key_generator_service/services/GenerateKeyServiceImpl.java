package com.tinyurl_system_design.key_generator_service.services;

import com.tinyurl_system_design.key_generator_service.models.ConsumedGeneratedKey;
import com.tinyurl_system_design.key_generator_service.models.GeneratedKey;
import com.tinyurl_system_design.key_generator_service.repositories.ConsumedKeyRepo;
import com.tinyurl_system_design.key_generator_service.repositories.GenerateKeyRepo;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

@Service
public class GenerateKeyServiceImpl implements GenerateKeyService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GenerateKeyRepo generateKeyRepo;

    @Autowired
    private ConsumedKeyRepo consumedKeyRepo;

    @Override
    public GeneratedKey generateKey() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        String randomKeyString = generator.generate(7);
        // continue generating random key string until not found
        while (generateKeyRepo.findGeneratedKeyByKey(randomKeyString) != null) {
            randomKeyString = generator.generate(7);
        }
        LocalDateTime currentDate = LocalDateTime.now();

        GeneratedKey generatedKey = new GeneratedKey(randomKeyString, currentDate, false);
        return generateKeyRepo.save(generatedKey);
    }

    @Override
    public ConsumedGeneratedKey getGeneratedKey() {
        GeneratedKey generatedKey = this.generateKeyRepo.findFirstByConsumedIsFalse();
        while (generatedKey == null) {
            generatedKey = this.generateKeyRepo.findFirstByConsumedIsFalse();
        }
        LocalDateTime currentDate = LocalDateTime.now();
        // move record to consumed table
        ConsumedGeneratedKey consumedGeneratedKey = new ConsumedGeneratedKey(generatedKey.getKey(), currentDate);
        this.consumedKeyRepo.save(consumedGeneratedKey);

        // delete from generated table
        this.generateKeyRepo.delete(generatedKey);
        return consumedGeneratedKey;
    }
}
