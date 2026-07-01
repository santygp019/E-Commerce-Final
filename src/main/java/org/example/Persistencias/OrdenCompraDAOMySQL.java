package org.example.Persistencias;

import org.example.Datos.OrdenCompra;
import org.example.Datos.Usuario;
import org.example.Estados.EstadoOrden;
import org.example.Interfaces.OrdenCompraDAO;
import org.example.Interfaces.UsuarioDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompraDAOMySQL implements OrdenCompraDAO {
    private Connection conexion;
    private UsuarioDAO usuarioDAO;

    public OrdenCompraDAOMySQL(UsuarioDAO usuarioDAO) {
        this.conexion = DBConnection.getConexion();
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public void guardar(OrdenCompra orden) {
        String sql = "INSERT INTO ordenes_compra (usuario_id, fecha, total, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orden.getCliente().getId());
            stmt.setObject(2, orden.getFecha());
            stmt.setDouble(3, orden.getTotal());
            stmt.setString(4, orden.getEstado().name());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orden.setNumeroUnico(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la orden de compra.", e);
        }
    }

    @Override
    public OrdenCompra buscarPorNumero(int numero) {
        String sql = "SELECT * FROM ordenes_compra WHERE numero = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, numero);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario cliente = usuarioDAO.buscarPorId(rs.getInt("usuario_id"));
                    return new OrdenCompra(
                            rs.getInt("numero"),
                            cliente,
                            rs.getTimestamp("fecha").toLocalDateTime(),
                            rs.getDouble("total"),
                            EstadoOrden.valueOf(rs.getString("estado"))
                    );
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("Error al buscar la orden de compra.", e);
        }
        return null;
    }

    @Override
    public List<OrdenCompra> obtenerTodas() {
        List<OrdenCompra> ordenes = new ArrayList<>();
        String sql = "SELECT * FROM ordenes_compra";
        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Usuario cliente = usuarioDAO.buscarPorId(rs.getInt("usuario_id"));
                ordenes.add(new OrdenCompra(
                        rs.getInt("numero"),
                        cliente,
                        rs.getTimestamp("fecha").toLocalDateTime(),
                        rs.getDouble("total"),
                        EstadoOrden.valueOf(rs.getString("estado"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las órdenes de compra.", e);
        }
        return ordenes;
    }

    @Override
    public List<OrdenCompra> obtenerPorUsuario(int idUsuario) {
        List<OrdenCompra> ordenes = new ArrayList<>();
        String sql = "SELECT * FROM ordenes_compra WHERE usuario_id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Usuario cliente = usuarioDAO.buscarPorId(idUsuario);
                    ordenes.add(new OrdenCompra(
                            rs.getInt("numero"),
                            cliente,
                            rs.getTimestamp("fecha").toLocalDateTime(),
                            rs.getDouble("total"),
                            EstadoOrden.valueOf(rs.getString("estado"))
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener las órdenes del usuario.", e);
        }
        return ordenes;
    }
}
