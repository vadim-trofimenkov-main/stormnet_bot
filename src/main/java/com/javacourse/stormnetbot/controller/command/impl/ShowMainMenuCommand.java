package com.javacourse.stormnetbot.controller.command.impl;

import com.javacourse.stormnetbot.controller.StormNetBot;
import com.javacourse.stormnetbot.controller.base.SessionManager;
import com.javacourse.stormnetbot.controller.base.UserSession;
import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.tool.ChatUtil;
import com.javacourse.stormnetbot.controller.constant.CommandNames;
import com.javacourse.stormnetbot.shared.entity.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;

public class ShowMainMenuCommand implements Command {
    private static final String ALL_COURSES = "All courses";
    private static final String MY_COURSES = "My courses";

    @Override
    public void execute(StormNetBot source, Update update) throws TelegramApiException {

        List<InlineKeyboardButton> buttons1 = Arrays.asList(
                new InlineKeyboardButton(MY_COURSES).setCallbackData(CommandNames.SHOW_MY_COURSES)
        );
        List<InlineKeyboardButton> buttons2 = Arrays.asList(
                new InlineKeyboardButton(ALL_COURSES).setCallbackData(CommandNames.SHOW_ALL_COURSES)
        );

        List<List<InlineKeyboardButton>> keyboard = Arrays.asList(buttons1, buttons2);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup(keyboard);
        Long chatId = ChatUtil.readChatId(update);
        UserSession session = SessionManager.getSession(chatId);
        User user = session.getUser();
        String message = "Hi, " + user.getUsername();
        ChatUtil.sendMessageWithMarkup(message, update, source, markup);
    }
}
