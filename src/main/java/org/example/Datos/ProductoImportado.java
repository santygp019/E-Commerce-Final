package org.example.Datos;
import org.example.Estados.EstadoProducto;

public class ProductoImportado extends Producto {
    private static final double ARANCEL = 1.21;

    public ProductoImportado(String codigo, String nombre, String desc, double precio, Categoria cat, int stock, double peso, EstadoProducto estado) {
        super(codigo, nombre, desc, precio, cat, stock, peso, estado);
    }

    @Override
    public double calcularPrecioFinal() {
        return getPrecio() * ARANCEL;
    }

    public void mostrarInformacion(){
        System.out.println("El tiene un  " + getNombre() + " - Precio Final (c/Arancel): $" + calcularPrecioFinal() + " - Stock: " + getStock());
    }
}