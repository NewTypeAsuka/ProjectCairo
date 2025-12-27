package com.newtypeblog.projectcairo.service;

import com.newtypeblog.projectcairo.domain.User;
import com.newtypeblog.projectcairo.repository.UserRepository;
import com.newtypeblog.projectcairo.util.PasswordUtil;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordChecker passwordChecker;

    public AuthService(UserRepository userRepository, PasswordChecker passwordChecker) {
        this.userRepository = userRepository;
        this.passwordChecker = passwordChecker;
    }

    public boolean login(String userId, String userPw, HttpSession session) {
        var userOpt = userRepository.findByUserId(userId);
        if (userOpt.isEmpty()) return false;

        var user = userOpt.get();

        // 1) 사용 상태 체크
        if (!"Y".equals(user.getUserStatus())) return false;

        // 2) “블로그 주인장만” 조건 (예: grade 0이 관리자라고 가정)
        // 너 DB에서 grade 정의를 어떻게 했는지에 따라 여기만 바꾸면 됨
        if (user.getUserGrade() == null || user.getUserGrade() != 0) return false;

        // 3) 비밀번호 체크 (평문 or BCrypt)
        if (!passwordChecker.matches(userPw, user.getUserPw())) return false;

        // 4) 성공: 세션에 로그인 흔적 남기기
        session.setAttribute("LOGIN_USER_NO", user.getUserNo());
        session.setAttribute("LOGIN_USER_ID", user.getUserId());
        session.setAttribute("LOGIN_USER_GRADE", user.getUserGrade());
        return true;
    }
}
