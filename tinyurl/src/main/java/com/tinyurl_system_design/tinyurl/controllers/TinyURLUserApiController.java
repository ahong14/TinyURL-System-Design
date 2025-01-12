package com.tinyurl_system_design.tinyurl.controllers;

import com.tinyurl_system_design.tinyurl.models.TinyURLUser;
import com.tinyurl_system_design.tinyurl.models.UserRequest;
import com.tinyurl_system_design.tinyurl.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/user")
public class TinyURLUserApiController {
    final private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserServiceImpl userService;

    @Autowired
    TinyURLUserApiController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     *
     * @param userRequest
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TinyURLUser> createUser(@RequestBody UserRequest userRequest) {
        logger.info("create user request: " + userRequest.toString());
        TinyURLUser createdUser = this.userService.createUser(userRequest.getName(), userRequest.getEmail());
        return ResponseEntity.ok(createdUser);
    }

    /**
     *
     * @param userId
     * @return
     */
    @GetMapping(path = "/{userId}")
    public ResponseEntity<TinyURLUser> getUser(@PathVariable String userId) {
        TinyURLUser foundUser = this.userService.getUser(userId);
        return ResponseEntity.ok(foundUser);
    }

}
