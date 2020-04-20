package com.javacourse.stormnetbot.dao.config;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private Environment environment;
    private BlockingQueue<Connection> available;
    private Set<Connection> given;

    public ConnectionPool(Environment environment){
        this.environment = environment;
        System.out.println(environment);
        try{
            init();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void init() throws SQLException {
        int poolSize = environment.getPoolSize();
        given = new HashSet<>(poolSize);
        available = new ArrayBlockingQueue<>(poolSize);
        String driver = environment.getDriver();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = environment.getUrl();
        String username = environment.getUsername();
        String password = environment.getPassword();
        Connection connection;
        for (int i = 0; i < poolSize; i++) {
            connection = DriverManager.getConnection(url, username, password);
            available.add(new PooledConnection(connection));
        }
        System.out.println("Connection pool is initialized");
    }
    public Connection take(){
        try{
            synchronized (available{
                Connection connection = available.take();
                synchronized (given) {
                }
                    return connection;
                }
            }catch (InterruptedException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


