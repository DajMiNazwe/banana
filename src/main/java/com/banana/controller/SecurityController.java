package com.banana.controller;

import com.banana.model.Token;
import com.banana.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.banana.mapping.SecurityUrlMapping.TOKEN_USER;

@RestController
public class SecurityController {

    private final TokenService tokenService;

    @Autowired
    public SecurityController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(TOKEN_USER)
    public Token getToken(@PathVariable String userId) {
        return tokenService.generateToken(userId);
    }
}
