package com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcConnection {

    private static Connection connection;

    public static void getConnection() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/main/resources/app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            try {
                Class.forName(properties.getProperty("driver"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection(properties.getProperty("url"),
                        properties.getProperty("username"), properties.getProperty("password"));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized Statement getStatement() {
        getConnection();
        Statement statement = null;
        try {
            connection.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
}

