package com.banana.controller;

import com.banana.model.Token;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @PostMapping
    public Token getToken() {
//        return tokenService.generateToken();
        return new Token();
    }
}
