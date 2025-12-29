package com.newtypeblog.projectcairo.repository;

import com.newtypeblog.projectcairo.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

        RowMapper<User> userRowMapper = (rs, rowNum) -> {
            User user = new User();
            user.setUserNo(rs.getLong("USER_NO"));
            user.setUserId(rs.getString("USER_ID"));
            user.setUserPw(rs.getString("USER_PW"));
            user.setUserNickname(rs.getString("USER_NICKNAME"));
            user.setUserGrade(rs.getInt("USER_GRADE"));
            user.setUserStatus(rs.getString("USER_STATUS"));
            user.setUserBirthDay(rs.getDate("USER_BIRTHDAY").toLocalDate());
            return user;
        };

        try {
            User result = jdbcTemplate.queryForObject(sql, userRowMapper, userId);
            return result;
        } catch (EmptyResultDataAccessException e) {
            // 조회 결과가 0건인 정상 케이스(=해당 유저 없음)로 보고 null 처리
            return null;
        } catch (IncorrectResultSizeDataAccessException e) {
            // 같은 USER_ID로 여러 건이 나왔다는 뜻(데이터 무결성 문제)
            // 여기서는 그대로 터뜨리는 게 보통 더 안전함.
            throw e;
        }
    }
}
