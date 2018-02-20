package com.banana.service;

import com.banana.model.Token;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public Token generateToken(String userId) {
        Token token = new Token();
        token.setUserId(userId);
        token.setToken(RandomStringUtils.randomAlphabetic(4));
        return token;
    }
}
