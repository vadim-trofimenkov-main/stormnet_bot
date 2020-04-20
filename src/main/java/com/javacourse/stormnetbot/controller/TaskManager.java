package com.javacourse.stormnetbot.controller;

import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.controller.command.exception.UnknownCommandException;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class TaskManager {
    private final Map<String, Command> COMMANDS = new HashMap<>();

   TaskManager() {

    }
    public void impl(String commandName, Update update, StormNetBot source){
       Command command = COMMANDS.get(commandName);
       if(command!= null){
           command.execute(source, update);
       }else {
           throw new UnknownCommandException();
       }
    }
}
