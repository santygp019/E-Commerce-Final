package org.example;

import org.example.Datos.*;
import org.example.Estados.EstadoProducto;
import org.example.Interfaces.CalificacionDAO;
import org.example.Interfaces.DAOFactory;
import org.example.Interfaces.ProductoDAO;
import org.example.Interfaces.UsuarioDAO;
import org.example.Persistencias.CategoriaDAOMySQL;
import org.example.Persistencias.DAOFactoryMySQL;
/*
public class Main2 {
    static void main(String[] args) {

        DAOFactory factory = new DAOFactoryMySQL();

        UsuarioDAO usuarioDAO = factory.crearUsuarioDAO();
        ProductoDAO productoDAO = factory.crearProductoDAO();
        CalificacionDAO calificacionDAO = factory.crearCalificacionDAO();

        try {
            Usuario cliente = usuarioDAO.buscarPorId(1);
            Producto producto = productoDAO.buscarPorId("PROD-001");

            System.out.println("Usuario recuperado: " + cliente.getNombre());
            System.out.println("Producto recuperado: " + producto.getNombre());

            Calificacion nuevaCalificacion = new Calificacion(
                    cliente,
                    producto,
                    5,
                    "¡Excelente calidad! Superó mis expectativas."
            );
            calificacionDAO.guardar(nuevaCalificacion);
            System.out.println("Calificación guardada exitosamente. ID asignado: " + nuevaCalificacion.getId());

            System.out.println("\n Consultando reseñas del producto ");
            var reseñas = calificacionDAO.obtenerPorProducto(producto.getCodigoUnico());

            for (Calificacion reseña : reseñas) {
                System.out.println("  Cliente: " + reseña.getCliente().getNombre());
                System.out.println("  Puntaje: " + reseña.getPuntaje() + "/5");
                System.out.println("  Comentario: " + reseña.getComentario());
            }
        } catch (RuntimeException e) {
            System.err.println(" Ocurrió un error en la prueba.");
            e.printStackTrace();
        }
*/
public class Main2 {
   public static void main(String[] args) {

        DAOFactory factory = new DAOFactoryMySQL();

        UsuarioDAO usuarioDAO = factory.crearUsuarioDAO();
        ProductoDAO productoDAO = factory.crearProductoDAO();
        CalificacionDAO calificacionDAO = factory.crearCalificacionDAO();

        try {
            Categoria cat = new CategoriaDAOMySQL().buscarPorId(1);

            Producto p = new ProductoFisico("P001", "Auriculares", "Auriculares inalámbricos", 15000, cat, 10, 0.3, EstadoProducto.Activo);
            productoDAO.guardar(p);
            System.out.println("Producto guardado con éxito.");

            Producto encontrado = productoDAO.buscarPorId("P001");
            encontrado.mostrarInformacion();

        } catch (RuntimeException e) {
            System.err.println("Ocurrió un error probando Producto.");
            e.printStackTrace();
        }
    }
}