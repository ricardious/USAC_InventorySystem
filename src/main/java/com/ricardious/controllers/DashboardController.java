package com.ricardious.controllers;

import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DashboardController implements Initializable {

    @FXML
    private Button addEmployee_addBtn;

    @FXML
    private Button addEmployee_btn;

    @FXML
    private Button addEmployee_clearBtn;

    @FXML
    private TableColumn<?, ?> addEmployee_col_date;

    @FXML
    private TableColumn<?, ?> addEmployee_col_employeeID;

    @FXML
    private TableColumn<?, ?> addEmployee_col_firstName;

    @FXML
    private TableColumn<?, ?> addEmployee_col_gender;

    @FXML
    private TableColumn<?, ?> addEmployee_col_lastName;

    @FXML
    private TableColumn<?, ?> addEmployee_col_phoneNum;

    @FXML
    private TableColumn<?, ?> addEmployee_col_position;

    @FXML
    private Button addEmployee_deleteBtn;

    @FXML
    private TextField addEmployee_employeeID;

    @FXML
    private TextField addEmployee_firstName;

    @FXML
    private AnchorPane addEmployee_form;

    @FXML
    private ComboBox<?> addEmployee_gender;

    @FXML
    private ImageView addEmployee_image;

    @FXML
    private Button addEmployee_importBtn;

    @FXML
    private TextField addEmployee_lastName;

    @FXML
    private TextField addEmployee_phoneNum;

    @FXML
    private ComboBox<?> addEmployee_position;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TableView<?> addEmployee_tableView;

    @FXML
    private Button addEmployee_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private BarChart<?, ?> home_chart;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalEmployees;

    @FXML
    private Label home_totalInactiveEm;

    @FXML
    private Label home_totalPresents;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button maximize;


    @FXML
    private Button salary_btn;

    @FXML
    private Button salary_clearBtn;

    @FXML
    private TableColumn<?, ?> salary_col_employeeID;

    @FXML
    private TableColumn<?, ?> salary_col_firstName;

    @FXML
    private TableColumn<?, ?> salary_col_lastName;

    @FXML
    private TableColumn<?, ?> salary_col_position;

    @FXML
    private TableColumn<?, ?> salary_col_salary;

    @FXML
    private TextField salary_employeeID;

    @FXML
    private Label salary_firstName;

    @FXML
    private AnchorPane salary_form;

    @FXML
    private Label salary_lastName;

    @FXML
    private Label salary_position;

    @FXML
    private TextField salary_salary;

    @FXML
    private TableView<?> salary_tableView;

    @FXML
    private Button salary_updateBtn;

    @FXML
    private Label username;

    @FXML
    private Button addEmployee_addBtn1;

    @FXML
    private Button addEmployee_clearBtn1;

    @FXML
    private TableColumn<?, ?> addEmployee_col_date1;

    @FXML
    private TableColumn<?, ?> addEmployee_col_employeeID1;

    @FXML
    private TableColumn<?, ?> addEmployee_col_firstName1;

    @FXML
    private TableColumn<?, ?> addEmployee_col_gender1;

    @FXML
    private TableColumn<?, ?> addEmployee_col_lastName1;

    @FXML
    private TableColumn<?, ?> addEmployee_col_phoneNum1;

    @FXML
    private TableColumn<?, ?> addEmployee_col_position1;

    @FXML
    private Button addEmployee_deleteBtn1;

    @FXML
    private TextField addEmployee_employeeID1;

    @FXML
    private TextField addEmployee_firstName1;

    @FXML
    private AnchorPane addEmployee_form1;

    @FXML
    private ComboBox<?> addEmployee_gender1;

    @FXML
    private ImageView addEmployee_image1;

    @FXML
    private Button addEmployee_importBtn1;

    @FXML
    private TextField addEmployee_lastName1;

    @FXML
    private TextField addEmployee_phoneNum1;

    @FXML
    private ComboBox<?> addEmployee_position1;

    @FXML
    private TextField addEmployee_search1;

    @FXML
    private TableView<?> addEmployee_tableView1;

    @FXML
    private Button addEmployee_updateBtn1;

    @FXML
    private Button inventory_btn1;

    @FXML
    private AnchorPane inventoryeployee_form;

    @FXML
    private Button inventory_btn11;

    @FXML
    private AnchorPane inventoryglobal_form;

    @FXML
    void importEXCEL(MouseEvent event) {

    }


    public void switchForm(ActionEvent event){

        if(event.getSource() == home_btn){
            home_form.setVisible(true);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            addEmployee_form1.setVisible(false);
            inventoryeployee_form.setVisible(false);
            inventoryglobal_form.setVisible(false);
        }else if(event.getSource() == addEmployee_btn){
            home_form.setVisible(false);
            addEmployee_form.setVisible(true);
            salary_form.setVisible(false);
            addEmployee_form1.setVisible(false);
            inventoryeployee_form.setVisible(false);
            inventoryglobal_form.setVisible(false);
        }else if(event.getSource() == salary_btn){
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(true);
            addEmployee_form1.setVisible(false);
            inventoryeployee_form.setVisible(false);
            inventoryglobal_form.setVisible(false);
        }else if(event.getSource() == inventory_btn){
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            addEmployee_form1.setVisible(true);
            inventoryeployee_form.setVisible(false);
            inventoryglobal_form.setVisible(false);
        }else if(event.getSource() == inventory_btn1){
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            addEmployee_form1.setVisible(false);
            inventoryeployee_form.setVisible(true);
            inventoryglobal_form.setVisible(false);
        }else if(event.getSource() == inventory_btn11){
            home_form.setVisible(false);
            addEmployee_form.setVisible(false);
            salary_form.setVisible(false);
            addEmployee_form1.setVisible(false);
            inventoryeployee_form.setVisible(false);
            inventoryglobal_form.setVisible(true);
        }


    }


    private double x=0;
    private double y=0;

    public void logout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Mensaje de Confirmacion");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas salir?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
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
        }catch(Exception e){e.printStackTrace();}
    }

    public void maximize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        if (!stage.isMaximized()) {
            // Get the size of the screen
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Adjust the window size to simulate a custom maximized state
            stage.setX(screenBounds.getMinX());
            stage.setY(screenBounds.getMinY());
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());
        } else {
            // Restore the previous window size if it was already "maximized"
            stage.setWidth(800);  // Default or desired width
            stage.setHeight(600); // Default or desired height
            stage.centerOnScreen(); // Center the window if necessary
        }

        stage.setMaximized(!stage.isMaximized()); // Toggle the maximized state

    }

    public void close(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumnResizePolicy(addEmployee_tableView);
        setColumnResizePolicy(salary_tableView);
        setColumnResizePolicy(addEmployee_tableView1);
    }

    private void setColumnResizePolicy(TableView<?> tableView) {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
