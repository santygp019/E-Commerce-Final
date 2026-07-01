package org.example.Datos;

public class Calificacion {
    private int id;
    private Usuario cliente;
    private Producto producto;
    private int puntaje;
    private String comentario;

    public Calificacion(int id, Usuario cliente, Producto producto, int puntaje, String comentario) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        validarPuntaje(puntaje);
        this.puntaje = puntaje;
        this.comentario = comentario;
    }

    public Calificacion(Usuario cliente, Producto producto, int puntaje, String comentario) {
        this.cliente = cliente;
        this.producto = producto;
        validarPuntaje(puntaje);
        this.puntaje = puntaje;
        this.comentario = comentario;
    }

    private void validarPuntaje(int puntaje) {
        if (puntaje < 1 || puntaje > 5) {
            throw new IllegalArgumentException("El puntaje debe estar entre 1 y 5.");
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Usuario getCliente() { return cliente; }
    public void setCliente(Usuario cliente) { this.cliente = cliente; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getPuntaje() { return puntaje; }
    public void setPuntaje(int puntaje) {
        validarPuntaje(puntaje);
        this.puntaje = puntaje;
    }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
