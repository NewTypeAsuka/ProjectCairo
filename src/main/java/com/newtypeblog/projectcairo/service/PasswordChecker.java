package com.newtypeblog.projectcairo.service;

/**
 * 비밀번호 검증 전략 인터페이스
 *
 * - 평문 / BCrypt / 다른 해시 방식으로 바뀌어도
 *   AuthService는 이 인터페이스만 의존
 */
public interface PasswordChecker {

    /**
     * 사용자가 입력한 비밀번호(rawPassword)와
     * DB에 저장된 해시 비밀번호(encodedPassword)를 비교
     *
     * @param rawPassword     사용자가 입력한 비밀번호
     * @param encodedPassword DB에 저장된 해시 비밀번호
     * @return 일치 여부
     */
    boolean matches(String rawPassword, String encodedPassword);
}
