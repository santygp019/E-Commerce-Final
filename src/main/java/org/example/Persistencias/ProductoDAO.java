package org.example.Persistencias;

import org.example.Datos.Producto;

import java.util.List;

public class ProductoDAO{
void guardar (Producto producto);
Producto buscarPorId(String codigoUnico);
List<Producto> obtenerTodos;
void actualizar (Producto producto);
void eliminar(String codigoUnico);

}


