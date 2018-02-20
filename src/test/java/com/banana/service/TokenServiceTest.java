package com.banana.service;

import com.banana.ApplicationSettings;
import com.banana.model.TokenEntity;
import com.banana.model.TokenResponse;
import com.banana.repository.TokenRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {

    @InjectMocks
    private TokenService sut;

    @Mock
    private ApplicationSettings applicationSettings;

    @Mock
    private TokenRepository tokenRepository;

    @Test
    public void generateTokenShouldGenerateOneTimeToken() throws Exception {
        // given
        String userId = "userId";

        // when
        TokenResponse result = sut.generateToken(userId);

        // then
        assertThat(result.getToken()).isNotNull();
        assertThat(result.getToken().length()).isEqualTo(applicationSettings.getOneTimeTokenLength());
        verify(tokenRepository).save(Mockito.any(TokenEntity.class));
    }

    @Test
    public void isTokenValidShouldReturnTrueWhenTimeRangeAndIdAreCorrect() throws Exception {
        // given
        String token = "token";
        String id = "id";
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setToken(token);
        tokenEntity.setUserId(id);
        when(tokenRepository.findOneByToken(token)).thenReturn(Optional.of(tokenEntity));

        // when
        boolean result = sut.isTokenValid(token, id);

        // then
        assertThat(result).isTrue();
    }
}