package org.example.Interfaces;

import org.example.Datos.Reclamo;

import java.util.List;

public interface ReclamoDAO {
    void guardar(Reclamo reclamo);
    Reclamo buscarPorNumero(int numero);
    List<Reclamo> obtenerTodos();
    void actualizar(Reclamo reclamo);
    void eliminar(int numero);
}
