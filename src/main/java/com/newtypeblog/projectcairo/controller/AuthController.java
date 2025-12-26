package com.newtypeblog.projectcairo.controller;

import com.newtypeblog.projectcairo.domain.User;
import com.newtypeblog.projectcairo.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 로그인 처리
     */
    @PostMapping("/login")
    public String login(
            @RequestParam String userId,
            @RequestParam String userPw,
            HttpSession session
    ) {

        User user = authService.login(userId, userPw);

        if (user == null) {
            // 로그인 실패
            return "redirect:/login?error";
        }

        // 로그인 성공 → 세션 저장
        session.setAttribute("LOGIN_USER", user);

        return "redirect:/";
    }
}
