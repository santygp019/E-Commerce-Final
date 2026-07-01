package org.example.Persistencias;

import org.example.Datos.Categoria;
import org.example.Estados.EstadoCategoria;
import org.example.Exceptiones.CategoriaNoEncontradaException;
import org.example.Interfaces.CategoriaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOMySQL implements CategoriaDAO {


    @Override
    public void guardar(Categoria categoria) {
        String sql = "INSERT INTO Categorias (nombre, descripcion, estado) VALUES (?, ?, ?)";

        Connection conn = DBConnection.getConexion();

        try (PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1,categoria.getNombre());
            stmt.setString(2,categoria.getDescripcion());
            stmt.setString(3,categoria.getEstado().name()); //el .name es para guarda el enum como texto

            stmt.executeUpdate();
            System.out.println("Categoria Registrada con exito");
        }catch (SQLException e){
            System.out.println("Error al guardar la categoria" + e.getMessage());
        }

    }

    @Override
    public Categoria buscarPorId(int id) throws CategoriaNoEncontradaException {
        String sql = "SELECT * FROM Categorias where id =?";

        Connection conn = DBConnection.getConexion();

        try(PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){ // aca le decimos que si encontro, armamos el objeto
                return new Categoria(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                        EstadoCategoria.valueOf(rs.getString("estado"))
                ); // aca convertimos el estado en texto de nuevo

            }else {
                // aca tiramos la excepcion por si no la encuentra
                throw  new CategoriaNoEncontradaException("No se encontro ninguna categoria con el ID:" + id);
            }
        }catch (SQLException e){
            System.out.println("Error de base de datos al buscar la categoria " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Categoria> obtenerTodas() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM Categorias ";

        Connection conn = DBConnection.getConexion();

        try(PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery())
        {
            while (rs.next()){
                Categoria cat = new Categoria
                        (
                         rs.getInt("id"),
                         rs.getString("nombre"),
                         rs.getString("descripcion"),
                         EstadoCategoria.valueOf(rs.getString("estado"))
                        );
                lista.add(cat); // agregamos el objeto a la lista
                }
            }catch(SQLException e ){
            System.out.println("Error al listar categorias " + e.getMessage());
        }
            return lista;
        }

    @Override
    public void actualizar(Categoria categoria) {
        String sql = "UPDATE Categoria SET nombre=?, descripcion =?, estado=? WHERE id=?";

        Connection conn = DBConnection.getConexion();

        try(PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1,categoria.getNombre());
            stmt.setString(2,categoria.getDescripcion());
            stmt.setString(3,categoria.getEstado().name());
            stmt.setInt(4,categoria.getId()); // aca usamos el ID para saber cual modificar

            stmt.executeUpdate();
            System.out.println("Categoria actualizada correctamente ");
        }catch (SQLException e){
            System.out.println("Erro al actualizar " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Categoria WHERE id = ?";

        try(Connection conn = DBConnection.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1,id);
            stmt.executeUpdate();
            System.out.println("Categoria eliminada con exito");
        }catch (SQLException e){
            System.out.println("Error al eliminar la categoria" + e.getMessage());
        }
        }

}



