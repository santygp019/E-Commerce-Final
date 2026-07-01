package org.example.Datos;

import java.time.LocalDateTime;
import java.util.Date;
import org.example.Estados.EstadoReclamo;

public class Reclamo {
    private int numero;
    private Usuario cliente;
    private OrdenCompra pedidoAsociado;
    private String motivo;
    private LocalDateTime fecha;
    private EstadoReclamo estado;

    public Reclamo(int numero, Usuario cliente, OrdenCompra pedidoAsociado, String motivo, LocalDateTime fecha, EstadoReclamo estado) {
        this.numero = numero;
        this.cliente = cliente;
        this.pedidoAsociado = pedidoAsociado;
        this.motivo = motivo;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Reclamo(Usuario cliente, OrdenCompra pedidoAsociado, String motivo, LocalDateTime fecha, EstadoReclamo estado) {
        this.cliente = cliente;
        this.pedidoAsociado = pedidoAsociado;
        this.motivo = motivo;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public Usuario getCliente() { return cliente; }
    public void setCliente(Usuario cliente) { this.cliente = cliente; }

    public OrdenCompra getPedidoAsociado() { return pedidoAsociado; }
    public void setPedidoAsociado(OrdenCompra pedidoAsociado) { this.pedidoAsociado = pedidoAsociado; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public EstadoReclamo getEstado() { return estado; }
    public void setEstado(EstadoReclamo estado) { this.estado = estado; }
}