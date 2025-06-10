package org.israel.cookies.services.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    //Declaramos e inicializamos las variables de conexion
    private static String url = "jdbc:mysql://localhost:3306/compraventa?serverTimezone=UTC";
    private static String username = "root";
    private static String password = "";

    //implementamos un metodo de tipo Connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
