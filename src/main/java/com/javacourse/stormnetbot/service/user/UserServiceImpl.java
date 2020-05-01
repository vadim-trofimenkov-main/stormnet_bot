package com.javacourse.stormnetbot.service.user;

import com.javacourse.stormnetbot.dao.DaoFactory;
import com.javacourse.stormnetbot.dao.user.UserDao;
import com.javacourse.stormnetbot.shared.entity.User;
import com.javacourse.stormnetbot.shared.entity.security.Feature;
import com.javacourse.stormnetbot.shared.exception.PermissionDeniedException;
import com.javacourse.stormnetbot.shared.tool.SecurityUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    @Override
    public List<User> getAllUsers(Long curChatId) {
        User user = getUSer(curChatId);
        if(!SecurityUtil.hasFeature(user, Feature.VIEW_USERS)){
            throw new PermissionDeniedException();
        }
        List<User> allUsers = userDao.getAllUsers();
        Collections.sort(allUsers, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                Integer compare1 = o1.getRole().getCompare();
                Integer compare2 = o2.getRole().getCompare();
                return compare1.compareTo(compare2);
            }
        });
        return allUsers;
    }
}
