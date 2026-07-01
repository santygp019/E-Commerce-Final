package org.example.Interfaces;

import org.example.Datos.Calificacion;

import java.util.List;

public interface CalificacionDAO {
    void guardar(Calificacion calificacion);
    List<Calificacion> obtenerPorProducto(String codigoProducto);
    List<Calificacion> obtenerPorUsuario(int idUsuario);
}
