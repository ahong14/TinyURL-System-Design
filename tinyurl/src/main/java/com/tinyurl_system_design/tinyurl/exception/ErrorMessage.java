package com.tinyurl_system_design.tinyurl.exception;

import java.util.Date;

// reference: https://github.com/bezkoder/spring-boot-restcontrolleradvice/blob/master/src/main/java/com/bezkoder/spring/rest/exhandling/exception/ErrorMessage.java
// reference: https://www.bezkoder.com/spring-boot-restcontrolleradvice/
public class ErrorMessage {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}