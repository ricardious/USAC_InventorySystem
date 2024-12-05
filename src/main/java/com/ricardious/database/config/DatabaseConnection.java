package com.ricardious.database.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handles the database connection configuration.
 * It reads the database connection properties from a configuration file
 * and establishes a connection to the database.
 */
public class DatabaseConnection {

    // Logger instance for logging database connection events and errors
    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());

    /**
     * Establishes a connection to the database using properties from a configuration file.
     *
     * @return a Connection object to the database, or null if the connection fails
     */
    public Connection getConnection() {
        Connection databaseLink = null;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            // Load properties file
            Properties props = new Properties();
            if (input == null) {
                LOGGER.log(Level.SEVERE, "Sorry, unable to find config.properties");
                return null;
            }
            props.load(input);

            // Retrieve properties
            String databaseName = props.getProperty("dbName");
            String databaseUser = props.getProperty("dbUser");
            String databasePassword = props.getProperty("dbPassword");
            String databaseHost = props.getProperty("dbHost");
            String url = "jdbc:mysql://" + databaseHost + "/" + databaseName;

            // Connect to database
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Database connection error", e);
        }

        return databaseLink;
    }
}
