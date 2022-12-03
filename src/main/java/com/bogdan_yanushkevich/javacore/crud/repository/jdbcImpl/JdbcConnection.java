package com.bogdan_yanushkevich.javacore.crud.repository.jdbcImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcConnection {


    public static Connection getConnection() {
        Properties properties = new Properties();
        Connection connection = null;

        try {
            properties.load(new FileInputStream("src/main/resources/app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return connection;
    }

    public static synchronized PreparedStatement getPreparedStatement(String SqlQuery) {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(
                    SqlQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
    public static synchronized PreparedStatement getPreparedStatementWithKeys(String SqlQuery) {

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(
                    SqlQuery, Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

}

