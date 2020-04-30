package com.javacourse.stormnetbot.dao.course;

import com.javacourse.stormnetbot.dao.config.ConnectionManager;
import com.javacourse.stormnetbot.dao.tool.EntityDaoUtil;
import com.javacourse.stormnetbot.shared.entity.Course;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private static final String SELECT_ALL_COURSES = "SELECT id, title, start_date FROM courses";
    private static final String SELECT_FULL_COURSE = "SELECT * FROM courses WHERE id = ?";

    @SneakyThrows
    @Override
    public List<Course> getAllCoursesShort() {
        List<Course> courses;
        Connection connection;
        Statement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_COURSES);
            courses = EntityDaoUtil.initCoursesShort(resultSet);
        } catch (SQLException e) {
            ConnectionManager.close(statement,connection);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return courses;
    }

    @SneakyThrows
    @Override
    public Course getFullCourse(int id) {
        Course course;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionManager.take();
            statement = connection.prepareStatement(SELECT_FULL_COURSE);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            course = EntityDaoUtil.initCourse(resultSet);
        } catch (SQLException e) {
            ConnectionManager.close(statement,connection);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return course;
    }
}
