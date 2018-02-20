package com.banana.shared;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String invalidTokenException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String accountNotFoundException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldError().getField() + " " + e.getBindingResult().getFieldError().getDefaultMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String genericErrorHandler(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }
}
