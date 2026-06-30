package org.example.Datos;

public class Calificacion {
    private int puntaje;
    private String comentario;

    public Calificacion(int puntaje, String comentario) {
        this.puntaje = puntaje;
        this.comentario = comentario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getComentario() {
        return comentario;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
