package com.javacourse.stormnetbot.dao.user;

import com.javacourse.stormnetbot.shared.entity.User;

public interface UserDao {
    User getUSer(Long chatId);
    void createUser (User user);
}
