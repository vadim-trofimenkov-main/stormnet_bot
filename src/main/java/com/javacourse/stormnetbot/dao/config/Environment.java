package com.javacourse.stormnetbot.dao.config;

import java.io.IOException;
import java.util.Objects;

public abstract class Environment {
    protected String url;
    protected String username;
    protected String password;
    protected String driver;
    protected Integer poolSize;
    protected Environment(boolean initImmediate){
        if(initImmediate){
            init();
        }
    }

    abstract void init();

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }

    public Integer getPoolSize() {
        return poolSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Environment that = (Environment) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(driver, that.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, username, password, driver);
    }

    @Override
    public String toString() {
        return "Environment{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", driver='" + driver + '\'' +
                '}';
    }
}
