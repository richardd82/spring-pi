package com.henry.pijava.Connections;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class ConnDB {

    private static final String URL = "jdbc:h2:tcp://localhost/~/expensDB";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private static Connection connection;

    private ConnDB() {
        // Evita que se cree una instancia de la clase
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
