package com.ricardious.controllers;

import com.ricardious.dao.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.ricardious.dao.Database;

import java.awt.event.ActionEvent;

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
        private PasswordField tfPass;

        @FXML
        private TextField tfUser;

        @FXML
        void SignIn(MouseEvent event) {

            System.out.println("hola mundo");
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

