package com.svalero.proyectojunio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.svalero.proyectojunio.util.Constants.*;

public class Database {
    private Connection connection;

    public Connection getConnection() {
        try {
            Class.forName(ORACLE_DRIVER);
            connection = DriverManager.getConnection(ORACLE_URL, USERNAME, PASSWORD);
            System.out.println("Conectado.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("No se ha podido cargar el driver de conexión.");
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos.");
            sqle.printStackTrace();
        }

        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos.");
            sqle.printStackTrace();
        }
    }
}
