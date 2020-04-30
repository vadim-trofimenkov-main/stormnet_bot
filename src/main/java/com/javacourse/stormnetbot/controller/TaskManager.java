package com.javacourse.stormnetbot.controller;

import com.javacourse.stormnetbot.controller.base.SessionManager;
import com.javacourse.stormnetbot.controller.base.UserSession;
import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.exception.UnknownCommandException;
import com.javacourse.stormnetbot.controller.command.impl.*;
import com.javacourse.stormnetbot.controller.constant.CommandNames;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private static final Map<String, Command> COMMANDS = new HashMap<>();

    TaskManager() {
        COMMANDS.put(CommandNames.START, new StartCommand());
        COMMANDS.put(CommandNames.NEW_USER, new CreateUserCommand());
        COMMANDS.put(CommandNames.SHOW_MAIN_MENU, new ShowMainMenuCommand());
        COMMANDS.put(CommandNames.SHOW_ALL_COURSES, new ShowAllCourses());
        COMMANDS.put(CommandNames.SHOW_FULL_COURSE, new ShowFullCourse());
    }

    public void impl(String commandName, Update update, StormNetBot source) throws TelegramApiException {
        Command command = COMMANDS.get(commandName);
        UserSession session = SessionManager.getSession(update);
        if (command != null) {
            if (session != null) {
                session.clearNextCommandIfExists();
            }
            command.execute(source, update);
        } else {
            Command nextCommand = session.getNextCommand();
            if (nextCommand != null) {
                nextCommand.execute(source, update);
            } else {
                throw new UnknownCommandException();
            }
        }
    }

    public static Command getCommand(String name) {
        return COMMANDS.get(name);
    }
}
