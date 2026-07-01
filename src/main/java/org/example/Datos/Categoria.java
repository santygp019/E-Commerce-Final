package org.example.Datos;

import org.example.Estados.EstadoCategoria;

public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;
    private EstadoCategoria estado;


    //Constructor con ID para que lo use el DAO
    public Categoria(int id, String nombre, String descripcion, EstadoCategoria estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    //Constructor Sin ID para que se pueda crear un categoria nueva y Mysql le asigne solo el ID con el autoincremental
    public Categoria(String nombre, String descripcion, EstadoCategoria estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setEstado(EstadoCategoria estado) {
        this.estado = estado;
    }

    public EstadoCategoria getEstado() {
        return estado;

    }

}



