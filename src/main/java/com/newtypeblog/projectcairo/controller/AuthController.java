package com.newtypeblog.projectcairo.controller;

import com.newtypeblog.projectcairo.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    // navbar.html에서 쓰는 세션 키가 session.loginUser 이므로 "loginUser"로 맞춘다. :contentReference[oaicite:3]{index=3}
    private static final String SESSION_LOGIN_USER = "loginUser";

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String userId,
            @RequestParam String userPw,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        try {
            boolean ok = authService.login(userId, userPw, session);

            System.out.println("userId = " + userId);
            System.out.println("userPw = " + userPw);

            if (!ok) {
                // 실패 사유를 화면에 띄우고 싶으면 index.html에서 이 flash 값을 꺼내 쓰면 된다.
                redirectAttributes.addFlashAttribute("loginError", "아이디/비밀번호가 올바르지 않습니다.");
                return "redirect:/";
            }

            // AuthService에서 세션을 세팅하지 않는다면 컨트롤러에서 세팅해도 된다.
            // (AuthService가 이미 세션에 넣고 있으면 이 줄은 중복이라 제거)
            session.setAttribute(SESSION_LOGIN_USER, userId);

            return "redirect:/";
        } catch (Exception e) {
            // 운영에선 로깅 권장. 지금은 사용자 안내만.
            redirectAttributes.addFlashAttribute("loginError", "로그인 처리 중 오류가 발생했습니다.");
            return "redirect:/";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
