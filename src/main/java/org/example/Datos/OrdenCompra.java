package org.example.Datos;

import org.example.Estados.EstadoOrden;

import java.time.LocalDateTime;
import java.util.List;

public class OrdenCompra {
    private int numeroUnico;
    private Usuario cliente;
    private LocalDateTime fecha;
    private List<Producto> productos;
    private int total;
    private EstadoOrden estados;
    private String pagoAsociado;
    private Envio envioAsciado;
}
