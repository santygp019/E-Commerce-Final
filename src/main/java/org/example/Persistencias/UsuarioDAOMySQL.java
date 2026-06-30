package org.example.Persistencias;

import org.example.Datos.Usuario;
import org.example.Estados.EstadoUsuario;
import org.example.Estados.Rol;
import org.example.Exceptiones.DatosInvalidosException;
import org.example.Exceptiones.UsuarioNoEncontradoException;
import org.example.Interfaces.UsuarioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOMySQL implements UsuarioDAO {
    private Connection conexion;

    @Override
    public void guardar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, apellido, email, contrasena, fecha_alta, estado, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getContrasenia());
            ps.setDate(5, usuario.getFechaAlta());
            ps.setString(6, String.valueOf(usuario.getEstado()));
            ps.setString(7, String.valueOf(usuario.getRoles()));

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    usuario.setId(generatedKeys.getInt(1));
                }
            } catch (SQLException e) {
                if (e.getErrorCode() == 1062) {
                    throw new DatosInvalidosException("El Email '" + usuario.getEmail() + "' ya se encuentra registrado.");
                }
                throw new DatosInvalidosException("Error al guardar el usuario." + e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        Usuario usuario = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = extraerUsuarioDeResultSet(rs);
                } else {
                    throw new UsuarioNoEncontradoException("No se encontró usuario con ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new DatosInvalidosException("Error al buscar el usuario por ID." + e);
        }

        return usuario;
    }


    @Override
    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usuario = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = extraerUsuarioDeResultSet(rs);
                } else {
                    throw new UsuarioNoEncontradoException("No se encontró usuario con email: " + email);
                }
            }
        } catch (SQLException e) {
            throw new DatosInvalidosException("Error al buscar el usuario por email." + e);
        }

        return usuario;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                usuarios.add(extraerUsuarioDeResultSet(rs));
            }

        } catch (SQLException e) {
            throw new DatosInvalidosException("Error al obtener los usuarios." + e);
        }

        return usuarios;
    }

    @Override
    public void actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, contrasena = ?, estado = ?, rol = ? WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getContrasenia());
            ps.setString(5, String.valueOf(usuario.getEstado()));
            ps.setString(6, String.valueOf(usuario.getRoles()));
            ps.setInt(7, usuario.getId());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new UsuarioNoEncontradoException("No se pudo actualizar. Usuario ID: " + usuario.getId() + " no encontrado.");
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new DatosInvalidosException("Ya existe otro usuario con el email '" + usuario.getEmail() + "'." + e);
            }
            throw new DatosInvalidosException("Error al actualizar el usuario." + e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new UsuarioNoEncontradoException("No se pudo eliminar. Usuario ID: " + id + " no encontrado.");
            }

        } catch (SQLException e) {
            throw new DatosInvalidosException("Error al eliminar el usuario." + e);
        }
    }

    private Usuario extraerUsuarioDeResultSet(ResultSet rs) throws SQLException {
        String estadoDB = rs.getString("estado");
        String rolDB = rs.getString("rol");

        EstadoUsuario estadoEnum = EstadoUsuario.valueOf(estadoDB.toUpperCase());
        Rol rolEnum = Rol.valueOf(rolDB.toUpperCase());

        Usuario usuario = new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("contrasena"),
                rs.getDate("fecha_alta"),
                estadoEnum
        );
        usuario.setRoles(rolEnum);

        return usuario;
    }
}
