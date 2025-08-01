/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Emesis
 */
// Clase encargada de establecer la conexión con la base de datos PostgreSQL
public class ConexionBD {

    // URL de conexión al servidor PostgreSQL.
    // Incluye el nombre de la base de datos: "Sistema de Reservas de Vuelos"
    private static final String URL = "jdbc:postgresql://localhost:5432/Sistema de Reservas de Vuelos";

    // Nombre de usuario de la base de datos (por defecto "postgres" en muchas instalaciones)
    private static final String USUARIO = "postgres";  

    // Contraseña del usuario especificado para acceder a la base de datos
    private static final String CONTRASENA = "eme12";  

    /**
     * Método estático que intenta establecer una conexión con la base de datos PostgreSQL.
     *
     * @return Objeto Connection si la conexión es exitosa, o null si ocurre un error.
     */
    public static Connection conectar() {
        try {
            // Intenta crear una conexión utilizando los datos especificados
            Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            // Mensaje de confirmación en consola si la conexión es exitosa
            System.out.println(" Conexión exitosa a PostgreSQL");

            // Devuelve el objeto Connection para su uso posterior
            return conn;

        } catch (SQLException e) {
            // Si ocurre un error al intentar conectarse, se imprime el mensaje en consola
            System.out.println(" Error de conexión: " + e.getMessage());

            // Devuelve null indicando que no se pudo establecer la conexión
            return null;
        }
    }
}

