package com.rf.coin_analysis.controller;

import com.rf.coin_analysis.config.ApiPaths;
import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.UserRegisterRequest;
import com.rf.coin_analysis.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.USER)
@CrossOrigin
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // kullanıcı kayıt
    @PostMapping(ApiPaths.REGISTER)
    ResponseEntity<ApiResponse<Void>> registerUser(@Valid @RequestBody UserRegisterRequest request){
        return ResponseEntity.ok(service.registerUser(request));
    }
    // kullanıcı sil
    @DeleteMapping(ApiPaths.DELETE)
    ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
    // kullanıcı güncelle
}
