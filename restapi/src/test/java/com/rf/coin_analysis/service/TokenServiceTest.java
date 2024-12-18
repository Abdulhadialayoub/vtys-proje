package com.rf.coin_analysis.service;

import com.rf.coin_analysis.exception.TokenException;
import com.rf.coin_analysis.model.Token;
import com.rf.coin_analysis.model.User;
import com.rf.coin_analysis.repository.TokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private TokenRepository tokenRepository;

    @Mock
    private User mockUser;

    @Mock
    private Token mockToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createToken_ShouldReturnToken() {
        // Arrange
        when(mockUser.getId()).thenReturn(1L);  // mock bir User objesi döndürdük

        // Act
        Token token = tokenService.createToken(mockUser);

        // Assert
        assertNotNull(token);
        assertNotNull(token.getToken());
        verify(tokenRepository, times(1)).save(any(Token.class));  // save methodunun çağrıldığını doğrula
    }

    @Test
    void verifyToken_ShouldReturnUser_WhenTokenExists() {
        // Arrange
        String tokenStr = "someToken";
        when(tokenRepository.findById(tokenStr)).thenReturn(Optional.of(mockToken));
        when(mockToken.getUser()).thenReturn(mockUser);

        // Act
        User user = tokenService.verifyToken(tokenStr);

        // Assert
        assertNotNull(user);
        verify(tokenRepository, times(1)).findById(tokenStr);
    }

    @Test
    void verifyToken_ShouldThrowTokenException_WhenTokenNotFound() {
        // Arrange
        String tokenStr = "invalidToken";
        when(tokenRepository.findById(tokenStr)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TokenException.class, () -> tokenService.verifyToken(tokenStr));
        verify(tokenRepository, times(1)).findById(tokenStr);
    }

    @Test
    void logout_ShouldDeleteToken_WhenTokenExists() {
        // Arrange
        String tokenStr = "someToken";
        when(tokenRepository.findById(tokenStr)).thenReturn(Optional.of(mockToken));

        // Act
        tokenService.logout(tokenStr);

        // Assert
        verify(tokenRepository, times(1)).delete(mockToken);
    }

    @Test
    void logout_ShouldThrowTokenException_WhenTokenNotFound() {
        // Arrange
        String tokenStr = "invalidToken";
        when(tokenRepository.findById(tokenStr)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TokenException.class, () -> tokenService.logout(tokenStr));
        verify(tokenRepository, times(0)).delete(any(Token.class));  // delete methodunun çağrılmadığını doğrula
    }
}
