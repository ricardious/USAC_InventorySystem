package com.ricardious.dao;

import java.sql.*;

public class Database {
    public static void myMethod() {
    String url = "jdbc:mysql://localhost:3306/usac_inventory?serverTimezone=UTC";
    String username = "root";
    String password = "root";


        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM usac_inventory.login");
            while (resultSet.next()){
                System.out.println(resultSet.getString("Usuario")+ " | " + resultSet.getString("Contraseña"));
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
