package com.newtypeblog.projectcairo.domain;

import java.time.LocalDate;

public class User {

    private Long userNo;
    private String userId;
    private String userPw;
    private String userNickname;
    private Integer userGrade;
    private String userStatus;
    private LocalDate userBirthday;

    /* =========================
       Getter
       ========================= */

    public Long getUserNo() {
        return userNo;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public Integer getUserGrade() {
        return userGrade;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public LocalDate getUserBirthday() {
        return userBirthday;
    }

    /* =========================
       Setter
       ========================= */

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setUserGrade(Integer userGrade) {
        this.userGrade = userGrade;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public void setUserBirthday(LocalDate userBirthday) {
        this.userBirthday = userBirthday;
    }
}
