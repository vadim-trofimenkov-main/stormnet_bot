package com.javacourse.stormnetbot.dao.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class JdbcConfigurer {
    private static Environment environment;
    private static JdbcConfigurer instance;
    private static final Boolean DOUBLE_CHECK = true;

    {
        initDriver();
    }

    private JdbcConfigurer() {
    }

    private void initDriver() {
        try {
            Class.forName(environment.getDriver());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(environment.getUrl(), environment.getUsername(), environment.getPassword());
    }

    public static JdbcConfigurer getInstance() {
        try {
            if (environment == null) {
                environment = new JdbcMySQLEnvironment();
                environment.init();
            }
            if (instance == null) {
                synchronized (DOUBLE_CHECK) {
                    if (instance == null) {
                        instance = new JdbcConfigurer();
                    }
                }
            }
            return instance;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void setEnvironment(Environment environment) {
        JdbcConfigurer.environment = environment;
    }
}
