package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String URL = "jdbc:mysql://localhost:3306/escena_del_crimen";
    private static final String USER = "root";
    private static final String PASSWORD = "root";


    private static Connection connection;

    public static Connection innitConnection() {
        try {
            System.out.println("Estoy aquí");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conecxión BBDD exitosa.");
        } catch (SQLException exception) {
            System.out.println("Conexión fallida: "+exception.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Conexión cerrada con éxito");
        } catch (SQLException exception) {
            throw new RuntimeException("Excepción al cerrar la BBDD" + exception);
        }
    }
}