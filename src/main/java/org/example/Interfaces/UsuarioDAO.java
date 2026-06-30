package org.example.Interfaces;

import org.example.Datos.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void guardar(Usuario usuario);
    Usuario buscarPorId(int id);
    Usuario buscarPorEmail(String email);
    List<Usuario> obtenerTodos();
    void actualizar(Usuario usuario);
    void eliminar(int id);
}
