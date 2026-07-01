package org.example.Datos;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }

    public double getSubtotal() {return this.precioUnitario * this.cantidad;}

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " ($" + getSubtotal() + ")";
    }
}