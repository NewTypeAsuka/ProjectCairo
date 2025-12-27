package com.newtypeblog.projectcairo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * BCrypt 기반 비밀번호 검증 구현체
 */
@Component
public class BCryptPasswordChecker implements PasswordChecker {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
