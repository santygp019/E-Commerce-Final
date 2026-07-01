package org.example.Datos;

import org.example.Estados.EstadoProducto;

public abstract class Producto {
    private String codigoUnico;
    private String nombre;
    private String descripcion;
    private double precio;
    private Categoria categoria;
    private int stock;
    private double peso;
    private EstadoProducto estado;

    public Producto(String codigoUnico, String nombre, String descripcion, double precio, Categoria categoria, int stock, double peso, EstadoProducto estado) {
        this.codigoUnico = codigoUnico;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.peso = peso;
        this.estado = estado;
    }


    public abstract double calcularPrecioFinal();
    public abstract void mostrarInformacion();

    public boolean validarDisponibilidad(int cantidad) {
        return this.stock >= cantidad && this.estado == EstadoProducto.Activo;
    }

    public void aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 100) {
            this.precio -= this.precio * (porcentaje / 100);
        }
    }


    public String getCodigoUnico() {
        return codigoUnico; }
    public String getNombre() {
        return nombre; }
    public double getPrecio() {
        return precio; }
    public int getStock() {
        return stock; }
    public void setStock(int stock) {
        this.stock = stock; }
    public EstadoProducto getEstado() {
        return estado; }
    public void setEstado(EstadoProducto estado) {
        this.estado = estado; }
}