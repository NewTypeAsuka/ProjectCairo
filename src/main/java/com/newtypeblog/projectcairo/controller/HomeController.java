package com.newtypeblog.projectcairo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /* 프로젝트 진입 지점 */
    @GetMapping("/")
    public String home() {
        return "index"; // templates/index.html 을 렌더링
    }
}

