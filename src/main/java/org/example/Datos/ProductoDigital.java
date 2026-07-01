package org.example.Datos;

import org.example.Estados.EstadoProducto;

public class ProductoDigital extends Producto {
    public ProductoDigital(String codigoUnico, String nombre, String descripcion, double precio, Categoria categoria, int stock, double peso, EstadoProducto estado) {
        super(codigoUnico, nombre, descripcion, precio, categoria, stock, peso, estado);
    }
    @Override
    public double calcularPrecioFinal() {
        return getPrecio();
    }
    @Override
    public void mostrarInformacion() {
        System.out.println("El producto " + getNombre() + " - Precio: $" + calcularPrecioFinal());
    }
}
