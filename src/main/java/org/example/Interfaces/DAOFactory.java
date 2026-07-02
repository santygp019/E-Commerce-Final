package org.example.Interfaces;

public interface DAOFactory {
    UsuarioDAO crearUsuarioDAO();
    CategoriaDAO crearCategoriaDAO();
    ProductoDAO crearProductoDAO();
    OrdenCompraDAO crearOrdenCompraDAO();
    ReclamoDAO crearReclamoDAO();
    CalificacionDAO crearCalificacionDAO();
}
