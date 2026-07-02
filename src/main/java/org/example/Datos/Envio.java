package org.example.Datos;

import org.example.Estados.EstadoEnvio;
import org.example.Estados.TipoEnvio;

public class Envio {
    private int id;
    private String codigoSeguimiento;
    private String direccion;
    private String provincia;
    private String ciudad;
    private String codigoPostal;
    private TipoEnvio tipoEnvio;
    private EstadoEnvio estado;
    private double costo;

    public Envio() {
    }

    public Envio(int id, String codigoSeguimiento, String direccion, String provincia, String ciudad, String codigoPostal, TipoEnvio tipoEnvio, EstadoEnvio estado, double costo) {
        this.id = id;
        this.codigoSeguimiento = codigoSeguimiento;
        this.direccion = direccion;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.tipoEnvio = tipoEnvio;
        this.estado = estado;
        this.costo = costo;
    }

    // construcor sin Id para que la base de datos lo cree automaticamente
    public Envio(String codigoSeguimiento, String direccion, String provincia, String ciudad, String codigoPostal, TipoEnvio tipoEnvio, EstadoEnvio estado, double costo) {
        this.codigoSeguimiento = codigoSeguimiento;
        this.direccion = direccion;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.tipoEnvio = tipoEnvio;
        this.estado = estado;
        this.costo = costo;
    }



    public String getCodigoSeguimiento() {
        return codigoSeguimiento;
    }

    public void setCodigoSeguimiento(String codigoSeguimiento) {
        this.codigoSeguimiento = codigoSeguimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public TipoEnvio getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoEnvio tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Envio " + codigoSeguimiento + " [" + estado + "]";
    }
}