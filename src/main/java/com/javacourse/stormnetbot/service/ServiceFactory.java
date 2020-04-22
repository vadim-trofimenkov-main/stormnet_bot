package com.javacourse.stormnetbot.service;

import com.javacourse.stormnetbot.service.user.UserService;
import com.javacourse.stormnetbot.service.user.UserServiceImpl;

public class ServiceFactory {
    private static UserService userService = new UserServiceImpl();

    public static UserService getUserService() {
        return userService;
    }
}
