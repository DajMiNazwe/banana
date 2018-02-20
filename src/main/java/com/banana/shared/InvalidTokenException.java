package com.banana.shared;

import java.security.InvalidParameterException;

public class InvalidTokenException extends InvalidParameterException {

    public InvalidTokenException(String token) {
        super(token + " is invalid.");
    }
}
