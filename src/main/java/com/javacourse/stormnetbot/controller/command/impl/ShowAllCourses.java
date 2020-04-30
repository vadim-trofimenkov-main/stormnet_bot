package com.javacourse.stormnetbot.controller.command.impl;

import com.javacourse.stormnetbot.controller.StormNetBot;
import com.javacourse.stormnetbot.controller.TaskManager;
import com.javacourse.stormnetbot.controller.base.SessionManager;
import com.javacourse.stormnetbot.controller.base.UserSession;
import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.tool.ChatUtil;
import com.javacourse.stormnetbot.controller.command.tool.UiEntityUtil;
import com.javacourse.stormnetbot.controller.constant.CommandNames;
import com.javacourse.stormnetbot.service.ServiceFactory;
import com.javacourse.stormnetbot.service.course.CourseService;
import com.javacourse.stormnetbot.shared.entity.Course;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowAllCourses implements Command {
    private CourseService service = ServiceFactory.getCourseService();

    @Override
    public void execute(StormNetBot source, Update update) throws TelegramApiException {
        List<Course> allActualCourses = service.getAllActualCourses();
        String courses = UiEntityUtil.coursesToShortString(allActualCourses);
        ChatUtil.sendMessage(courses, update, source);
        UserSession session = SessionManager.getSession(update);
        session.setAllCourses(allActualCourses);
        session.setNextCommand(TaskManager.getCommand(CommandNames.SHOW_FULL_COURSE));
    }
}
