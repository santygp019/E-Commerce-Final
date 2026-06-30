package org.example.Datos;

import org.example.Estados.EstadoProducto;

public abstract class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precioBase;
    private int stock;
    private Categoria categoria;
    private EstadoProducto estado;

    // Constructor Completo
    public Producto(int id, String nombre, String descripcion, double precioBase, int stock, Categoria categoria, EstadoProducto estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.stock = stock;
        this.categoria = categoria;
        this.estado = estado;
    }

    // Método abstracto para cumplir con el Polimorfismo
    public abstract double calcularPrecioFinal();

    // Getters y Setters
    public int getId() {
        return id; }
    public void setId(int id) {
        this.id = id; }

    public String getNombre() {
        return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre; }

    public String getDescripcion() {
        return descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; }

    public double getPrecioBase() {
        return precioBase; }
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase; }

    public int getStock() {
        return stock; }
    public void setStock(int stock) {
        this.stock = stock; }

    public Categoria getCategoria() {
        return categoria; }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria; }

    public EstadoProducto getEstado() {
        return estado; }
    public void setEstado(EstadoProducto estado) {
        this.estado = estado; }

    @Override
    public String toString() {
        return nombre + " (Precio Base: $" + precioBase + " | Stock: " + stock + ")";
    }
}