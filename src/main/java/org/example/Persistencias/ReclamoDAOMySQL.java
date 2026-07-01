package org.example.Persistencias;

import org.example.Datos.OrdenCompra;
import org.example.Datos.Reclamo;
import org.example.Datos.Usuario;
import org.example.Estados.EstadoReclamo;
import org.example.Interfaces.OrdenCompraDAO;
import org.example.Interfaces.ReclamoDAO;
import org.example.Interfaces.UsuarioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReclamoDAOMySQL implements ReclamoDAO {
    private Connection conexion;
    private UsuarioDAO usuarioDAO;
    private OrdenCompraDAO ordenDAO;

    public ReclamoDAOMySQL(UsuarioDAO usuarioDAO, OrdenCompraDAO ordenDAO) {
        this.conexion = DBConnection.getConexion();
        this.usuarioDAO = usuarioDAO;
        this.ordenDAO = ordenDAO;
    }

    @Override
    public void guardar(Reclamo reclamo) {
        String sql = "INSERT INTO reclamos (usuario_id, orden_numero, motivo, fecha, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, reclamo.getCliente().getId());
            stmt.setInt(2, reclamo.getPedidoAsociado().getNumeroUnico());
            stmt.setString(3, reclamo.getMotivo());
            stmt.setObject(4, reclamo.getFecha());
            stmt.setString(5, reclamo.getEstado().name());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    reclamo.setNumero(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el reclamo en la base de datos.", e);
        }
    }

    @Override
    public Reclamo buscarPorNumero(int numero) {
        String sql = "SELECT * FROM reclamos WHERE numero = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario cliente = usuarioDAO.buscarPorId(rs.getInt("usuario_id"));
                    OrdenCompra orden = ordenDAO.buscarPorNumero(rs.getInt("orden_numero"));
                    return new Reclamo(
                            rs.getInt("numero"),
                            cliente,
                            orden,
                            rs.getString("motivo"),
                            rs.getTimestamp("fecha").toLocalDateTime(),
                            EstadoReclamo.valueOf(rs.getString("estado"))
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el reclamo.", e);
        }
        return null;
    }

    @Override
    public List<Reclamo> obtenerTodos() {
        List<Reclamo> reclamos = new ArrayList<>();
        String sql = "SELECT * FROM reclamos";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario cliente = usuarioDAO.buscarPorId(rs.getInt("usuario_id"));
                OrdenCompra orden = ordenDAO.buscarPorNumero(rs.getInt("orden_numero"));
                reclamos.add(new Reclamo(
                        rs.getInt("numero"),
                        cliente,
                        orden,
                        rs.getString("motivo"),
                        rs.getTimestamp("fecha").toLocalDateTime(),
                        EstadoReclamo.valueOf(rs.getString("estado"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener la lista de reclamos.", e);
        }
        return reclamos;
    }

    @Override
    public void actualizar(Reclamo reclamo) {
        String sql = "UPDATE reclamos SET motivo = ?, estado = ? WHERE numero = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, reclamo.getMotivo());
            stmt.setString(2, reclamo.getEstado().name());
            stmt.setInt(3, reclamo.getNumero());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el reclamo.", e);
        }
    }

    @Override
    public void eliminar(int numero) {
        String sql = "DELETE FROM reclamos WHERE numero = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, numero);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el reclamo.", e);
        }
    }
}
