package org.example.Datos;

import org.example.Estados.EstadoUsuario;
import org.example.Estados.Rol;

import java.sql.Date;
import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasenia;
    private LocalDateTime fechaDeAlta;
    private Rol roles;
    private EstadoUsuario estado;

    public Usuario(int id, String nombre, String apellido, String email, String contrasenia, LocalDateTime fechaDeAlta, EstadoUsuario estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.fechaDeAlta = fechaDeAlta;
        this.estado = estado;
    }

    public Usuario(String nombre, String apellido, String email, String contrasenia, LocalDateTime fechaDeAlta, EstadoUsuario estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasenia = contrasenia;
        this.fechaDeAlta = fechaDeAlta;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public LocalDateTime getFechaDeAlta() { return fechaDeAlta; }
    public void setFechaDeAlta(LocalDateTime fechaDeAlta) { this.fechaDeAlta = fechaDeAlta; }

    public Rol getRoles() { return roles; }
    public void setRoles(Rol roles) { this.roles = roles; }

    public EstadoUsuario getEstado() { return estado; }
    public void setEstado(EstadoUsuario estado) { this.estado = estado; }

}



