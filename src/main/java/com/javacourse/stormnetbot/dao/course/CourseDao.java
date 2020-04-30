package com.javacourse.stormnetbot.dao.course;

import com.javacourse.stormnetbot.shared.entity.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCoursesShort();
    Course getFullCourse(int id);
}
