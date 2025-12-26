package com.newtypeblog.projectcairo.service;

import com.newtypeblog.projectcairo.domain.User;
import com.newtypeblog.projectcairo.repository.UserRepository;
import com.newtypeblog.projectcairo.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String userId, String plainPassword) {

        User user = userRepository.findByUserId(userId);

        // 비밀번호 검증
        boolean matches = PasswordUtil.matches(plainPassword, user.getUserPw());

        if (!matches) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
