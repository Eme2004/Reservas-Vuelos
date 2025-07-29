/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;
import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author USER
 */
public class PruebaConexion {
    public static void main(String[] args) throws SQLException {
        Connection conn = ConexionBD.conectar();
        if (conn != null) {
            System.out.println("Conectado correctamente a la base de datos.");
        } else {
            System.out.println("No se pudo conectar.");
        }
    }
    
}
