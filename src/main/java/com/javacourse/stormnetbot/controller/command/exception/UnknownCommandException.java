package com.javacourse.stormnetbot.controller.command.exception;

import com.javacourse.stormnetbot.shared.exception.UserFriendlyException;

public class UnknownCommandException extends UserFriendlyException {
    public UnknownCommandException() {
        super("Unknown Command.");
    }
}
