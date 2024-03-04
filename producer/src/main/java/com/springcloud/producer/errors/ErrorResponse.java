package com.springcloud.producer.errors;

import java.util.List;

public class ErrorResponse {
    private List<String> errorMessages;

    public ErrorResponse(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    // Getter for errorMessages
}
