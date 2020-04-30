package com.javacourse.stormnetbot.service;

import com.javacourse.stormnetbot.service.course.CourseService;
import com.javacourse.stormnetbot.service.course.CourseServiceImpl;
import com.javacourse.stormnetbot.service.user.UserService;
import com.javacourse.stormnetbot.service.user.UserServiceImpl;

public class ServiceFactory {
    private static UserService userService = new UserServiceImpl();
    private static CourseService courseService = new CourseServiceImpl();

    public static UserService getUserService() { return userService; }

    public static CourseService getCourseService() { return courseService; }
}
