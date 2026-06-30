package org.example.Datos;

import java.util.Date;

public class Pago {
    private int id;
    private double monto;
    private Date fecha;
    private boolean confirmado;
    private String metodoPago;

    public Pago(int id, double monto, String metodoPago) {
        this.id = id;
        this.monto = monto;
        this.fecha = new Date();
        this.confirmado = false;
        this.metodoPago = metodoPago;
    }

    public int getId() {
        return id; }
    public void setId(int id) {
        this.id = id; }

    public double getMonto() {
        return monto; }
    public void setMonto(double monto) {
        this.monto = monto; }

    public Date getFecha() {
        return fecha; }
    public void setFecha(Date fecha) {
        this.fecha = fecha; }

    public boolean isConfirmado() {
        return confirmado; }
    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado; }

    public String getMetodoPago() {
        return metodoPago; }
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago; }

    @Override
    public String toString() {
        return "Pago N°" + id + " - Monto: $" + monto + " [Confirmado: " + confirmado + "]";
    }
}