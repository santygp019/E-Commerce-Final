package org.example.Datos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.example.Estados.EstadoOrden;

public class OrdenCompra {
    private int numeroUnico;
    private Usuario cliente;
    private LocalDateTime fecha;
    private double total;
    private EstadoOrden estado;

    public OrdenCompra(int numeroUnico, Usuario cliente, LocalDateTime fecha, double total, EstadoOrden estado) {
        this.numeroUnico = numeroUnico;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }
    public OrdenCompra(Usuario cliente, LocalDateTime fecha, double total, EstadoOrden estado) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public int getNumeroUnico() { return numeroUnico; }
    public void setNumeroUnico(int numeroUnico) { this.numeroUnico = numeroUnico; }

    public Usuario getCliente() { return cliente; }
    public void setCliente(Usuario cliente) { this.cliente = cliente; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public EstadoOrden getEstado() { return estado; }
    public void setEstado(EstadoOrden estado) { this.estado = estado; }
}