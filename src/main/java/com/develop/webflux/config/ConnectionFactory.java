package com.develop.webflux.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.develop.webflux.PubliConstants.*;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            final String find = DATABASE_LOCAL;
            final String user = DATABASE_USER;
            final String password = DATABASE_PASSWORD;
            return DriverManager.getConnection(find, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
