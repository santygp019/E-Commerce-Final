package org.example.Datos;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }


    public void agregarItem(ItemCarrito item) {
        this.items.add(item);
    }

    public void vaciarCarrito() {
        this.items.clear();
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public List<ItemCarrito> getItems() { return items; }
    public void setItems(List<ItemCarrito> items) { this.items = items; }

    @Override
    public String toString() {
        return "Carrito (Items: " + items.size() + " | Total: $" + calcularTotal() + ")";
    }
}