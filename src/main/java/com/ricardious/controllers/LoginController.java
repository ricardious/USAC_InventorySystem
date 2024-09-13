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
import java.util.Objects;

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
    private Label Rg;

    @FXML
    private TextField UR;

    @FXML
    private TextField CC;

    @FXML
    private TextField CC1;

    @FXML
    private Button btnSignIn1;

    @FXML
    void SignIn(MouseEvent event) {
        if (btnSignIn.getText().equals("Iniciar Sesión")){

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

    @FXML
    public void Registro(){
        btnSignIn1.setOnMouseClicked(event->toggleVisibility());

    }

    private void toggleVisibility() {
        String texto;
        texto = btnSignIn1.getText();

        if (Objects.equals(texto, "Registro")){
            btnSignIn1.setText("Regresar");
            btnSignIn.setText("Crear Usuario");
            tfUser.setVisible(false);
            tfPass.setVisible(false);
            lblForgot1.setVisible(false);
            Rg.setVisible(true);
            UR.setVisible(true);
            CC.setVisible(true);
            CC1.setVisible(true);

        } else if (Objects.equals(texto, "Regresar")) {
            btnSignIn1.setText("Registro");
            btnSignIn.setText("Iniciar Sesión");
            tfUser.setVisible(true);
            tfPass.setVisible(true);
            lblForgot1.setVisible(true);
            Rg.setVisible(false);
            UR.setVisible(false);
            CC.setVisible(false);
            CC1.setVisible(false);
        }
    }

}