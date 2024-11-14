package com.ricardious.controllers;

import com.ricardious.database.DatabaseConnection;
import com.ricardious.models.ActivoFijo;
import com.ricardious.models.EdificioFijo;
import com.ricardious.models.EmpleadobienesFijo;
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


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;








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
    private AnchorPane empleado_form;

    @FXML
    private AnchorPane edificios_form;

    @FXML
    private TableView<Map> edificiotabla;

    @FXML
    private TableColumn<?, ?> col_id_edificios;

    @FXML
    private TableColumn<?, ?> col_nombre_edificios;

    @FXML
    private TableColumn<?, ?> col_ubicacion_edificios;

    @FXML
    private TableColumn<?, ?> col_descripcion_edificios;

    @FXML
    private TableColumn<?, ?> col_seccion_edificios;

    @FXML
    private TextField agregar_idedificio;

    @FXML
    private TextField agregar_nombreedificios;

    @FXML
    private TextField agregar_ubicacion;

    @FXML
    private TextField agregar_descripcion;

    @FXML
    private TextField agregar_seccion;

    @FXML
    private Button limpiardatos_edificiosbt;

    @FXML
    private Button agregarinfo_edificiosbt;

    @FXML
    private Button eliminaredificio_bt;





    @FXML
    private AnchorPane inventarioempleado_form;

    @FXML
    private TableView<Map> inventarioempl_table;

    @FXML
    private TableColumn<?, ?> inventarioempleado_tarjeta;

    @FXML
    private TableColumn<?, ?> inventarioempleado_codigoactivo;

    @FXML
    private TableColumn<?, ?> inventarioempleado_desc;

    @FXML
    private TableColumn<?, ?> inventarioempleado_valor;

    @FXML
    private TableColumn<?, ?> inventarioempleado_registropersonal;

    @FXML
    private TableColumn<?, ?> inventarioempleado_nombre;

    @FXML
    private TableColumn<?, ?> inventarioempleado_activo;

    @FXML
    private TableColumn<?, ?> inventarioempleado_seccion;

    @FXML
    private TableColumn<?, ?> inventarioempleado_estado;

    @FXML
    private Button inventarioempl_cleanbtn;

    @FXML
    private Button inventarioempl_updatebtn;

    @FXML
    private Button inventarioempl_eliminarbtn;

    @FXML
    private TextField empleadotarjetafield;

    @FXML
    private TextField empleadocodigofield;

    @FXML
    private TextField empleadodescripfield;

    @FXML
    private TextField empleadovalorfield;

    @FXML
    private TextField empleadoregistrofield;

    @FXML
    private TextField empleadonombrefield;

    @FXML
    private TextField empleadoactivofield;

    @FXML
    private TextField empleadoseccionfield;

    @FXML
    private TextField empleadoestadofield;








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





    @FXML
    void importEXCELL(MouseEvent event) {

    }

    private String ColNumero = "Numero";
    private String ColNombreedificio = "Nombreedificio";
    private String ColDescripcionn = "Descripcion";
    private String ColUbicacion = "Ubicacion";
    private String ColSeccion = "Seccion";



    public ObservableList<Map> getEdificios(){
        var sql = "SELECT * FROM usac_inventory.edificios";
        ObservableList<Map> edificiosList = FXCollections.observableArrayList();
        try{
            DatabaseConnection connectNoww = new DatabaseConnection();
            PreparedStatement consultaa = connectNoww.getConnection().prepareStatement(sql);
            ResultSet resultSett = consultaa.executeQuery();
            while (resultSett.next()){
                EdificioFijo EdificioFijo = new EdificioFijo();
                Map<String, Object> coleccionn = new HashMap<>();
                EdificioFijo.setNumero(resultSett.getInt("Numero"));
                EdificioFijo.setNombreedificio(resultSett.getString("Nombreedificio"));
                EdificioFijo.setUbicacion(resultSett.getString("Descripcion"));
                EdificioFijo.setDescripcion(resultSett.getString("Ubicacion"));
                EdificioFijo.setSeccion(resultSett.getString("Seccion"));
                coleccionn.put(ColNumero, EdificioFijo.getNumero());
                coleccionn.put(ColNombreedificio, EdificioFijo.getNombreedificio());
                coleccionn.put(ColDescripcionn,  EdificioFijo.getUbicacion());
                coleccionn.put(ColUbicacion, EdificioFijo.getDescripcion());
                coleccionn.put(ColSeccion,  EdificioFijo.getSeccion());

                edificiosList.add(coleccionn);
            }
            resultSett.close();
            consultaa.close();

        } catch (Exception u) {
            throw new RuntimeException(u);
        }
        return edificiosList;
    }

    private void llenarTablaEdificios(){
        ObservableList<Map> listaa = getEdificios();
        this.col_id_edificios.setCellValueFactory(new MapValueFactory(ColNumero));
        this.col_nombre_edificios.setCellValueFactory(new MapValueFactory(ColNombreedificio));
        this.col_ubicacion_edificios.setCellValueFactory(new MapValueFactory(ColDescripcionn));
        this.col_descripcion_edificios.setCellValueFactory(new MapValueFactory(ColUbicacion));
        this.col_seccion_edificios.setCellValueFactory(new MapValueFactory(ColSeccion));
        

        this.edificiotabla.setItems(listaa);


    }





    @FXML
    void importtEXCELL(MouseEvent event) {

    }

    private String ColTarjeta = "Tarjeta";
    private String ColCodigoActivo = "CodigoActivo";
    private String ColDescripcionnn = "Descripcion";
    private String ColValor = "Valor";
    private String ColRegistropersonal = "Registropersonal";
    private String ColNombreempleado = "Nombreempleado";
    private String ColActivo = "Activo";
    private String ColSeccionn = "Seccionn";
    private String ColEstado = "Estado";



    public ObservableList<Map> getEmpleadobienes(){
        var sql = "SELECT * FROM usac_inventory.empleadobienes";
        ObservableList<Map> empleadobienesList = FXCollections.observableArrayList();
        try{
            DatabaseConnection connecttNow = new DatabaseConnection();
            PreparedStatement consullta = connecttNow.getConnection().prepareStatement(sql);
            ResultSet resulttSet = consullta.executeQuery();
            while (resulttSet.next()){
                EmpleadobienesFijo EmpleadobienesFijo = new EmpleadobienesFijo();
                Map<String, Object> colecccion = new HashMap<>();
                EmpleadobienesFijo.setTarjeta(resulttSet.getInt("Tarjeta"));
                EmpleadobienesFijo.setCodigoActivo(resulttSet.getString("CodigoActivo"));
                EmpleadobienesFijo.setDescripcion(resulttSet.getString("Descripcion"));
                EmpleadobienesFijo.setValor(resulttSet.getString("Valor"));
                EmpleadobienesFijo.setRegistropersonal(resulttSet.getString("Registropersonal"));
                EmpleadobienesFijo.setNombreempleado(resulttSet.getString("Nombreempleado"));
                EmpleadobienesFijo.setActivo(resulttSet.getString("Activo"));
                EmpleadobienesFijo.setSeccionn(resulttSet.getString("Seccionn"));
                EmpleadobienesFijo.setEstado(resulttSet.getString("Estado"));
                colecccion.put(ColTarjeta, EmpleadobienesFijo.getTarjeta());
                colecccion.put(ColCodigoActivo, EmpleadobienesFijo.getCodigoActivo());
                colecccion.put(ColDescripcionnn,  EmpleadobienesFijo.getDescripcion());
                colecccion.put(ColValor, EmpleadobienesFijo.getValor());
                colecccion.put(ColRegistropersonal,  EmpleadobienesFijo.getRegistropersonal());
                colecccion.put(ColNombreempleado, EmpleadobienesFijo.getNombreempleado());
                colecccion.put(ColActivo, EmpleadobienesFijo.getActivo());
                colecccion.put(ColSeccionn, EmpleadobienesFijo.getSeccionn());
                colecccion.put(ColEstado, EmpleadobienesFijo.getEstado());

                empleadobienesList.add(colecccion);
            }
            resulttSet.close();
            consullta.close();

        } catch (Exception a) {
            throw new RuntimeException(a);
        }
        return empleadobienesList;
    }

    private void llenarTablaEmpleadobienes(){
        ObservableList<Map> listta = getEmpleadobienes();
        this.inventarioempleado_tarjeta.setCellValueFactory(new MapValueFactory(ColTarjeta));
        this.inventarioempleado_codigoactivo.setCellValueFactory(new MapValueFactory(ColCodigoActivo));
        this.inventarioempleado_desc.setCellValueFactory(new MapValueFactory(ColDescripcionnn));
        this.inventarioempleado_valor.setCellValueFactory(new MapValueFactory(ColValor));
        this.inventarioempleado_registropersonal.setCellValueFactory(new MapValueFactory(ColRegistropersonal));
        this.inventarioempleado_nombre.setCellValueFactory(new MapValueFactory(ColNombreempleado));
        this.inventarioempleado_activo.setCellValueFactory(new MapValueFactory(ColActivo));
        this.inventarioempleado_seccion.setCellValueFactory(new MapValueFactory(ColSeccionn));
        this.inventarioempleado_estado.setCellValueFactory(new MapValueFactory(ColEstado));

        this.inventarioempl_table.setItems(listta);

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

            llenarTablaEmpleadobienes();


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

            llenarTablaEdificios();

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




    @FXML
    void Addd(MouseEvent event) {
        String Numero = agregar_idedificio.getText();
        Integer numero = Integer.parseInt(Numero);
        String Nombreedificio = agregar_nombreedificios.getText();
        String Ubicacion = agregar_ubicacion.getText();
        String Descripcion = agregar_descripcion.getText();
        String Seccion = agregar_seccion.getText();


        DatabaseConnection connectNoww = new DatabaseConnection();
        String addEdificio = "INSERT INTO usac_inventory.edificios(Numero, Nombreedificio, Ubicacion, Descripcion, Seccion) VALUES (?, ? , ?, ?, ? )";
        try (Connection connectDBs = connectNoww.getConnection();
             PreparedStatement preparedStatementt = connectDBs.prepareStatement(addEdificio)){
            preparedStatementt.setInt(1, numero);
            preparedStatementt.setString(2, Nombreedificio);
            preparedStatementt.setString(3, Ubicacion);
            preparedStatementt.setString(4, Descripcion);
            preparedStatementt.setString(5, Seccion);
            preparedStatementt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
        }
        catch (SQLException u) {
            JOptionPane.showMessageDialog(null, "Datos ingresados no validos");
            u.printStackTrace();
        }
        llenarTablaEdificios();
    }

    @FXML
    void Clearr(MouseEvent event) {
        agregar_idedificio.setText("");
        agregar_nombreedificios.setText("");
        agregar_ubicacion.setText("");
        agregar_descripcion.setText("");
        agregar_seccion.setText("");
    }

    @FXML
    void Deletee(MouseEvent event) {
        String Numero = JOptionPane.showInputDialog(null, "Ingrese el numero a Eliminar: ", "Ingrese aquí el texto", JOptionPane.OK_CANCEL_OPTION);

        if (Numero == null || Numero.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return; // Sale del método sin hacer nada
        }
        else {
            DatabaseConnection connectNoww = new DatabaseConnection();
            String addEdificio = "DELETE FROM usac_inventory.edificios WHERE (Numero = ?)";
            try (Connection connectDBs = connectNoww.getConnection();
                 PreparedStatement preparedStatementt = connectDBs.prepareStatement(addEdificio)){
                preparedStatementt.setString(1, Numero);
                preparedStatementt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se han Eliminado los datos");
            }
            catch (SQLException u) {
                JOptionPane.showMessageDialog(null, "Datos ingresados no validos");
                u.printStackTrace();
            }
        }
        llenarTablaEdificios();
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








    @FXML
    void AAdd(MouseEvent event) {
        String Tarjeta = empleadotarjetafield.getText();
        Integer tarejta = Integer.parseInt(Tarjeta);
        String CodigoActivo = empleadocodigofield.getText();
        String Descripcion = empleadodescripfield.getText();
        String Valor = empleadovalorfield.getText();
        String Registropersonal = empleadoregistrofield.getText();
        String Nombreempleado = empleadonombrefield.getText();
        String Activo = empleadoactivofield.getText();
        String Seccionn = empleadoseccionfield.getText();
        String Estado = empleadoestadofield.getText();





        DatabaseConnection connecttNow = new DatabaseConnection();
        String addEmpleadobienes = "INSERT INTO usac_inventory.empleadobienes(Tarjeta, CodigoActivo, Descripcion, Valor, Registropersonal, Nombreempleado, Activo, Seccionn, Estado) VALUES (?, ? , ?, ?, ?, ?, ?, ?, ? )";
        try (Connection connectDBs = connecttNow.getConnection();
             PreparedStatement preparedStatement = connectDBs.prepareStatement(addEmpleadobienes)){
            preparedStatement.setInt(1, tarejta);
            preparedStatement.setString(2, CodigoActivo);
            preparedStatement.setString(3, Descripcion);
            preparedStatement.setString(4, Valor);
            preparedStatement.setString(5, Registropersonal);
            preparedStatement.setString(6, Nombreempleado);
            preparedStatement.setString(7, Activo);
            preparedStatement.setString(8, Seccionn);
            preparedStatement.setString(9, Estado);



            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
        }
        catch (SQLException u) {
            JOptionPane.showMessageDialog(null, "Datos ingresados no validos");
            u.printStackTrace();
        }
        llenarTablaEdificios();
    }

    @FXML
    void Cllear(MouseEvent event) {
        empleadotarjetafield.setText("");
        empleadocodigofield.setText("");
        empleadodescripfield.setText("");
        empleadovalorfield.setText("");
        empleadoregistrofield.setText("");
        empleadonombrefield.setText("");
        empleadoactivofield.setText("");
        empleadoseccionfield.setText("");
        empleadoestadofield.setText("");

    }

    @FXML
    void Delette(MouseEvent event) {
        String Tarjeta = JOptionPane.showInputDialog(null, "Ingrese el tarejta a Eliminar: ", "Ingrese aquí el texto", JOptionPane.OK_CANCEL_OPTION);

        if (Tarjeta == null || Tarjeta.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return; // Sale del método sin hacer nada
        }
        else {
            DatabaseConnection connecttNow = new DatabaseConnection();
            String addEmpleadobienes = "DELETE FROM usac_inventory.empleadobienes WHERE (Tarjeta = ?)";
            try (Connection connectDBs = connecttNow.getConnection();
                 PreparedStatement preparedStatement = connectDBs.prepareStatement(addEmpleadobienes)){
                preparedStatement.setString(1, Tarjeta);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se han Eliminado los datos");
            }
            catch (SQLException u) {
                JOptionPane.showMessageDialog(null, "Datos ingresados no validos");
                u.printStackTrace();
            }
        }
        llenarTablaEmpleadobienes();
    }









    @FXML
    void exportToExcel(MouseEvent event) {
        ObservableList<Map> dataList = getEmpleadobienes();

        // Crea un archivo Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Empleadobienes");

            // Crear encabezados
            Row headerRow = sheet.createRow(0);
            String[] headers = {ColTarjeta, ColCodigoActivo, ColDescripcionnn, ColValor, ColRegistropersonal, ColNombreempleado, ColActivo, ColSeccionn, ColEstado};

            for (int i = 0; i < headers.length; i++) {
                org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Población de datos
            for (int i = 0; i < dataList.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Map<String, Object> dataMap = dataList.get(i);

                for (int j = 0; j < headers.length; j++) {
                    org.apache.poi.ss.usermodel.Cell cell = row.createCell(j);
                    Object value = dataMap.get(headers[j]);
                    if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof String) {
                        cell.setCellValue((String) value);
                    }
                }
            }

            // Guardar el archivo
            try (FileOutputStream fileOut = new FileOutputStream("empleadobienes.xlsx")) {
                workbook.write(fileOut);
            }
            System.out.println("Archivo exportado exitosamente");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void importFromExcel(MouseEvent event) {
        String filePath = "empleadobienes.xlsx"; // Ruta del archivo Excel

        try (FileInputStream file = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Connection connection = new DatabaseConnection().getConnection();
            String sql = "INSERT INTO usac_inventory.empleadobienes (Tarjeta, CodigoActivo, Descripcion, Valor, Registropersonal, Nombreempleado, Activo, Seccionn, Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            connection.setAutoCommit(false); // Iniciamos una transacción

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    try {
                        Integer tarjeta = (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.NUMERIC) ?
                                (int) row.getCell(0).getNumericCellValue() : null;
                        String codigoActivo = getCellValueAsString(row.getCell(1));
                        String descripcion = getCellValueAsString(row.getCell(2));
                        String valor = getCellValueAsString(row.getCell(3));
                        String registropersonal = getCellValueAsString(row.getCell(4));
                        String nombreempleado = getCellValueAsString(row.getCell(5));
                        String activo = getCellValueAsString(row.getCell(6));
                        String seccionn = getCellValueAsString(row.getCell(7));
                        String estado = getCellValueAsString(row.getCell(8));

                        if (tarjeta == null || codigoActivo.isEmpty() || descripcion.isEmpty()) {
                            System.out.println("Fila inválida en el índice " + i + ", se omite.");
                            continue; // Si faltan campos obligatorios, se omite esta fila
                        }

                        // Preparar y ejecutar la consulta SQL para insertar los datos
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, tarjeta);
                        preparedStatement.setString(2, codigoActivo);
                        preparedStatement.setString(3, descripcion);
                        preparedStatement.setString(4, valor);
                        preparedStatement.setString(5, registropersonal);
                        preparedStatement.setString(6, nombreempleado);
                        preparedStatement.setString(7, activo);
                        preparedStatement.setString(8, seccionn);
                        preparedStatement.setString(9, estado);

                        preparedStatement.executeUpdate();
                    } catch (Exception e) {
                        System.err.println("Error al procesar la fila " + i + ": " + e.getMessage());
                    }
                }
            }

            connection.commit(); // Confirmamos los cambios en la base de datos
            System.out.println("Datos importados exitosamente");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al importar los datos desde Excel: " + e.getMessage());
        }
    }

    // Método auxiliar para convertir el valor de una celda a String
    private String getCellValueAsString(org.apache.poi.ss.usermodel.Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString(); // Para fechas, puedes formatear esto según necesites
                } else {
                    return String.valueOf((int) cell.getNumericCellValue()); // Convertir a entero si es un número
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }















}
