package com.banana.service;

import com.banana.ApplicationSettings;
import com.banana.model.Token;
import com.banana.repository.TokenRepository;
import com.banana.shared.InvalidTokenException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

@Service
public class TokenService {

    private final ApplicationSettings settings;

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(ApplicationSettings settings, TokenRepository tokenRepository) {
        this.settings = settings;
        this.tokenRepository = tokenRepository;
    }

    public Token generateToken(String userId) {
        Token token = new Token();
        token.setUserId(userId);
        token.setToken(RandomStringUtils.randomAlphabetic(settings.getOneTimeTokenLength()).toUpperCase());
        return tokenRepository.save(token);
    }

    boolean isTokenValid(String token, String id) {
        Optional<Token> optionalToken = tokenRepository.findOneByToken(token);
        Token tokenFromMemory;
        if (optionalToken.isPresent()) {
            tokenFromMemory = optionalToken.get();
        } else {
            throw new InvalidTokenException(token);
        }
        return tokenFromMemory.getUserId().equals(id) && isTimeValid(tokenFromMemory.getCreationTime());
    }

    private boolean isTimeValid(Instant tokenTime) {
        Duration duration = Duration.between(tokenTime, Instant.now());
        return duration.getSeconds() <= settings.getOneTimeTokenValiditySeconds();
    }
}
