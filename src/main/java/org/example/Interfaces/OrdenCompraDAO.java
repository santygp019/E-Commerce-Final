package org.example.Interfaces;

import org.example.Datos.OrdenCompra;

import java.util.List;

public interface OrdenCompraDAO {
    void guardar(OrdenCompra orden);
    OrdenCompra buscarPorNumero(int numero);
    List<OrdenCompra> obtenerTodas();
    List<OrdenCompra> obtenerPorUsuario(int idUsuario);
}
