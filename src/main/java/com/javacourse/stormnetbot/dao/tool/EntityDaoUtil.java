package com.javacourse.stormnetbot.dao.tool;

import com.javacourse.stormnetbot.shared.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityDaoUtil {
    public static User initUser(ResultSet rs) throws SQLException {
        rs.absolute(1);
        String status = rs.getString("status");
        String username = rs.getString("username");
        Long chatId = rs.getLong("chat_id");
        return new User( chatId, username,status);
    }

}
