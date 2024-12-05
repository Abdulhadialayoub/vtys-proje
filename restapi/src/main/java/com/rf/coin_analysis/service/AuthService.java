package com.rf.coin_analysis.service;

import com.rf.coin_analysis.dto.AuthDto;
import com.rf.coin_analysis.dto.LoginRequest;
import com.rf.coin_analysis.dto.converter.DtoConverter;
import com.rf.coin_analysis.exception.AuthException;
import com.rf.coin_analysis.model.Token;
import com.rf.coin_analysis.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenService tokenService;
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final DtoConverter converter;
    public AuthDto login(LoginRequest request){
        User user=userService.findByEmail(request.getEmail());
        if(!encoder.matches(request.getPassword(),user.getPassword())) throw new AuthException();
        Token token=tokenService.createToken(user);
        AuthDto dto=AuthDto.builder().token(token.getToken()).user(converter.convertUser(user)).build();
        return dto;
    }

    public void logout(String token) {
        tokenService.logout(token);
    }
}
