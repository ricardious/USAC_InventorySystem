package com.ricardious.controllers;

import com.ricardious.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {


    @FXML
    private ImageView btnMinimize;

    @FXML
    private ImageView btnClose;

    @FXML
    private Button btnSignIn;

    @FXML
    private Label lblForgot;

    @FXML
    private Label lblForgot1;

    @FXML
    private Label errorLBL;

    @FXML
    private PasswordField tfPass;

    @FXML
    private TextField tfUser;

    @FXML
    void SignIn(MouseEvent event) {
        String User = tfUser.getText();
        String Password = tfPass.getText();
        Database connectNow = new Database();
        Connection connectDB = connectNow.myConnection();
        String verifyLogin = "SELECT * FROM usac_inventory.login WHERE Usuario = '" + User + "' AND Contraseña = '" + Password + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            if (queryResult.next()) {
                errorLBL.setTextFill(Color.GREEN);
                errorLBL.setText("Login Correcto!");
            } else {
                errorLBL.setText("Datos Incorrectos");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnClose) {
            System.exit(0);
        } else if (event.getSource() == btnMinimize) {
            Stage stage = (Stage) btnMinimize.getScene().getWindow();
            stage.setIconified(true);
        }

    }

}