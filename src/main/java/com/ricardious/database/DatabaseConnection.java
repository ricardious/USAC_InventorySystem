package com.ricardious.database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "sql5744925";
        String databaseUser = "sql5744925";
        String databasePassword = "VCRQvDLqX4";
        String url = "jdbc:mysql://sql5.freesqldatabase.com/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.
                    getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
