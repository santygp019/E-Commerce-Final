package org.example.Persistencias;

import org.example.Datos.Calificacion;
import org.example.Datos.Producto;
import org.example.Datos.Usuario;
import org.example.Interfaces.CalificacionDAO;
import org.example.Interfaces.ProductoDAO;
import org.example.Interfaces.UsuarioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDAOMySQL implements CalificacionDAO {
    private Connection conexion;
    private UsuarioDAO usuarioDAO;
    private ProductoDAO productoDAO;

    public CalificacionDAOMySQL(UsuarioDAO usuarioDAO, ProductoDAO productoDAO) {
        this.conexion = DBConnection.getConexion();
        this.usuarioDAO = usuarioDAO;
        this.productoDAO = productoDAO;
    }

    @Override
    public void guardar(Calificacion calificacion) {
        String sql = "INSERT INTO calificaciones (usuario_id, producto_codigo, puntaje, comentario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, calificacion.getCliente().getId());
            stmt.setString(2, calificacion.getProducto().getCodigoUnico());
            stmt.setInt(3, calificacion.getPuntaje());
            stmt.setString(4, calificacion.getComentario());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    calificacion.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la calificación en la base de datos.", e);
        }
    }

    @Override
    public List<Calificacion> obtenerPorProducto(String codigoProducto) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM calificaciones WHERE producto_codigo = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, codigoProducto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Reconstruimos el usuario y el producto usando sus respectivos DAOs
                    Usuario cliente = usuarioDAO.buscarPorId(rs.getInt("usuario_id"));
                    Producto producto = productoDAO.buscarPorId(codigoProducto);

                    Calificacion calificacion = new Calificacion(
                            rs.getInt("id"),
                            cliente,
                            producto,
                            rs.getInt("puntaje"),
                            rs.getString("comentario")
                    );
                    calificaciones.add(calificacion);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las calificaciones del producto.", e);
        }

        return calificaciones;
    }

    @Override
    public List<Calificacion> obtenerPorUsuario(int idUsuario) {
        List<Calificacion> calificaciones = new ArrayList<>();
        String sql = "SELECT * FROM calificaciones WHERE usuario_id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario cliente = usuarioDAO.buscarPorId(idUsuario);
                    Producto producto = productoDAO.buscarPorId(rs.getString("producto_codigo"));

                    Calificacion calificacion = new Calificacion(
                            rs.getInt("id"),
                            cliente,
                            producto,
                            rs.getInt("puntaje"),
                            rs.getString("comentario")
                    );
                    calificaciones.add(calificacion);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las calificaciones del usuario.", e);
        }

        return calificaciones;
    }
}
