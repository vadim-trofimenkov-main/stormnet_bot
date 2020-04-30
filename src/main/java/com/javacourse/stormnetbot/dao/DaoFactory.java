package com.javacourse.stormnetbot.dao;

import com.javacourse.stormnetbot.dao.course.CourseDao;
import com.javacourse.stormnetbot.dao.course.CourseDaoImpl;
import com.javacourse.stormnetbot.dao.user.UserDao;
import com.javacourse.stormnetbot.dao.user.UserDaoImpl;

public class DaoFactory {
    private static UserDao userDao = new UserDaoImpl();
    private static CourseDao courseDao = new CourseDaoImpl();

    public static UserDao getUserDao() { return userDao; }

    public static CourseDao getCourseDao() { return courseDao; }
}
