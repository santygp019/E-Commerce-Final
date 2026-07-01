package org.example.Datos;

import org.example.Exceptiones.StockInsuficienteException;
import org.example.Exceptiones.DatosInvalidosException;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<String> historialMovimientos;

    public Inventario() {
        this.historialMovimientos = new ArrayList<>();
    }

    public void ingresoStock(Producto producto, int cantidad) throws DatosInvalidosException {
        if (cantidad <= 0) {
            throw new DatosInvalidosException("La cantidad a ingresar debe ser mayor a cero.");
        }
        producto.setStock(producto.getStock() + cantidad);
        registrarMovimiento("INGRESO - Producto: " + producto.getCodigoUnico() + " | Cantidad: " + cantidad);
    }

    public void egresoStock(Producto producto, int cantidad) throws StockInsuficienteException, DatosInvalidosException {
        if (cantidad <= 0) {
            throw new DatosInvalidosException("La cantidad de egreso debe ser mayor a cero.");
        }
        if (!producto.validarDisponibilidad(cantidad)) {
            throw new StockInsuficienteException("Stock insuficiente o producto inactivo para: " + producto.getNombre());
        }
        producto.setStock(producto.getStock() - cantidad);
        registrarMovimiento("EGRESO - Producto: " + producto.getCodigoUnico() + " | Cantidad: " + cantidad);
    }

    public void ajusteStock(Producto producto, int nuevoStock) throws DatosInvalidosException {
        if (nuevoStock < 0) {
            throw new DatosInvalidosException("El stock no puede ser negativo.");
        }
        int stockAnterior = producto.getStock();
        producto.setStock(nuevoStock);
        registrarMovimiento("AJUSTE - Producto: " + producto.getCodigoUnico() + " | Stock Anterior: " + stockAnterior + " -> Nuevo Stock: " + nuevoStock);
    }

    public int consultaStock(Producto producto) {
        return producto.getStock();
    }

    private void registrarMovimiento(String movimiento) {
        historialMovimientos.add(movimiento);
    }

    public List<String> getHistorialMovimientos() {
        return new ArrayList<>(historialMovimientos);
    }
}