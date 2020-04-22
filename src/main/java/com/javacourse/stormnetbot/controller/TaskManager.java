package com.javacourse.stormnetbot.controller;

import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.exception.UnknownCommandException;
import com.javacourse.stormnetbot.controller.command.impl.StartCommand;
import com.javacourse.stormnetbot.controller.constant.CommandNames;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final Map<String, Command> COMMANDS = new HashMap<>();

    TaskManager() {
        COMMANDS.put(CommandNames.START, new StartCommand());
    }

    public void impl(String commandName, Update update, StormNetBot source) throws TelegramApiException {
        Command command = COMMANDS.get(commandName);
        if (command != null) {
            command.execute(source, update);
        } else {
            throw new UnknownCommandException();
        }
    }
}
