package com.carlos.invoice.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity IllegalArgumentToBadRequest(HttpServletRequest request, Exception e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationExceptionToBadRequest(HttpServletRequest request, Exception e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
