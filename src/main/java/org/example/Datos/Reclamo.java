package org.example.Datos;

import java.util.Date;
import org.example.Estados.EstadoReclamo;

public class Reclamo {
    private int numero;
    private String motivo;
    private Date fecha;
    private EstadoReclamo estado;
    private int numeroReclamo;
    private Usuario cliente;
    private OrdenCompra pedidoAsociado;

    public Reclamo(int numeroReclamo, Usuario cliente, OrdenCompra pedidoAsociado, String motivo, EstadoReclamo estado) {
        this.numeroReclamo = numeroReclamo;
        this.cliente = cliente;
        this.pedidoAsociado = pedidoAsociado;
        this.motivo = motivo;
        this.fecha = new Date();
        this.estado = estado;
    }

    public int getNumeroReclamo() {
        return numeroReclamo;
    }
    public void setNumeroReclamo(int numeroReclamo) {
    this.numeroReclamo = numeroReclamo; }

    public Usuario getCliente() {
    return cliente; }
    public void setCliente(Usuario cliente) {
    this.cliente = cliente; }

    public OrdenCompra getPedidoAsociado() {
    return pedidoAsociado; }
    public void setPedidoAsociado(OrdenCompra pedidoAsociado) {
    this.pedidoAsociado = pedidoAsociado; }

    public String getMotivo() {
    return motivo; }
    public void setMotivo(String motivo) {
    this.motivo = motivo; }

    public Date getFecha() {
    return fecha; }
    public void setFecha(Date fecha) {
    this.fecha = fecha; }

    public EstadoReclamo getEstado() {
    return estado; }
    public void setEstado(EstadoReclamo estado) {
    this.estado = estado; }

    @Override
    public String toString() {
        return "Reclamo N°" + numeroReclamo + " [" + estado + "] - Motivo: " + motivo;
    }
}