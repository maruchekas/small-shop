package com.maruchek.smallshop.api.response;

import com.maruchek.smallshop.model.ErrorDescription;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class ValidationErrorResponse
        extends ErrorResponse {
    private final List<ErrorDescription> errors;

    public ValidationErrorResponse(String message, List<ErrorDescription> errors) {
        super(message);
        this.errors = errors;
    }
}