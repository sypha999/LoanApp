package com.loans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionhandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(CustomException.class)
        public ResponseEntity<ErrorResponse> handlerForFailedMailException(final CustomException ex) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

}}
