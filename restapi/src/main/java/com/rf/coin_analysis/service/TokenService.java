package com.rf.coin_analysis.service;

import com.rf.coin_analysis.exception.TokenException;
import com.rf.coin_analysis.model.Token;
import com.rf.coin_analysis.model.User;
import com.rf.coin_analysis.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository repository;
    public Token createToken(User user){
        String token= UUID.randomUUID().toString();
        Token db=Token.builder().token(token).user(user).build();
        repository.save(db);
        return db;
    }
    public User verifyToken(String token){
      Token db=getToken(token);
      return db.getUser();
    }
    public void logout(String token){
        Token db=getToken(token);
        repository.delete(db);
    }
    public Token getToken(String token){
        return repository.findById(token).orElseThrow(()->new TokenException());
    }
}
