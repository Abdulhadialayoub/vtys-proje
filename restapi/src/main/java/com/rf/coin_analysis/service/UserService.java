package com.rf.coin_analysis.service;

import com.rf.coin_analysis.dto.ApiResponse;
import com.rf.coin_analysis.dto.UserRegisterRequest;
import com.rf.coin_analysis.dto.converter.DtoConverter;
import com.rf.coin_analysis.exception.NoAuthorityException;
import com.rf.coin_analysis.exception.NotFoundException;
import com.rf.coin_analysis.model.User;
import com.rf.coin_analysis.repository.UserRepository;
import com.rf.coin_analysis.security.UserIdentityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final DtoConverter converter;
    private final PasswordEncoder encoder;
    private final UserIdentityManager manager;

    public ApiResponse<Void> registerUser(UserRegisterRequest request){
        User user= User.builder().password(encoder.encode(request.getPassword())).email(request.getEmail()).name(request.getName()).build();
        repository.save(user);

        return ApiResponse.ok("Kayıt Başarılı");
    }

    public ApiResponse<Void> delete(Long id) {
        User user=findById(id);
        if(!manager.isAuthorized(manager.getAuthenticationId(),id)) throw new NoAuthorityException();
        repository.delete(user);
        return ApiResponse.ok("Kullanıcı silindi");
    }
    protected User findById(Long id){
        return repository.findById(id).orElseThrow(()->new NotFoundException("Kullanıcı"));
    }
    protected User findByEmail(String email){
        return repository.findByEmail(email).orElseThrow(()->new NotFoundException("Kullanici"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmail(email);
    }
}
