package com.javacourse.stormnetbot.service.user;

import com.javacourse.stormnetbot.dao.DaoFactory;
import com.javacourse.stormnetbot.dao.user.UserDao;
import com.javacourse.stormnetbot.shared.entity.User;

public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getUserDao();

    @Override
    public User getUSer(Long chatId) {
        User user = userDao.getUSer(chatId);
        return user;
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }
}
