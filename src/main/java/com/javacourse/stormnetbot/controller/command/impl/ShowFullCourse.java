package com.javacourse.stormnetbot.controller.command.impl;

import com.javacourse.stormnetbot.controller.StormNetBot;
import com.javacourse.stormnetbot.controller.base.SessionManager;
import com.javacourse.stormnetbot.controller.base.UserSession;
import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.tool.ChatUtil;
import com.javacourse.stormnetbot.controller.command.tool.UiEntityUtil;
import com.javacourse.stormnetbot.service.ServiceFactory;
import com.javacourse.stormnetbot.service.course.CourseService;
import com.javacourse.stormnetbot.shared.entity.Course;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowFullCourse implements Command {
    private CourseService service = ServiceFactory.getCourseService();

    @Override
    public void execute(StormNetBot source, Update update) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        List<Course> allCourses = session.getAllCourses();
        if (allCourses == null) {
            allCourses = service.getAllActualCourses();
        }
        String input = update.getMessage().getText();
        input = input.substring(1);
        int courseNumber = Integer.parseInt(input);
        Course course = allCourses.get(courseNumber - 1);
        Course fullCourse = service.getFullCourse(course.getId());
        String fullCourseMsg = UiEntityUtil.coursesToString(fullCourse);

        ChatUtil.sendMessage(fullCourseMsg, chatId, source);

    }
}
