package com.tinyurl_system_design.tinyurl.services;

import com.tinyurl_system_design.tinyurl.models.TinyURLUser;

import java.util.NoSuchElementException;

public interface UserService {

    TinyURLUser createUser(String name, String email);

    TinyURLUser getUser(String id) throws NoSuchElementException;
}
