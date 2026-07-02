package org.example;

import org.example.Datos.Envio;
import org.example.Estados.EstadoEnvio;
import org.example.Estados.TipoEnvio;
import org.example.Interfaces.EnvioDAO;
import org.example.Persistencias.EnvioDAOMySQL;

import java.util.List;

public class Main3 {
    public static void main(String[] args) {

        EnvioDAO envioDAO = new EnvioDAOMySQL();
        Envio nuevoEnvio = new Envio(
         "Arg-98765-x",
         "Av.Ridavadia 18666",
         "Capital Federal",
         "Buenos Aires",
         "1408",
                TipoEnvio.ESTANDAR,
                EstadoEnvio.PENDIENTE,
                5000.0
        );
       envioDAO.guardar(nuevoEnvio);


        System.out.println("Prueba de lectura de envios");

        List<Envio> listaEnvios = envioDAO.obtenerTodos();

        for (Envio envio : listaEnvios) {
            System.out.println("Codigo :" + envio.getCodigoSeguimiento()+
                               "Direccion: " + envio.getDireccion() +
                               "Ciudad: " + envio.getCiudad() +
                                "Tipo: " + envio.getTipoEnvio() +
                                "Estado: " + envio.getEstado() +
                                "Costo: $" + envio.getCosto());
        }
    }
}
