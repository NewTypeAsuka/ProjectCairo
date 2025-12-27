package com.newtypeblog.projectcairo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // templates/index.html 을 렌더링
        return "index";
    }
}

