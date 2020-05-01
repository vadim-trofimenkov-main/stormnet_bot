package com.javacourse.stormnetbot.controller;

import com.javacourse.stormnetbot.controller.base.SessionManager;
import com.javacourse.stormnetbot.controller.base.UserSession;
import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.exception.UnknownCommandException;
import com.javacourse.stormnetbot.controller.command.tool.ChatUtil;
import com.javacourse.stormnetbot.controller.constant.CommandNames;
import com.javacourse.stormnetbot.shared.entity.User;
import com.javacourse.stormnetbot.shared.entity.security.Role;
import com.javacourse.stormnetbot.shared.exception.UserFriendlyException;
import lombok.SneakyThrows;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StormNetBot extends TelegramLongPollingBot {
    private final TaskManager TASK_MANAGER = new TaskManager();

    static {
        ApiContextInitializer.init();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (!preCheck(update)) return;
            String input = getInput(update);
            TASK_MANAGER.impl(input, update, this);
        } catch (UserFriendlyException e) {
            e.printStackTrace();
            ChatUtil.sendMessage(e.getMessage(), update, this);
        } catch (Exception e) {
            e.printStackTrace();
            ChatUtil.sendMessage("Server error", update, this);
        }
    }

    private boolean preCheck(Update update) throws TelegramApiException {
        if (!sessionOk(update)) {
            Command command = TaskManager.getCommand(CommandNames.START);
            command.execute(this, update);
            return false;
        }
        return checkPermission(update);
    }

    private boolean checkPermission(Update update) throws TelegramApiException {
        UserSession session = SessionManager.getSession(update);
        User user = session.getUser();
        if (Role.BLOCKED.equals(user.getRole())) {
            ChatUtil.sendMessage("You are blocked", update, this);
            return false;
        }
        return true;
    }

    private boolean sessionOk(Update update) {
        UserSession session = SessionManager.getSession(update);
        return session != null;
    }

    private String getInput(Update update) {
        String input;
        if (update.hasMessage()) {
            input = update.getMessage().getText();
        } else if (update.hasCallbackQuery()) {
            input = update.getCallbackQuery().getData();
        } else {
            throw new UnknownCommandException();
        }
        return input;
    }

    @Override
    public String getBotUsername() {
        return "Stormnet_Bot";
    }

    @Override
    public String getBotToken() {
        return "1118009599:AAGYIqFb3AyGizyjDbTMNbA3Zllq-zRIJ0I";
    }

    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            StormNetBot stormNetBot = new StormNetBot();
            telegramBotsApi.registerBot(stormNetBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
