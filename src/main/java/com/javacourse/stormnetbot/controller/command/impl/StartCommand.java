package com.javacourse.stormnetbot.controller.command.impl;

import com.javacourse.stormnetbot.controller.StormNetBot;
import com.javacourse.stormnetbot.controller.base.SessionManager;
import com.javacourse.stormnetbot.controller.base.UserSession;
import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.tool.ChatUtil;
import com.javacourse.stormnetbot.service.ServiceFactory;
import com.javacourse.stormnetbot.service.user.UserService;
import com.javacourse.stormnetbot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand implements Command {
    private UserService userService = ServiceFactory.getUserService();

    @Override
    public void execute(StormNetBot source, Update update) throws TelegramApiException {
        UserSession session = SessionManager.getSession(update);
        Long chatId = ChatUtil.readChatId(update);
        if (session != null) {
            String userName = session.getUser().getUsername();
            ChatUtil.sendMessage("Hi, " + userName, update, source);
        } else {
            User user = userService.getUSer(chatId);
            if (user == null) {
                ChatUtil.sendMessage("I don't know who are you", chatId, source);
            } else {
                if (user.getStatus().equals("super_admin")) {
                    ChatUtil.sendMessage("Ave king " + user.getUsername(), chatId, source);

                } else {
                    ChatUtil.sendMessage("Hi " + user.getUsername(), chatId, source);
                }
                SessionManager.putSession(chatId, user);
            }
        }
    }
}
