package org.example.Interfaces;

import org.example.Datos.Categoria;
import org.example.Exceptiones.CategoriaNoEncontradaException;

import java.util.List;

public interface CategoriaDAO {

    void guardar(Categoria categoria);
    Categoria buscarPorId(int id) throws CategoriaNoEncontradaException;
    List<Categoria> obtenerTodas();
    void actualizar(Categoria categoria);
    void eliminar(int id);


}
