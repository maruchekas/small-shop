package com.maruchek.smallshop.api.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String message;
}