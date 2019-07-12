package com.subject.sell_cake.auth.service;

import com.subject.sell_cake.auth.AuthUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserAuthService extends UserDetailsService {
    AuthUser loadUserByAccessToken(String token);
}
