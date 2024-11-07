package com.ricardious.controllers;

import com.ricardious.database.DatabaseConnection;
import com.ricardious.models.ActivoFijo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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

import javax.swing.*;

public class DashboardController implements Initializable {

    @FXML
    private Button Add_Activos;

    @FXML
    private Button Clear_Campos;

    @FXML
    private Button Delete_Activos;

    @FXML
    private TextField Descripcion_Activos;

    @FXML
    private TextField Literal_Activos;

    @FXML
    private TextField Renglon_Gasto_Activos;

    @FXML
    private TextField Search_Bienes;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button home_btn;

    @FXML
    private Button agregar_empleado;

    @FXML
    private Button saldo_activos;

    @FXML
    private Button logout;

    @FXML
    private Button inventario_empleado;

    @FXML
    private Button inventario_activos;

    @FXML
    private Button inventario_Global;

    @FXML
    private Button empleado;

    @FXML
    private Button edificio;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AnchorPane inventarioempleado_form;

    @FXML
    private AnchorPane bienes;

    @FXML
    private TableView<Map> addEmployee_tableView11;

    @FXML
    private TableColumn<?, ?> addEmployee_col_employeeID11;

    @FXML
    private TableColumn<?, ?> addEmployee_col_firstName11;

    @FXML
    private TableColumn<?, ?> addEmployee_col_lastName11;

    @FXML
    private AnchorPane inventarioglobal_form;


    @FXML
    private AnchorPane agregar_empleado_form;


    @FXML
    private TableView<?> addEmployee_tableView1;


    @FXML
    private AnchorPane saldo_activos_form;

    @FXML
    private TableView<?> saldo_tableView;

    @FXML
    private AnchorPane edificios_form;

    @FXML
    private AnchorPane empleado_form;


    private FilteredList<Map> filteredData;
    private SortedList<Map> sortedData;


    private <T> void setupTableSearch(TextField searchField, TableView<T> tableView, ObservableList<T> data,
                                      String... searchProperties) {
        // Inicializa FilteredList con todos los datos
        FilteredList<T> filteredData = new FilteredList<>(data, p -> true);

        // Añade listener al campo de búsqueda
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(item -> {
                // Si el texto de búsqueda está vacío, muestra todos los items
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Busca en todas las propiedades especificadas
                for (String property : searchProperties) {
                    String value = "";
                    if (item instanceof Map) {
                        value = String.valueOf(((Map) item).get(property));
                    } else {
                        try {
                            value = String.valueOf(item.getClass().getField(property).get(item));
                        } catch (Exception e) {
                            continue;
                        }
                    }

                    if (value.toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                }
                return false;
            });
        });

