package com.banana.service;

import com.banana.ApplicationSettings;
import com.banana.model.TokenEntity;
import com.banana.model.TokenResponse;
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

    public TokenResponse generateToken(String userId) {
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(RandomStringUtils.randomAlphabetic(settings.getOneTimeTokenLength()).toUpperCase());
        tokenRepository.save(tokenEntity);
        return new TokenResponse(tokenEntity.getToken());
    }

    boolean isTokenValid(String token, String id) {
        Optional<TokenEntity> optionalToken = tokenRepository.findOneByToken(token);
        TokenEntity tokenEntityFromMemory;
        if (optionalToken.isPresent()) {
            tokenEntityFromMemory = optionalToken.get();
        } else {
            throw new InvalidTokenException(token);
        }
        return tokenEntityFromMemory.getUserId().equals(id) && isTimeValid(tokenEntityFromMemory.getCreationTime());
    }

    private boolean isTimeValid(Instant tokenTime) {
        Duration duration = Duration.between(tokenTime, Instant.now());
        return duration.getSeconds() <= settings.getOneTimeTokenValiditySeconds();
    }
}
