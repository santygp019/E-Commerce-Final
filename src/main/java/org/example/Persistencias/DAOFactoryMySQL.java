package org.example.Persistencias;

import org.example.Interfaces.DAOFactory;
import org.example.Interfaces.UsuarioDAO;

public class DAOFactoryMySQL implements DAOFactory {

    @Override
    public UsuarioDAO crearUsuarioDAO() {
        return new UsuarioDAOMySQL();
    }
}
