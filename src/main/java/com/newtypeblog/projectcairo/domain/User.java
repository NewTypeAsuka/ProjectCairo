package com.newtypeblog.projectcairo.domain;

import java.time.LocalDate;

public class User {

    /* field */
    private Long userNo;
    private String userId;
    private String userPw;
    private String userProfile;
    private String userNickname;
    private LocalDate userBirthday;
    private Integer userGrade;
    private String userStatus;

    /* getter */
    public Long getUserNo() { return userNo; }
    public String getUserId() { return userId; }
    public String getUserPw() { return userPw; }
    public String getUserProfile() { return userProfile; }
    public String getUserNickname() { return userNickname; }
    public LocalDate getUserBirthday() { return userBirthday; }
    public Integer getUserGrade() { return userGrade; }
    public String getUserStatus() { return userStatus; }

    /* setter */
    public void setUserNo(Long userNo) { this.userNo = userNo;}
    public void setUserId(String userId) { this.userId = userId;}
    public void setUserPw(String userPw) { this.userPw = userPw;}
    public void setUserProfile(String userProfile) { this.userProfile = userProfile;}
    public void setUserNickname(String userNickname) { this.userNickname = userNickname;}
    public void setUserBirthDay(LocalDate userBirthday) { this.userBirthday = userBirthday;}
    public void setUserGrade(Integer userGrade) { this.userGrade = userGrade;}
    public void setUserStatus(String userStatus) { this.userStatus = userStatus;}

}
