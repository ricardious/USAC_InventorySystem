package com.ricardious.controllers;

import animatefx.animation.FadeIn;
import com.ricardious.database.DatabaseConnection;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ImageView btnBack;

    @FXML
    private ImageView btnMinimize;

    @FXML
    private ImageView btnMinimize1;

    @FXML
    private ImageView btnClose;

    @FXML
    private ImageView btnClose1;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

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
    private Pane pnlSignIn;

    @FXML
    private Pane pnlSignUp;

    private void hideErrorLabelAfterDelay() {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), errorLBL);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> errorLBL.setVisible(false));
        fadeTransition.play();
    }

    private double x=0;
    private double y=0;
    public void loginS(){
        try {

                btnSignUp.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboard.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);

                root.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                stage.setScene(scene);
                stage.show();
            }
        catch(Exception e){e.printStackTrace();}
}

    @FXML
    private void SignIn(MouseEvent event) {
        if (event.getSource().equals(btnSignIn)) {
            String user = tfUser.getText();
            String password = tfPass.getText();

            if (user.isEmpty() || password.isEmpty()) {
                errorLBL.setText("Por favor, complete todos los campos.");
                errorLBL.setVisible(true);
                hideErrorLabelAfterDelay();
                return;
            }

            DatabaseConnection connectNow = new DatabaseConnection();
            String verifyLogin = "SELECT * FROM ricardious.users WHERE username = ? AND Contraseña = ?";

            try (Connection connectDB = connectNow.getConnection();
                 PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin)) {

                preparedStatement.setString(1, user);
                preparedStatement.setString(2, password);

                try (ResultSet queryResult = preparedStatement.executeQuery()) {
                    if (queryResult.next()) {
                        errorLBL.setTextFill(Color.GREEN);
                        errorLBL.setText("Login Correcto!");
                        loginS();
                    } else {
                        errorLBL.setText("Datos Incorrectos");
                    }
                    errorLBL.setVisible(true);
                    hideErrorLabelAfterDelay();
                }
            } catch (SQLException e) {
                errorLBL.setText("Error al intentar iniciar sesión. Intente de nuevo.");
                errorLBL.setVisible(true);
                hideErrorLabelAfterDelay();
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) {
        if (event.getSource().equals(btnSignUp)) {

            new FadeIn(pnlSignUp).play();
            pnlSignUp.toFront();
        }
    }

    @FXML
    private void handleMouseEvent(MouseEvent event) {
        if (event.getSource() == btnClose || event.getSource() == btnClose1) {
            System.exit(0);
        } else if (event.getSource() == btnMinimize || event.getSource() == btnMinimize1) {
            Stage stage = (Stage) btnMinimize.getScene().getWindow();
            stage.setIconified(true);
        } else if (event.getSource().equals(btnBack)) {
            new FadeIn(pnlSignIn).play();
            pnlSignIn.toFront();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}