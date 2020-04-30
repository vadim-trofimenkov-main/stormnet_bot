package com.javacourse.stormnetbot.service.course;

import com.javacourse.stormnetbot.shared.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllActualCourses();
    Course getFullCourse(int id);
}
