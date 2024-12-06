package com.rf.coin_analysis.service;

import com.rf.coin_analysis.dto.AuthDto;
import com.rf.coin_analysis.dto.LoginRequest;
import com.rf.coin_analysis.dto.UserDto;
import com.rf.coin_analysis.dto.converter.DtoConverter;
import com.rf.coin_analysis.exception.AuthException;
import com.rf.coin_analysis.model.Token;
import com.rf.coin_analysis.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {
    @Mock
    private TokenService tokenService;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private UserService userService;
    @Mock
    private DtoConverter converter;
    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ShouldReturnAuthDto_WhenLoginIsSuccessful() {
        // arrange
        LoginRequest request = LoginRequest.builder().email("user@example.com").password("Password_4548").build();
        User mockUser = User.builder().id(1L).email("user@example.com").password("Password_4548").build();
        Token mockToken = Token.builder().token("mocktoken").user(mockUser).build();
        AuthDto mockDto = AuthDto.builder().user(UserDto.builder().email("user@example.com").build()).token("mocktoken").build();
        Mockito.when(userService.findByEmail(request.getEmail())).thenReturn(mockUser);
        Mockito.when(tokenService.createToken(mockUser)).thenReturn(mockToken);
        Mockito.when(encoder.matches(request.getPassword(), mockUser.getPassword())).thenReturn(true);
        Mockito.when(converter.convertUser(mockUser)).thenReturn(UserDto.builder().email("user@example.com").build());
        // act
        AuthDto result = authService.login(request);
        //assert
        assertNotNull(result);
        assertEquals(mockDto.getToken(), result.getToken());
        assertEquals(mockDto.getUser(), result.getUser());
        Mockito.verify(userService, Mockito.times(1)).findByEmail(request.getEmail());
        Mockito.verify(encoder,Mockito.times(1)).matches(request.getPassword(),mockUser.getPassword());
        Mockito.verify(tokenService, Mockito.times(1)).createToken(mockUser);
        Mockito.verify(converter, Mockito.times(1)).convertUser(mockUser);
    }
    @Test
    void login_ShouldThrowAuthException_WhenPasswordIsInvalid(){
        // arrange
        LoginRequest request = LoginRequest.builder().email("user@example.com").password("Password_4548").build();
        User mockUser = User.builder().id(1L).email("user@example.com").password("Password_4548").build();
        Mockito.when(userService.findByEmail(request.getEmail())).thenReturn(mockUser);
        Mockito.when(encoder.matches(request.getPassword(),mockUser.getPassword())).thenReturn(false);


        //assert
        assertThrows(AuthException.class,()->authService.login(request));
        Mockito.verify(userService,Mockito.times(1)).findByEmail(request.getEmail());
        Mockito.verify(encoder,Mockito.times(1)).matches(request.getPassword(),mockUser.getPassword());
        Mockito.verifyNoInteractions(converter,tokenService);

    }
    @Test
    void logout(){
        // arrange
        String mockToken="mocktoken";
        // act
        authService.logout(mockToken);
        // assert
        Mockito.verify(tokenService,Mockito.times(1)).logout(mockToken);
    }



}