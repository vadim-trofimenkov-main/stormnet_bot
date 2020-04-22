package com.javacourse.stormnetbot.service.user;

import com.javacourse.stormnetbot.shared.entity.User;

public interface UserService {
    User getUSer(Long chatId);
    void createUser (User user);
}
