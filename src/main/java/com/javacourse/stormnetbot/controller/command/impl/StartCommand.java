package com.javacourse.stormnetbot.controller.command.impl;

import com.javacourse.stormnetbot.controller.StormNetBot;
import com.javacourse.stormnetbot.controller.TaskManager;
import com.javacourse.stormnetbot.controller.base.SessionManager;
import com.javacourse.stormnetbot.controller.base.UserSession;
import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.tool.ChatUtil;
import com.javacourse.stormnetbot.controller.constant.CommandNames;
import com.javacourse.stormnetbot.service.ServiceFactory;
import com.javacourse.stormnetbot.service.user.UserService;
import com.javacourse.stormnetbot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements Command {
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void execute(StormNetBot source, Update update) throws TelegramApiException {
        Long chatId = ChatUtil.readChatId(update);
        User user = userService.getUSer(chatId);
        UserSession session = SessionManager.getSession(chatId);
        if (user != null) {
            if (session != null) {
                session.setUser(user);
            } else {
                SessionManager.putSession(chatId, user);
            }
            Command showMenu = TaskManager.getCommand((CommandNames.SHOW_MAIN_MENU));
            showMenu.execute(source, update);
        } else {
            ChatUtil.sendMessage("Hi. Enter your username.", chatId, source);
            Command newUseCommand = TaskManager.getCommand(CommandNames.NEW_USER);
            session = SessionManager.putSession(chatId, new User(chatId, null, "not registered"));
            session.setNextCommand(newUseCommand);
        }
    }
}

