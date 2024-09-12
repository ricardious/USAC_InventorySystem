package com.ricardious.database;

import java.sql.*;


public class Database {
    public Connection connection;
    public Connection myConnection() {
    String url = "jdbc:mysql://localhost:3306/usac_inventory?serverTimezone=UTC";
    String username = "root";
    String password = "root";


        try {
            connection = DriverManager.getConnection(url, username, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return connection;
    }
}
