package org.example.Datos;

import java.util.Date;

public class Devolucion {
    private int id;
    private Usuario cliente;
    private Producto producto;
    private String motivo;
    private Date fecha;


    public Devolucion(int id, Usuario cliente, Producto producto, String motivo) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.motivo = motivo;
        this.fecha = new Date();

    }

    public int getId() {
        return id; }
    public void setId(int id) {
        this.id = id; }

    public Usuario getCliente() {
        return cliente; }
    public void setCliente(Usuario cliente) {
        this.cliente = cliente; }

    public Producto getProducto() {
        return producto; }
    public void setProducto(Producto producto) {
        this.producto = producto; }

    public String getMotivo() {
        return motivo; }
    public void setMotivo(String motivo) {
        this.motivo = motivo; }

    public Date getFecha() {
        return fecha; }
    public void setFecha(Date fecha) {
        this.fecha = fecha; }


    @Override
    public String toString() {
        return "Devolucion N°" + id + " - Producto: " + producto.getNombre() + " ";
    }
}