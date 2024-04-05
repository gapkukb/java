package com.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String getUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
