package com.newtypeblog.projectcairo.util;

import com.newtypeblog.projectcairo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupDbSmokeTest implements CommandLineRunner {

    private final UserRepository userRepository;

    public StartupDbSmokeTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        // 앱 시작하자마자 1번 DB 호출해서 연결 확인
        try {
            var user = userRepository.findByUserId("sangzoon0102");
            System.out.println("[DB TEST OK] userNickname=" + user.getUserNickname());
            System.out.println("[DB TEST OK] userId=" + user.getUserId());
            System.out.println("[DB TEST OK] userPw=" + user.getUserPw());
        } catch (Exception e) {
            System.out.println("[DB TEST FAIL] " + e.getMessage());
            // 원하면 e.printStackTrace(); 도 가능
        }
    }
}
