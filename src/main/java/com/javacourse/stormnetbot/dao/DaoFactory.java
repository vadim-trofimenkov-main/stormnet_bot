package com.javacourse.stormnetbot.dao;

import com.javacourse.stormnetbot.dao.user.UserDao;
import com.javacourse.stormnetbot.dao.user.UserDaoImpl;

public class DaoFactory {
    private static UserDao userDao = new UserDaoImpl();

    public static UserDao getUserDao() {
        return userDao;
    }
}
