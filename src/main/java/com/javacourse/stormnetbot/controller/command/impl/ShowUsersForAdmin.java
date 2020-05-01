package com.javacourse.stormnetbot.controller.command.impl;

import com.javacourse.stormnetbot.controller.StormNetBot;
import com.javacourse.stormnetbot.controller.base.SessionManager;
import com.javacourse.stormnetbot.controller.base.UserSession;
import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.tool.ChatUtil;
import com.javacourse.stormnetbot.controller.command.tool.UiEntityUtil;
import com.javacourse.stormnetbot.service.ServiceFactory;
import com.javacourse.stormnetbot.service.user.UserService;
import com.javacourse.stormnetbot.shared.entity.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class ShowUsersForAdmin implements Command {
    private UserService service = ServiceFactory.getUserService();

    @Override
    public void execute(StormNetBot source, Update update) throws TelegramApiException {
        UserSession session = SessionManager.getSession(update);
        Long chatId = ChatUtil.readChatId(update);
        List<User> allUsers = service.getAllUsers(chatId);

        session.setAllUsers(allUsers);
        String userToString = UiEntityUtil.userToString(allUsers);
        ChatUtil.sendMessage(userToString, update, source);
    }
}
