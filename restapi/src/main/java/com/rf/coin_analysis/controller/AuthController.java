package com.rf.coin_analysis.controller;

import com.rf.coin_analysis.config.ApiPaths;
import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.AuthDto;
import com.rf.coin_analysis.dto.LoginRequest;
import com.rf.coin_analysis.dto.UserDto;
import com.rf.coin_analysis.service.AuthService;
import jakarta.validation.Valid;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.AUTH)
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    //giriş
    @PostMapping(ApiPaths.LOGIN)
    ResponseEntity<ApiResponse<AuthDto>> login(@Valid @RequestBody LoginRequest request){
        int oneMonth = 30 * 24 * 60 * 60;
        AuthDto dto=service.login(request);
        ResponseCookie cookie=ResponseCookie.from("login-token",dto.getToken()).path("/").maxAge(oneMonth).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(ApiResponse.ok("Giriş Başarili",dto));
    }
    // çıkış yap
    @DeleteMapping(ApiPaths.LOGOUT)
    ResponseEntity<?> logout(@CookieValue(name = "login-token",required = false) String token){
        service.logout(token);
        ResponseCookie cookie=ResponseCookie.from("login-token","").path("/").maxAge(0).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body("Çıkış Yapıldı");

    }


}
