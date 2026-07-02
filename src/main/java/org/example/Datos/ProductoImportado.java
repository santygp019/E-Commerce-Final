package org.example.Datos;

import org.example.Estados.EstadoProducto;

public class ProductoImportado extends Producto {
    private static final double ARANCEL = 1.21;

    public ProductoImportado(String codigoUnico, String nombre, String descripcion, double precio, Categoria categoria, int stock, double peso, EstadoProducto estado) {
        super(codigoUnico, nombre, descripcion, precio, categoria, stock, peso, estado);
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio() * ARANCEL;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("[Importado] " + getNombre() + " - Precio Final (c/Arancel): $" + calcularPrecioFinal() + " - Stock: " + getStock());
    }
}