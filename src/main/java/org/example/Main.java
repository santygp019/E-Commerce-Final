package org.example;


import org.example.Datos.Usuario;
import org.example.Estados.EstadoUsuario;
import org.example.Estados.Rol;
import org.example.Interfaces.DAOFactory;
import org.example.Interfaces.UsuarioDAO;
import org.example.Persistencias.DAOFactoryMySQL;
import org.example.Persistencias.DBConnection;

import java.time.LocalDateTime;

public class Main {
    static void main() {
        System.out.println("Iniciando Sistema E-Commerce...");

        try{
            DBConnection.getConexion();
        } catch (RuntimeException e) {
            System.out.println("El sistema no puede iniciar debido a un problema con la base de datos.");
            System.out.println("Por Favor, contacte al Admin.");
        }
        DAOFactory factory = new DAOFactoryMySQL();

        UsuarioDAO usuarioDAO = factory.crearUsuarioDAO();

        Usuario nuevoUsuario = new Usuario("Leonel", "Messi", "leo.messi@davinci.edu.ar", "campeon123", LocalDateTime.now(), EstadoUsuario.ACTIVO);

        nuevoUsuario.setRoles(Rol.CLIENTE);

        usuarioDAO.guardar(nuevoUsuario);
        System.out.println("¡Usuario insertado en MySQL exitosamente!");
    }
}
