package com.javacourse.stormnetbot.controller;

import com.javacourse.stormnetbot.controller.command.tool.ChatUtil;
import com.javacourse.stormnetbot.shared.exception.UserFriendlyException;
import lombok.SneakyThrows;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
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
            Message message = update.getMessage();
            String text = message.getText();
            TASK_MANAGER.impl(text, update, this);
        } catch (UserFriendlyException e) {
                ChatUtil.sendMessage(e.getMessage(), update, this);
        } catch (Exception e) {
                ChatUtil.sendMessage("Server error", update, this);
        }
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
        try{
            StormNetBot stormNetBot = new StormNetBot();
            telegramBotsApi.registerBot(stormNetBot);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
