package org.example.Persistencias;

import org.example.Datos.*;
import org.example.Estados.EstadoProducto;
import org.example.Exceptiones.DatosInvalidosException;
import org.example.Exceptiones.ProductoNoEncontradoException;
import org.example.Interfaces.ProductoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOMySQL implements ProductoDAO {
    private Connection instancia;

    public ProductoDAOMySQL() {
        this.instancia = DBConnection.getConexion();
    }

    @Override
    public void guardar(Producto producto) {
        String sql = "INSERT INTO productos (codigo_unico, nombre, descripcion, precio, categoria_id, stock, peso, estado, tipo) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = instancia.prepareStatement(sql)) {
            ps.setString(1, producto.getCodigoUnico());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getCategoria().getId());
            ps.setInt(6, producto.getStock());
            ps.setDouble(7, producto.getPeso());
            ps.setString(8, producto.getEstado().name());

            String tipo;
            if(producto instanceof ProductoFisico){
                tipo = "FISICO";
            } else if (producto instanceof ProductoDigital){
                tipo = "DIGITAL";
            }else {
                tipo = "IMPORTADO";
            }
            ps.setString(9, tipo);

            ps.executeUpdate();

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                throw new DatosInvalidosException("El código '" + producto.getCodigoUnico() + "' ya existe.");
            }
            throw new DatosInvalidosException("Error al guardar el producto." + e);
        }
    }

    @Override
    public Producto buscarPorId(String codigoUnico) {
        String sql = "SELECT * FROM productos WHERE codigo_unico = ?";
        Producto producto = null;

        try (PreparedStatement ps = instancia.prepareStatement(sql)) {
            ps.setString(1, codigoUnico);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = extraerProductoDeResultSet(rs);
                } else {
                    throw new ProductoNoEncontradoException("No se encontró producto con código: " + codigoUnico);
                }
            }
        } catch (SQLException e) {
            throw new DatosInvalidosException("Error al buscar el producto." + e);
        }

        return producto;
    }

    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (PreparedStatement ps = instancia.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                productos.add(extraerProductoDeResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DatosInvalidosException("Error al obtener los productos." + e);
        }

        return productos;
    }

    @Override
    public void actualizar(Producto producto) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio=?, categoria_id=?, stock=?, peso=?, estado=? WHERE codigo_unico=?";

        try (PreparedStatement ps = instancia.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getCategoria().getId());
            ps.setInt(5, producto.getStock());
            ps.setDouble(7, producto.getPeso());
            ps.setString(8, producto.getEstado().name());
            ps.setString(9, producto.getCodigoUnico());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new ProductoNoEncontradoException("No se pudo actualizar. Producto '" + producto.getCodigoUnico() + "' no encontrado.");
            }
        } catch (SQLException e) {
            throw new DatosInvalidosException("Error al actualizar el producto." + e);
        }
    }

    @Override
    public void eliminar(String codigoUnico) {
        String sql = "DELETE FROM productos WHERE codigo_unico = ?";

        try (PreparedStatement ps = instancia.prepareStatement(sql)) {
            ps.setString(1, codigoUnico);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new ProductoNoEncontradoException("No se pudo eliminar. Producto '" + codigoUnico + "' no encontrado.");
            }
        } catch (SQLException e) {
            throw new DatosInvalidosException("Error al eliminar el producto." + e);
        }
    }

    private Producto extraerProductoDeResultSet(ResultSet rs) throws SQLException {
        String codigo = rs.getString("codigo_unico");
        String nombre = rs.getString("nombre");
        String descripcion = rs.getString("descripcion");
        double precio = rs.getDouble("precio");
        int categoriaId = rs.getInt("categoria_id");
        int stock = rs.getInt("stock");
        double peso = rs.getDouble("peso");
        EstadoProducto estado = EstadoProducto.valueOf(rs.getString("estado"));
        String tipo = rs.getString("tipo");


        Categoria categoria = new CategoriaDAOMySQL().buscarPorId(categoriaId);

        if (tipo.equals("FISICO")) {
            return new ProductoFisico(codigo, nombre, descripcion, precio, categoria, stock, peso, estado);
        }
        if (tipo.equals("DIGITAL")) {
            return new ProductoDigital(codigo, nombre, descripcion, precio, categoria, stock, estado);
        }
        if (tipo.equals("IMPORTADO")) {
            return new ProductoImportado(codigo, nombre, descripcion, precio, categoria, stock, peso, estado);
        }
        throw new DatosInvalidosException("Tipo de producto desconocido en la base de datos: " + tipo);

    }
}