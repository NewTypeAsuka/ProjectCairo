package com.newtypeblog.projectcairo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 비밀번호 해시 전용 유틸
 * - BCrypt 사용
 * - 회원가입 화면은 없지만
 *   DB에 직접 넣을 해시 생성용 + 로그인 비교용
 */
public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 평문 비밀번호 → BCrypt 해시
     */
    public static String makeHash(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 평문 비밀번호 vs 해시값 비교
     */
    public static boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}
