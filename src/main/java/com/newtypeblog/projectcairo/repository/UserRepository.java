package com.newtypeblog.projectcairo.repository;

import com.newtypeblog.projectcairo.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findByUserId(String userId) {
        String sql = """
            SELECT
                USER_NO,
                USER_ID,
                USER_PW,
                USER_NICKNAME,
                USER_GRADE,
                USER_STATUS,
                USER_BIRTHDAY
            FROM C_USER
            WHERE USER_ID = ?
                AND USER_STATUS = 'Y'
        """;

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            User user = new User();
            user.setUserNo(rs.getLong("USER_NO"));
            user.setUserId(rs.getString("USER_ID"));
            user.setUserPw(rs.getString("USER_PW"));
            user.setUserNickname(rs.getString("USER_NICKNAME"));
            user.setUserGrade(rs.getInt("USER_GRADE"));
            user.setUserStatus(rs.getString("USER_STATUS"));
            user.setUserBirthDay(rs.getDate("USER_BIRTHDAY").toLocalDate());
            return user;
        }, userId);
    }
}
