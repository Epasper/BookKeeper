package com.bookkeeper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public static final String BASE_SQL //
            = "SELECT username, password FROM bookkeeper.user";

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        long userId = rs.getLong("user_id");
        String userName = rs.getString("username");
        String encryptedPassword = rs.getString("password");

        return new User(encryptedPassword, userName, userId);
    }

}
