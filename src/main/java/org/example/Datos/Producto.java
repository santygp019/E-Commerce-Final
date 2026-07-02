package org.example.Datos;

import org.example.Estados.EstadoProducto;
import org.example.Exceptiones.DatosInvalidosException;


public abstract class Producto {
    private String codigoUnico;
    private String nombre;
    private String descripcion;
    private double precio;
    private Categoria categoria;
    private int stock;
    private double peso;
    private EstadoProducto estado;
    private final double precioOriginal;

    public Producto(String codigoUnico, String nombre, String descripcion, double precio, Categoria categoria, int stock, double peso, EstadoProducto estado) {
        if (codigoUnico == null || codigoUnico.isBlank()) {
            throw new DatosInvalidosException("El código único no puede estar vacío.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new DatosInvalidosException("El nombre no puede estar vacío.");
        }
        if (precio <= 0) {
            throw new DatosInvalidosException("El precio debe ser mayor a cero.");
        }
        if (categoria == null) {
            throw new DatosInvalidosException("El producto debe tener una categoría asignada.");
        }
        if (stock < 0) {
            throw new DatosInvalidosException("El stock no puede ser negativo.");
        }

        this.codigoUnico = codigoUnico;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.peso = peso;
        this.estado = estado;
        this.precioOriginal = precio;

    }

    public abstract double calcularPrecioFinal();
    public abstract void mostrarInformacion();

    public boolean validarDisponibilidad(int cantidad) {
        return this.stock >= cantidad && this.estado == EstadoProducto.Activo;
    }


    public void aplicarDescuento(double porcentaje) throws DatosInvalidosException {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new DatosInvalidosException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        this.precio = this.precioOriginal - (this.precioOriginal * (porcentaje / 100)); // resta el %, no lo reemplaza
    }
    public void quitarDescuento(){
        this.precio = this.precioOriginal;
    }
    public String getCodigoUnico() { return codigoUnico; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public Categoria getCategoria() { return categoria; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public double getPeso() { return peso; }
    public EstadoProducto getEstado() { return estado; }
    public void setEstado(EstadoProducto estado) { this.estado = estado; }
    public double getPrecioOriginal() { return precioOriginal; }
}