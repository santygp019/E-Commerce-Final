package org.example.Interfaces;

import org.example.Datos.Producto;

import java.util.List;

public interface ProductoDAO {

    void guardar (Producto producto);
    Producto buscarPorId(String codigoUnico);
    List<Producto> obtenerTodos();
    void actualizar (Producto producto);
    void eliminar(String codigoUnico);

}
