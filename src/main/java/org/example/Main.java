package org.example;


import org.example.Persistencias.DBConnection;

public class Main {
    static void main() {
        System.out.println("Iniciando Sistema E-Commerce...");

        try{
            DBConnection.getConexion();
        } catch (RuntimeException e) {
            System.out.println("El sistema no puede iniciar debido a un problema con la base de datos.");
            System.out.println("Por Favor, contacte al Admin.");
        }finally {
            DBConnection.cerrarConexion();
        }
    }
}
