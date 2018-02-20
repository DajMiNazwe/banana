package com.banana.shared;

import java.util.NoSuchElementException;

public class AccountNotFoundException extends NoSuchElementException {

    public AccountNotFoundException(String id) {
        super("User " + id + " does not exists.");
    }
}
