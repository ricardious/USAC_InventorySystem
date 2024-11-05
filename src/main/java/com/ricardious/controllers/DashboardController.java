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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class DashboardController implements Initializable {

    @FXML
    private TextField Search_Bienes;

    @FXML
    private Button addEmployee_addBtn;

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
    private ComboBox<?> addEmployee_gender11;

    @FXML
    private ImageView addEmployee_image;

    @FXML
    private ImageView addEmployee_image11;

    @FXML
    private Button addEmployee_importBtn;

    @FXML
    private Button addEmployee_importBtn11;

    @FXML
    private TextField addEmployee_lastName;

    @FXML
    private TextField addEmployee_lastName11;

    @FXML
    private TextField addEmployee_phoneNum;

    @FXML
    private TextField addEmployee_phoneNum111;

    @FXML
    private ComboBox<?> addEmployee_position;

    @FXML
    private TextField addEmployee_search;

    @FXML
    private TableView<?> addEmployee_tableView;

    @FXML
    private Button addEmployee_updateBtn;

    @FXML
    private TextField saldo_empleadoID;

    @FXML
    private Label saldo_nombre;

    @FXML
    private AnchorPane inventoryactivos_form;


    @FXML
    private AnchorPane main_form;





    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Button maximize;




    @FXML
    private Label username;

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
    private Label home_totalEmployees;

    @FXML
    private Label home_totalPresents;

    @FXML
    private Label home_totalInactiveEm;

    @FXML
    private BarChart<?, ?> home_chart;



    @FXML
    private AnchorPane inventarioempleado_form;

    @FXML
    private TextField inventarioempl_codigo;

    @FXML
    private TableView<?> inventarioempl_tableView1;

    @FXML
    private TableColumn<?, ?> inventarioempleo_tarjeta;

    @FXML
    private TableColumn<?, ?> inventarioempleo_activo;

    @FXML
    private TableColumn<?, ?> inventarioempleo_desc;

    @FXML
    private TableColumn<?, ?> inventarioempleo_estado;

    @FXML
    private TableColumn<?, ?> inventarioempleo_condicion;

    @FXML
    private Button inventarioempl_cleanbtn;

    @FXML
    private Button inventarioempl_updatebtn;

    @FXML
    private Button inventarioempl_eliminarbtn;



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
    private TextField addEmployee_employeeID11;

    @FXML
    private TextField addEmployee_firstName11;

    @FXML
    private ComboBox<?> addEmployee_position11;

    @FXML
    private Button addEmployee_clearBtn11;

    @FXML
    private Button addEmployee_deleteBtn11;

    @FXML
    private Button addEmployee_updateBtn11;

    @FXML
    private Button addEmployee_addBtn11;





    @FXML
    private AnchorPane inventarioglobal_form;

    @FXML
    private TableView<?> inventarioglobal_tableView;

    @FXML
    private TableColumn<?, ?> global_col_tarjeta;

    @FXML
    private TableColumn<?, ?> global_col_codgo;

    @FXML
    private TableColumn<?, ?> global_col_desc;

    @FXML
    private TableColumn<?, ?> global_col_ubi;

    @FXML
    private TableColumn<?, ?> global_col_dueño;

    @FXML
    private TableColumn<?, ?> global_col_costo;

    @FXML
    private TableColumn<?, ?> global_col_estado;

    @FXML
    private TableColumn<?, ?> global_col_condicion;

    @FXML
    private Label inftotal;

    @FXML
    private Label totalinvent;

    @FXML
    private Button total_btn;

    @FXML
    private Button inventarioglobal_cleanbtn1;

    @FXML
    private Button inventarioglobal_updatebtn1;

    @FXML
    private Button inventarioglobal_eliminarbtn1;



    @FXML
    private AnchorPane agregar_empleado_form;

    @FXML
    private TextField agregarempleado_search;

    @FXML
    private TableView<?> addEmployee_tableView1;

    @FXML
    private TableColumn<?, ?> agregarempl_col_tarjeta;

    @FXML
    private TableColumn<?, ?> agregarempl_col_codigo;

    @FXML
    private TableColumn<?, ?> agregarempl_col_desc;

    @FXML
    private TableColumn<?, ?> agregarempl_col_ubic;

    @FXML
    private TableColumn<?, ?> agregarempl_col_dueño;

    @FXML
    private TableColumn<?, ?> agregarempl_col_estado;

    @FXML
    private TableColumn<?, ?> agregarempl_col_condicion;

    @FXML
    private TextField agregarempl_tarjeta;

    @FXML
    private TextField agregarempl_codigo;

    @FXML
    private TextField agregarempl_desc;

    @FXML
    private ComboBox<?> agregarempl_ubic;

    @FXML
    private TextField agregarempl_dueño;

    @FXML
    private ComboBox<?> agregarempl_estado;

    @FXML
    private TextField agregarempl_condicion;

    @FXML
    private ImageView agregarempl_image1;

    @FXML
    private Button agregarempl_importBtn1;

    @FXML
    private Button agregarempl_clearBtn1;

    @FXML
    private Button agregarempl_deleteBtn1;

    @FXML
    private Button agregarempl_updateBtn1;

    @FXML
    private Button agregarempl_addBtn1;





    @FXML
    private AnchorPane saldo_activos_form;

    @FXML
    private Label saldo_apellido;

    @FXML
    private Label saldo_cargo;

    @FXML
    private TextField saldo_saldoactivos;

    @FXML
    private TableView<?> saldo_tableView;

    @FXML
    private Button saldo_updateBtn;

    @FXML
    private Button saldo_clearBtn;

    @FXML
    private TableColumn<?, ?> saldo_col_employeeID;

    @FXML
    private TableColumn<?, ?> saldo_col_firstName;

    @FXML
    private TableColumn<?, ?> saldo_col_lastName;

    @FXML
    private TableColumn<?, ?> saldo_col_position;

    @FXML
    private TableColumn<?, ?> saldo_col_salary;



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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumnResizePolicy(saldo_tableView);
        setColumnResizePolicy(addEmployee_tableView1);
        setColumnResizePolicy(addEmployee_tableView11);
    }

    private void setColumnResizePolicy(TableView<?> tableView) {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
