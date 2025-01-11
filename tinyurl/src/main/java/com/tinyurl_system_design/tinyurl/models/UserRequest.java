package com.tinyurl_system_design.tinyurl.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UserRequest {
    private String name;

    private String email;

    @JsonCreator
    public UserRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
