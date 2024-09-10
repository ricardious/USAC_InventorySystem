    package com.ricardious;

    import BDT.DatabaseConnection;
    import javafx.application.Application;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.stage.Stage;
    import javafx.stage.StageStyle;
    import java.sql.Connection;
    import java.sql.SQLException;



    public class Main extends Application {
        private double xOffset = 0;
        private double yOffset = 0;

        @Override
        public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);

            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });

            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
            // Intentar obtener una conexión a la base de datos
          /*  try {
                // Llamamos al método getConnection de DatabaseConnection
                Connection connection = DatabaseConnection.getConnection();

                // Si la conexión es exitosa, imprimimos un mensaje de éxito
                if (connection != null) {
                    System.out.println("La conexión a la base de datos fue exitosa.");
                } else {
                    System.out.println("No se pudo establecer conexión a la base de datos.");
                }

            } catch (SQLException e) {
                System.out.println("Ocurrió un error al conectar a la base de datos.");
                e.printStackTrace();
            }*/
        }

    }