package com.tinyurl_system_design.key_generator_service.daemons;

import com.tinyurl_system_design.key_generator_service.models.GeneratedKey;
import com.tinyurl_system_design.key_generator_service.services.GenerateKeyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableAsync
@Component
public class GenerateKeyDaemon {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    GenerateKeyServiceImpl generateKeyService;

    @Async
    @Scheduled(fixedRate = 1000)
    // generate a key every second
    public void scheduleGenerateKey() {
        GeneratedKey generatedKey = this.generateKeyService.generateKey();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MM:SS");
//        logger.info("generated key: " + generatedKey.getKey() + " " + LocalDateTime.now().format(dateTimeFormatter));
    }
}
