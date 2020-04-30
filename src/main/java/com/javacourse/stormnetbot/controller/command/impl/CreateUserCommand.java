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

public class CreateUserCommand implements Command {

    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void execute(StormNetBot source, Update update) throws TelegramApiException {
        String text = update.getMessage().getText();
        Long chatId = ChatUtil.readChatId(update);
        User newUser = new User(chatId, text, "simple");
        userService.createUser(newUser);
        UserSession session = SessionManager.getSession(chatId);
        session.setUser(newUser);
        if (session != null) {
            session.setUser(newUser);
        } else {
            SessionManager.putSession(chatId, newUser);
        }
        ChatUtil.sendMessage("Welcome, " + newUser.getUsername(), chatId, source);
        Command showMenu = TaskManager.getCommand((CommandNames.SHOW_MAIN_MENU));
        showMenu.execute(source, update);
    }
}