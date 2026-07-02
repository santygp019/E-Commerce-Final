package org.example.Interfaces;

import org.example.Datos.Envio;

import java.util.List;

public interface EnvioDAO {

    void guardar(Envio envio);
    Envio buscarPorCodigo(String codigoSeguimiento);
    List<Envio> obtenerTodos();
    void actualizar(Envio envio);
    void eliminar(String  codigoSeguimiento);
}
