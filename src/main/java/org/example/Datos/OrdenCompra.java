package org.example.Datos;

import java.util.Date;
import java.util.List;
import org.example.Estados.EstadoOrden;

public class OrdenCompra {
    private int numeroUnico;
    private Usuario cliente;
    private Date fecha;
    private List<ItemCarrito> productos;
    private double total;
    private EstadoOrden estado;

    public OrdenCompra(int numeroUnico, Usuario cliente, List<ItemCarrito> productos, double total, EstadoOrden estado) {
        this.numeroUnico = numeroUnico;
        this.cliente = cliente;
        this.fecha = new Date();
        this.productos = productos;
        this.total = total;
        this.estado = estado;
    }

    public int getNumeroUnico() {
        return numeroUnico; }
    public void setNumeroUnico(int numeroUnico) {
        this.numeroUnico = numeroUnico; }

    public Usuario getCliente() {
        return cliente; }
    public void setCliente(Usuario cliente) {
        this.cliente = cliente; }

    public Date getFecha() {
        return fecha; }
    public void setFecha(Date fecha) {
        this.fecha = fecha; }

    public List<ItemCarrito> getProductos() {
        return productos; }
    public void setProductos(List<ItemCarrito> productos) {
        this.productos = productos; }

    public double getTotal() {
        return total; }
    public void setTotal(double total) {
        this.total = total; }

    public EstadoOrden getEstado() {
        return estado; }
    public void setEstado(EstadoOrden estado) {
        this.estado = estado; }

    @Override
    public String toString() {
        return "Orden N°" + numeroUnico + " [" + estado + "] - Total: $" + total;
    }
}