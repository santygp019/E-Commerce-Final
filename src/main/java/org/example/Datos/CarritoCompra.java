package org.example.Datos;

import org.example.Exceptiones.CarritoVacioException;

import java.util.ArrayList;
import java.util.List;

public class CarritoCompra {
    private Usuario cliente;
    private List<ItemCarrito> items;

    public CarritoCompra(Usuario cliente) {
        this.cliente = cliente;
        this.items = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getCodigoUnico().equals(producto.getCodigoUnico())) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void eliminarProducto(String codigoProducto) {
        items.removeIf(item -> item.getProducto().getCodigoUnico().equals(codigoProducto));
    }

    public void modificarCantidad(String codigoProducto, int nuevaCantidad) {
        for (ItemCarrito item : items) {
            if (item.getProducto().getCodigoUnico().equals(codigoProducto)) {
                if (nuevaCantidad <= 0) {
                    eliminarProducto(codigoProducto);
                } else {
                    item.setCantidad(nuevaCantidad);
                }
                return;
            }
        }
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public double calcularTotal() {
        if (items.isEmpty()) {
            throw new CarritoVacioException("No se puede calcular el total porque el carrito esta vacio.");
        }
        double total = 0.0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void visualizarCarrito() {
        if (items.isEmpty()) {
            System.out.println("El carrito esta vacio.");
            return;
        }
        System.out.println("CARRITO DE COMPRAS DE " + cliente.getNombre());
        for (ItemCarrito item : items) {
            System.out.println("- " + item.getProducto().getNombre() + "| Cantidad: " + item.getCantidad() + "| Precio U: $" + item.getPrecioUnitario() + "| Subtotal: $" + item.getSubtotal());
        }
        System.out.println("TOTAL A PAGAR: $" + calcularTotal());
    }
}