        // Enlaza SortedList con FilteredList
        SortedList<T> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        // Vincula los datos filtrados y ordenados a la tabla
        tableView.setItems(sortedData);
    }

    private void setupBienesSearch() {
        setupTableSearch(Search_Bienes,
                addEmployee_tableView11,
                getBienes(),
                ColLiteral, ColDescripcion, ColRenglonGasto);
    }



    @FXML
    void importEXCEL(MouseEvent event) {

    }

    private String ColLiteral = "Literal";
    private String ColDescripcion = "Descripcion";
    private String ColRenglonGasto = "RenglonGasto";

    public ObservableList<Map> getBienes(){
        var sql = "SELECT * FROM usac_inventory.bienes";
        ObservableList<Map> bienesList = FXCollections.observableArrayList();
        try{
        DatabaseConnection connectNow = new DatabaseConnection();
        PreparedStatement consulta = connectNow.getConnection().prepareStatement(sql);
        ResultSet resultSet = consulta.executeQuery();
        while (resultSet.next()){
            ActivoFijo ActivoFijo = new ActivoFijo();
            Map<String, Object> coleccion = new HashMap<>();
            ActivoFijo.setLiteral(resultSet.getString("Literal"));
            ActivoFijo.setDescripcion(resultSet.getString("Descripcion"));
            ActivoFijo.setRenglonGasto(Integer.parseInt(resultSet.getString("RenglonGasto")));
            coleccion.put(ColLiteral, ActivoFijo.getLiteral());
            coleccion.put(ColDescripcion, ActivoFijo.getDescripcion());
            coleccion.put(ColRenglonGasto, String.valueOf(resultSet.getInt("RenglonGasto")));
            bienesList.add(coleccion);
        }
        resultSet.close();
        consulta.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bienesList;
    }

    private void llenarTablaBienes(){
        ObservableList<Map> lista = getBienes();
        this.addEmployee_col_employeeID11.setCellValueFactory(new MapValueFactory(ColLiteral));
        this.addEmployee_col_firstName11.setCellValueFactory(new MapValueFactory(ColDescripcion));
        this.addEmployee_col_lastName11.setCellValueFactory(new MapValueFactory(ColRenglonGasto));
        this.addEmployee_tableView11.setItems(lista);

        setupBienesSearch();
    }


    public void switchForm(ActionEvent event){

        if(event.getSource() == home_btn){
            home_form.setVisible(true);
            bienes.setVisible(false);
            saldo_activos_form.setVisible(false);
            agregar_empleado_form.setVisible(false);
            inventarioglobal_form.setVisible(false);
            inventarioempleado_form.setVisible(false);
            empleado_form.setVisible(false);
            edificios_form.setVisible(false);


            //Color que muestra en que pestaña estamos
            home_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #7f00ff, #e100ff);");
            inventario_activos.setStyle("-fx-background-color: transparent");
            saldo_activos.setStyle("-fx-background-color: transparent");
            agregar_empleado_form.setStyle("-fx-background-color: transparent");
            inventario_Global.setStyle("-fx-background-color: transparent");
            inventario_empleado.setStyle("-fx-background-color: transparent");
            empleado.setStyle("-fx-background-color: transparent");
            edificio.setStyle("-fx-background-color: transparent");





        }else if(event.getSource() == inventario_activos){
            home_form.setVisible(false);
            bienes.setVisible(true);
            saldo_activos_form.setVisible(false);
            agregar_empleado_form.setVisible(false);
            inventarioglobal_form.setVisible(false);
            inventarioempleado_form.setVisible(false);
            empleado_form.setVisible(false);
            edificios_form.setVisible(false);
              //Color que muestra en que pestaña estamos
            inventario_activos.setStyle("-fx-background-color: linear-gradient(to bottom right, #7f00ff, #e100ff);");
            home_btn.setStyle("-fx-background-color: transparent");
            saldo_activos.setStyle("-fx-background-color: transparent");
            agregar_empleado.setStyle("-fx-background-color: transparent");
            inventario_Global.setStyle("-fx-background-color: transparent");
            inventario_empleado.setStyle("-fx-background-color: transparent");
            empleado.setStyle("-fx-background-color: transparent");
            edificio.setStyle("-fx-background-color: transparent");

            llenarTablaBienes();

        }else if(event.getSource() == saldo_activos){
            home_form.setVisible(false);
            bienes.setVisible(false);
            saldo_activos_form.setVisible(true);
            agregar_empleado_form.setVisible(false);
            inventarioglobal_form.setVisible(false);
            inventarioempleado_form.setVisible(false);
            empleado_form.setVisible(false);
            edificios_form.setVisible(false);
            //Color que muestra en que pestaña estamos
            saldo_activos.setStyle("-fx-background-color: linear-gradient(to bottom right, #7f00ff, #e100ff);");
            inventario_activos.setStyle("-fx-background-color: transparent");
            home_btn.setStyle("-fx-background-color: transparent");
            agregar_empleado.setStyle("-fx-background-color: transparent");
            inventario_Global.setStyle("-fx-background-color: transparent");
            inventario_empleado.setStyle("-fx-background-color: transparent");
            empleado.setStyle("-fx-background-color: transparent");
            edificio.setStyle("-fx-background-color: transparent");

        }else if(event.getSource() == agregar_empleado){
            home_form.setVisible(false);
            bienes.setVisible(false);
            saldo_activos_form.setVisible(false);
            agregar_empleado_form.setVisible(true);
            inventarioglobal_form.setVisible(false);
            inventarioempleado_form.setVisible(false);
            empleado_form.setVisible(false);
            edificios_form.setVisible(false);

            agregar_empleado.setStyle("-fx-background-color: linear-gradient(to bottom right, #7f00ff, #e100ff);");
            inventario_activos.setStyle("-fx-background-color: transparent");
            saldo_activos.setStyle("-fx-background-color: transparent");
            home_btn.setStyle("-fx-background-color: transparent");
            inventario_Global.setStyle("-fx-background-color: transparent");
            inventario_empleado.setStyle("-fx-background-color: transparent");
            empleado.setStyle("-fx-background-color: transparent");
            edificio.setStyle("-fx-background-color: transparent");

        }else if(event.getSource() == inventario_Global){
            home_form.setVisible(false);
            bienes.setVisible(false);
            saldo_activos_form.setVisible(false);
            agregar_empleado_form.setVisible(false);
            inventarioglobal_form.setVisible(true);
            inventarioempleado_form.setVisible(false);
            empleado_form.setVisible(false);
            edificios_form.setVisible(false);

            inventario_Global.setStyle("-fx-background-color: linear-gradient(to bottom right, #7f00ff, #e100ff);");
            inventario_activos.setStyle("-fx-background-color: transparent");
            saldo_activos.setStyle("-fx-background-color: transparent");
            agregar_empleado.setStyle("-fx-background-color: transparent");
            home_btn.setStyle("-fx-background-color: transparent");
            inventario_empleado.setStyle("-fx-background-color: transparent");
            empleado.setStyle("-fx-background-color: transparent");
            edificio.setStyle("-fx-background-color: transparent");

        }else if(event.getSource() == inventario_empleado){
            home_form.setVisible(false);
            bienes.setVisible(false);
            saldo_activos_form.setVisible(false);
            agregar_empleado_form.setVisible(false);
            inventarioglobal_form.setVisible(false);
            inventarioempleado_form.setVisible(true);
            empleado_form.setVisible(false);
            edificios_form.setVisible(false);

            inventario_empleado.setStyle("-fx-background-color: linear-gradient(to bottom right, #7f00ff, #e100ff);");
            inventario_activos.setStyle("-fx-background-color: transparent");
            saldo_activos.setStyle("-fx-background-color: transparent");
            agregar_empleado.setStyle("-fx-background-color: transparent");
            inventario_Global.setStyle("-fx-background-color: transparent");
            home_btn.setStyle("-fx-background-color: transparent");
            empleado.setStyle("-fx-background-color: transparent");
            edificio.setStyle("-fx-background-color: transparent");

        }else if(event.getSource() == empleado){
            home_form.setVisible(false);
            bienes.setVisible(false);
            saldo_activos_form.setVisible(false);
            agregar_empleado_form.setVisible(false);
            inventarioglobal_form.setVisible(false);
            inventarioempleado_form.setVisible(false);
            empleado_form.setVisible(true);
            edificios_form.setVisible(false);


            empleado.setStyle("-fx-background-color: linear-gradient(to bottom right, #7f00ff, #e100ff);");
            inventario_activos.setStyle("-fx-background-color: transparent");
            saldo_activos.setStyle("-fx-background-color: transparent");
            agregar_empleado.setStyle("-fx-background-color: transparent");
            inventario_Global.setStyle("-fx-background-color: transparent");
            home_btn.setStyle("-fx-background-color: transparent");
            inventario_empleado.setStyle("-fx-background-color: transparent");
            edificio.setStyle("-fx-background-color: transparent");


        }else if(event.getSource() == edificio){
            home_form.setVisible(false);
            bienes.setVisible(false);
            saldo_activos_form.setVisible(false);
            agregar_empleado_form.setVisible(false);
            inventarioglobal_form.setVisible(false);
            inventarioempleado_form.setVisible(false);
            empleado_form.setVisible(false);
            edificios_form.setVisible(true);



            edificio.setStyle("-fx-background-color: linear-gradient(to bottom right, #7f00ff, #e100ff);");
            inventario_activos.setStyle("-fx-background-color: transparent");
            saldo_activos.setStyle("-fx-background-color: transparent");
            agregar_empleado.setStyle("-fx-background-color: transparent");
            inventario_Global.setStyle("-fx-background-color: transparent");
            home_btn.setStyle("-fx-background-color: transparent");
            empleado.setStyle("-fx-background-color: transparent");
            inventario_empleado.setStyle("-fx-background-color: transparent");



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

    @FXML
    void Add(MouseEvent event) {
        String Literal = Literal_Activos.getText();
        String Descripcion = Descripcion_Activos.getText();
        String Renglon_Gasto = Renglon_Gasto_Activos.getText();
        Integer Renglon_gasto = Integer.parseInt(Renglon_Gasto);

        DatabaseConnection connectNow = new DatabaseConnection();
        String addActivo = "INSERT INTO usac_inventory.bienes(Literal, Descripcion, RenglonGasto) VALUES (?, ? , ?)";
        try (Connection connectDB = connectNow.getConnection();
             PreparedStatement preparedStatement = connectDB.prepareStatement(addActivo)){
            preparedStatement.setString(1, Literal);
            preparedStatement.setString(2, Descripcion);
            preparedStatement.setInt(3, Renglon_gasto);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
    }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Datos ingresados no validos");
        e.printStackTrace();
        }
        llenarTablaBienes();
    }

    @FXML
    void Clear(MouseEvent event) {
        Literal_Activos.setText("");
        Descripcion_Activos.setText("");
        Renglon_Gasto_Activos.setText("");
    }

    @FXML
    void Delete(MouseEvent event) {
        String Literal = JOptionPane.showInputDialog(null, "Ingrese la Literal a Eliminar: ", "Ingrese aquí el texto", JOptionPane.OK_CANCEL_OPTION);

        if (Literal == null || Literal.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return; // Sale del método sin hacer nada
        }
        else {
        DatabaseConnection connectNow = new DatabaseConnection();
        String addActivo = "DELETE FROM usac_inventory.bienes WHERE (Literal = ?)";
        try (Connection connectDB = connectNow.getConnection();
             PreparedStatement preparedStatement = connectDB.prepareStatement(addActivo)){
            preparedStatement.setString(1, Literal);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se han Eliminado los datos");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Datos ingresados no validos");
            e.printStackTrace();
        }
    }
        llenarTablaBienes();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumnResizePolicy(saldo_tableView);
        setColumnResizePolicy(addEmployee_tableView1);
        setColumnResizePolicy(addEmployee_tableView11);
        home_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #7f00ff, #e100ff);");
    }

    private void setColumnResizePolicy(TableView<?> tableView) {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
