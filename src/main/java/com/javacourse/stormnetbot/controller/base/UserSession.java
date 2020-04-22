package com.javacourse.stormnetbot.controller.base;

import com.javacourse.stormnetbot.shared.entity.User;
import lombok.Getter;
import lombok.Setter;

public class UserSession {
    @Getter
    @Setter
    private User user;

    public UserSession(User user) {
        this.user = user;
    }
}
