package org.example.Persistencias;

import org.example.Datos.Envio;
import org.example.Estados.EstadoEnvio;
import org.example.Estados.TipoEnvio;
import org.example.Exceptiones.EnvioNoEncontradoException;
import org.example.Interfaces.EnvioDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnvioDAOMySQL implements EnvioDAO {
    @Override
    public void guardar(Envio envio) {
        String sql = "INSERT INTO Envios (codigoSeguimiento,direccion,provincia,ciudad,codigoPostal,tipoEnvio,estado,costo) VALUES (?,?, ?,?,?,?,?,?)";

        Connection conn = DBConnection.getConexion();

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,envio.getCodigoSeguimiento());
            stmt.setString(2,envio.getDireccion());
            stmt.setString(3,envio.getProvincia());
            stmt.setString(4,envio.getCiudad());
            stmt.setString(5,envio.getCodigoPostal());
            stmt.setString(6,envio.getTipoEnvio().name());
            stmt.setString(7,envio.getEstado().name());
            stmt.setDouble(8,envio.getCosto());

            stmt.executeUpdate();

            System.out.println("Envio registrado con exito");

        }catch (SQLException e){
            System.out.println("Error al registrar Envio" + e.getMessage());
        }
    }

    @Override
    public Envio buscarPorCodigo(String codigoSeguimiento) {
        String sql = "SELECT * FROM Envios WHERE codigoSeguimiento = ?";

        Connection conn = DBConnection.getConexion();

        try (PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1,codigoSeguimiento);

            // Como el ResultSet se abre acá, lo metemos en un try interno para que se cierre bien
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return new Envio(
                       rs.getInt("id"),
                       rs.getString("codigoSeguimiento"),
                       rs.getString("direccion"),
                       rs.getString("provincia"),
                       rs.getString("ciudad"),
                       rs.getString("codigoPostal"),
                            TipoEnvio.valueOf(rs.getString("tipoEnvio")),
                            EstadoEnvio.valueOf(rs.getString("estado")),
                       rs.getDouble("costo")
                    );
                }else {
                    throw new EnvioNoEncontradoException("No se encontro el envio con el codigo: " + codigoSeguimiento);
                }
            }
        }catch (SQLException e){
            System.out.println("Error al buscar Envio" + e.getMessage());
            return null;
        }

    }

    @Override
    public List<Envio> obtenerTodos() {
        List<Envio> lista = new ArrayList<>();

        String sql = "SELECT * FROM Envios";
        Connection conn = DBConnection.getConexion();

        try(PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())

        {
            while (rs.next()){
                Envio envio = new Envio(
                 rs.getString("codigoSeguimiento"),
                 rs.getString("direccion"),
                 rs.getString("provincia"),
                 rs.getString("ciudad"),
                 rs.getString("codigoPostal"),
                 TipoEnvio.valueOf(rs.getString("TipoEnvio")),
                 EstadoEnvio.valueOf(rs.getString("estado")),
                 rs.getDouble("costo")
                );
                lista.add(envio);
            }
        }catch (SQLException e){
            System.out.println("Error al buscar Envio" + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Envio envio) {
        String sql = "UPDATE Envios SET direccion =?, ciudad = ?, codigoPostal =?, tipoEnvio=?, estado=?, costo=? WHERE codigoSeguimiento=?";
        Connection conn = DBConnection.getConexion();

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, envio.getDireccion());
            stmt.setString(2,envio.getProvincia());
            stmt.setString(3,envio.getCiudad());
            stmt.setString(4,envio.getCodigoPostal());
            stmt.setString(5,envio.getTipoEnvio().name());
            stmt.setString(6,envio.getEstado().name());
            stmt.setDouble(7,envio.getCosto());
            stmt.setString(8,envio.getCodigoSeguimiento());

            stmt.executeUpdate();
            System.out.println("Envio actualizado con exito");


        }catch (SQLException e){
            System.out.println("Error al actualizar Envio" + e.getMessage());
        }
    }

    @Override
    public void eliminar(String codigoSeguimiento) {
        String sql = "DELETE FROM Envios WHERE codigoSeguimiento = ?";

        Connection conn = DBConnection.getConexion();

        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1,codigoSeguimiento);
            stmt.executeUpdate();
            System.out.println("Envio eliminado con exito");
        }catch (SQLException e){
            System.out.println("Error al eliminar Envio" + e.getMessage());
        }
    }
}
