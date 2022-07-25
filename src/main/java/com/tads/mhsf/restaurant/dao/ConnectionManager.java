package com.tads.mhsf.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/restaurant";
    private static final String USER = "postgres";
    private static final String PASSWORD = "psql";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
