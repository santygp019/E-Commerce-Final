package org.example.Datos;

import org.example.Estados.EstadoProducto;

import java.util.List;

public abstract class Producto  {
    private String codigoUnico;
    private String nombre;
    private String descripcion;
    private double precio;
    private double peso;
    private int Stock;
    private Categoria categorias;
    private EstadoProducto estado;
}
