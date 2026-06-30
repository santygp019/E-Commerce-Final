package org.example.Datos;

import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String contraseña;
    private LocalDateTime fechaDeAlta;
    private boolean activo;


    public Usuario(int id, String nombre, String apellido, String email, String contraseña, LocalDateTime fechaDeAlta, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contraseña = contraseña;
        this.fechaDeAlta = fechaDeAlta;
        this.activo = activo;

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id; }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public LocalDateTime getFechaDeAlta() {
        return fechaDeAlta;
    }
    public void setFechaDeAlta(LocalDateTime fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }







}



