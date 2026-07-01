package org.example.Datos;

import java.util.Date;

public class Devolucion {
    private Usuario cliente; // Suponiendo que usás tu clase Usuario para los clientes
    private Producto producto;
    private String motivo;
    private Date fecha;
    private String estado;

    public Devolucion(Usuario cliente, Producto producto, String motivo) {
        this.cliente = cliente;
        this.producto = producto;
        this.motivo = motivo;
        this.fecha = new Date();
        this.estado = "PENDIENTE";
    }

    public void registrarDevolucion() {
        System.out.println("Devolución registrada para el cliente " + cliente.getNombre() + " por el producto " + producto.getNombre());
    }


    public Usuario getCliente() { return cliente; }
    public Producto getProducto() { return producto; }
    public String getMotivo() { return motivo; }
    public Date getFecha() { return fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}