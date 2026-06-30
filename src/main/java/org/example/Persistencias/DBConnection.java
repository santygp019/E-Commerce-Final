package org.example.Persistencias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection instancia = null;

    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce_davinci?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    private DBConnection(){
    }

    public static Connection getConexion(){
        if (instancia == null){
            try {
                instancia = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                System.out.println("Conexion exitosa a la base de datos MySQL.");
            } catch (SQLException e){
                System.out.println("Error: No se pudo conectar a la base de datos.");
                System.out.println("Detalle del error: " + e.getMessage());
            }
        }
        return instancia;
    }
    public static void cerrarConexion(){
        if (instancia != null){
            try {
                instancia.close();
                System.out.println("Conexion a la base de datos cerrada correctamente");
            } catch (SQLException e) {
                throw new RuntimeException("Error al intentar cerrar la conexion: " + e.getMessage());
            }finally {
                instancia = null;
            }
        }
    }
}
