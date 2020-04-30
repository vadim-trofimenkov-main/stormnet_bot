package com.javacourse.stormnetbot.controller.base;

import com.javacourse.stormnetbot.controller.command.Command;
import com.javacourse.stormnetbot.shared.entity.Course;
import com.javacourse.stormnetbot.shared.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UserSession {
    private User user;
    private Command nextCommand;
    private List<Course> allCourses;

    public UserSession(User user) {
        this.user = user;
    }

    public void clearNextCommandIfExists() {
        if (nextCommand != null) {
            nextCommand = null;
        }
    }
}
