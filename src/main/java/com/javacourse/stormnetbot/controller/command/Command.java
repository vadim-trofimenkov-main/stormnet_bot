package com.javacourse.stormnetbot.controller.command;

import com.javacourse.stormnetbot.controller.StormNetBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    void execute(StormNetBot source, Update update);
}
