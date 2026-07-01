package org.example.Persistencias;

import org.example.Interfaces.*;
import org.example.Interfaces.DAOFactory;
import org.example.Interfaces.OrdenDAO;
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
    public OrdenDAO crearOrdenDAO() {
        return null;
    }

    @Override
    public CalificacionDAO crearCalificacionDAO() {
        return new CalificacionDAOMySQL(crearUsuarioDAO(), crearProductoDAO());
    }
}
