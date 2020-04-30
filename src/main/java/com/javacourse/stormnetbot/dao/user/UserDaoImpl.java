package com.javacourse.stormnetbot.dao.user;

import com.javacourse.stormnetbot.dao.config.ConnectionManager;
import com.javacourse.stormnetbot.dao.tool.EntityDaoUtil;
import com.javacourse.stormnetbot.shared.entity.User;
import com.javacourse.stormnetbot.shared.exception.UserFriendlyException;
import lombok.SneakyThrows;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private final static String SELECT_USER_BY_CHAT_ID = "SELECT * FROM users WHERE chat_id = ?;";
    private final static String CREATE_USER = "INSERT INTO users(chat_id, username, status) VALUES( ?, ?, ?);";

    @SneakyThrows
    @Override
    public User getUSer(Long chatId) {
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(SELECT_USER_BY_CHAT_ID);
            statement.setLong(1, chatId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = EntityDaoUtil.initUser(resultSet);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public void createUser(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(CREATE_USER);
            statement.setLong(1, user.getChatId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getStatus());
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new UserFriendlyException("This name is already taken");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.close(statement, connection);
        }
    }
}
