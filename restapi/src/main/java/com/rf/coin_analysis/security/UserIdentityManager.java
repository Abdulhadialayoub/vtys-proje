package com.rf.coin_analysis.security;

import com.rf.coin_analysis.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Configuration
public class UserIdentityManager {
    public Long getAuthenticationId(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user= (User) authentication.getPrincipal();
        return user.getId();
    }
    public boolean isAuthorized(Long authenticateId,Long requestId){
    return authenticateId.equals(requestId);
    }
}
