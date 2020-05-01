package com.javacourse.stormnetbot.service.user;

import com.javacourse.stormnetbot.shared.entity.User;

import java.util.List;

public interface UserService {
    User getUSer(Long chatId);

    void createUser(User user);

    List<User> getAllUsers(Long curChatId);
}
