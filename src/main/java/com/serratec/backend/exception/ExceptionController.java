package com.serratec.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionController {

    @ExceptionHandler(value
            = {SkillException.class})
    protected ResponseEntity<Object> tokenException(SkillException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(e.getMessage());
        apiError.setDebugMessage(e.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value
            = {UserException.class})
    protected ResponseEntity<Object> tokenException(UserException e) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(e.getMessage());
        apiError.setDebugMessage(e.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
