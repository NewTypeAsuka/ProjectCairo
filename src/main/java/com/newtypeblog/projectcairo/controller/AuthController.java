package com.newtypeblog.projectcairo.controller;

import com.newtypeblog.projectcairo.domain.User;
import com.newtypeblog.projectcairo.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.newtypeblog.projectcairo.dto.auth.LoginRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest req, HttpSession session) {
        boolean ok = authService.login(req.userId(), req.userPw(), session);
        return ok ? ResponseEntity.ok().build()
                  : ResponseEntity.status(401).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}