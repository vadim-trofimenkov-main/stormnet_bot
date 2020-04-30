package com.javacourse.stormnetbot.controller.command.tool;

import com.javacourse.stormnetbot.shared.entity.Course;

import java.util.Collection;
import java.util.Collections;

public class UiEntityUtil {
    public static String coursesToShortString(Collection<Course> courses) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (Course course : courses) {
            builder.append("/")
                    .append(++i)
                    .append(" ")
                    .append(course.getTitle())
                    .append("    Start date: ")
                    .append(course.getStartDate())
                    .append("\n");

        }
        return builder.toString();
    }

    public static String coursesToString(Course course) {
        StringBuilder builder = new StringBuilder();
        builder.append(course.getTitle())
                .append("\n")
                .append("Start date: ")
                .append(course.getStartDate())
                .append("\n")
                .append("Description: ")
                .append(course.getDescription());
        return builder.toString();
    }
}
