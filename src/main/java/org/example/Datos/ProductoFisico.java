package org.example.Datos;

import org.example.Estados.EstadoProducto;

public class ProductoFisico extends Producto {

    public ProductoFisico(String codigoUnico, String nombre, String descripcion, double precio, Categoria categoria, int stock, double peso, EstadoProducto estado) {
        super(codigoUnico, nombre, descripcion, precio, categoria, stock, peso, estado);
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio();
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("[Físico] " + getNombre() + " - Peso: " + getPeso() + "kg - Precio: $" + calcularPrecioFinal() + " - Stock: " + getStock());
    }
}