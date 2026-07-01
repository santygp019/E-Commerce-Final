package org.example;


import org.example.Datos.Categoria;
import org.example.Datos.Usuario;
import org.example.Estados.EstadoCategoria;
import org.example.Estados.EstadoUsuario;
import org.example.Estados.Rol;
import org.example.Interfaces.CategoriaDAO;
import org.example.Interfaces.DAOFactory;
import org.example.Interfaces.UsuarioDAO;
import org.example.Persistencias.CategoriaDAOMySQL;
import org.example.Persistencias.DAOFactoryMySQL;
import org.example.Persistencias.DBConnection;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    static void main() {
        System.out.println("Iniciando Sistema E-Commerce...");
    /*
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
        */

        // prueba de categoria en la base de datos

        CategoriaDAO categoriaDAO = new CategoriaDAOMySQL();
        System.out.println("--- prueba de guardado");
        Categoria nuevaCategoria = new Categoria("Placas de Video","Nvidia / AMD", EstadoCategoria.ACTIVA);
        Categoria procesadores = new Categoria("Procesadores", "Intel / amd ", EstadoCategoria.ACTIVA);
        // aca guardamos la nueva categoria en la base de datos

        categoriaDAO.guardar(nuevaCategoria);
        categoriaDAO.guardar(procesadores);


        System.out.println("\n Prueba de lectura ");
        List<Categoria> listaDesdeDB = categoriaDAO.obtenerTodas();

        for (Categoria c :listaDesdeDB){
            System.out.println("ID " + c.getId() +
                    " Nombre " + c.getNombre() +
                    " Estado " + c.getEstado());
        }



    }
}
