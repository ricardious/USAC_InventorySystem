    package BDT;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;


    public class DatabaseConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/mi_base_datos"; // Cambia 'mi_base_datos' por el nombre de tu base de datos
        private static final String USER = "root"; // Cambia 'root' por tu usuario de MySQL
        private static final String PASSWORD = "tu_contraseña"; // Cambia 'tu_contraseña' por tu contraseña de MySQL

        public static Connection getConnection() throws SQLException {
            Connection connection = null;
            try {
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establecer la conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (ClassNotFoundException e) {
                System.out.println("Error al cargar el driver de MySQL.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos.");
                e.printStackTrace();
            }
            return connection;
        }
    }