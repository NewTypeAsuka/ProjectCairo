package com.newtypeblog.projectcairo.dto.auth;

/**
 * 로그인 요청 DTO
 * - 클라이언트(login.js)에서 JSON으로 넘어오는 로그인 입력값을 담는다.
 * - DB에는 해시된 비밀번호가 저장되어 있어도, 요청은 평문 비밀번호(userPw)를 받는다.
 *   → 서비스에서 DB의 해시값과 매칭(verify) 처리하면 됨.
 */
public class LoginRequest {

    /* field */
    private String userId;   // 아이디
    private String userPw;   // 평문 비밀번호(입력값)

    public LoginRequest() {
        // Jackson(JSON->객체 변환) 기본 생성자
    }
k
    public LoginRequest(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    /* getter */
    public String getUserId() { return userId; }
    public String getUserPw() { return userPw; }

    /* setter */
    public void setUserId(String userId) { this.userId = userId; }
    public void setUserPw(String userPw) { this.userPw = userPw; }

}
