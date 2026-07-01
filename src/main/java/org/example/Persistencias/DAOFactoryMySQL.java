package org.example.Persistencias;

import org.example.Interfaces.*;
import org.example.Interfaces.DAOFactory;
import org.example.Interfaces.OrdenCompraDAO;
import org.example.Interfaces.ProductoDAO;

public class DAOFactoryMySQL implements DAOFactory {

    @Override
    public UsuarioDAO crearUsuarioDAO() {
        return new UsuarioDAOMySQL();
    }

    @Override
    public CategoriaDAO crearCategoriaDAO() {
        return new CategoriaDAOMySQL();
    }

    @Override
    public ProductoDAO crearProductoDAO() {
        return null;
    }

    @Override
    public OrdenCompraDAO crearOrdenCompraDAO() {
        return null;
    }

    @Override
    public ReclamoDAO crearReclamoDAO() {
        return new ReclamoDAOMySQL(crearUsuarioDAO(), crearOrdenCompraDAO());
    }

    @Override
    public CalificacionDAO crearCalificacionDAO() {
        return new CalificacionDAOMySQL(crearUsuarioDAO(), crearProductoDAO());
    }
}
