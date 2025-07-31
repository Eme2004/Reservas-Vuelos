/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author USER
 */
public class ReservaDAO {
     // CREATE - Insertar reserva
    public boolean insertarReserva(int idVuelo, int idUsuario, String nombre, String documento, int cantidad) {
    String sqlInsert = "INSERT INTO reservas (id_vuelo, id_usuario, nombre_cliente, documento_cliente, cantidad_asientos) VALUES (?, ?, ?, ?, ?)";
    String sqlUpdateAsientos = "UPDATE vuelos SET asientos_disponibles = asientos_disponibles - ? WHERE id_vuelo = ? AND asientos_disponibles >= ?";

    try (Connection conn = ConexionBD.conectar()) {
        conn.setAutoCommit(false); // Transacción

        // Primero actualizamos la cantidad de asientos disponibles
        try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdateAsientos)) {
            stmtUpdate.setInt(1, cantidad);
            stmtUpdate.setInt(2, idVuelo);
            stmtUpdate.setInt(3, cantidad);
            int filasActualizadas = stmtUpdate.executeUpdate();

            if (filasActualizadas == 0) {
                conn.rollback();
                return false; // No hay suficientes asientos disponibles
            }
        }

        // Insertamos la reserva
        try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
            stmtInsert.setInt(1, idVuelo);
            stmtInsert.setInt(2, idUsuario); // Nuevo parámetro agregado
            stmtInsert.setString(3, nombre);
            stmtInsert.setString(4, documento);
            stmtInsert.setInt(5, cantidad);
            stmtInsert.executeUpdate();
        }

        conn.commit();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }
    // DELETE - Cancelar reserva
public boolean cancelarReserva(int idReserva) {
    String sqlSelect = "SELECT id_vuelo, cantidad_asientos FROM reservas WHERE id_reserva = ?";
    String sqlDelete = "DELETE FROM reservas WHERE id_reserva = ?";
    String sqlUpdateVuelo = "UPDATE vuelos SET asientos_disponibles = asientos_disponibles + ? WHERE id_vuelo = ?";

    try (Connection conn = ConexionBD.conectar()) {
        conn.setAutoCommit(false); // Iniciar transacción

        int idVuelo = -1;
        int cantidad = 0;

        // 1. Obtener datos de la reserva
        try (PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect)) {
            stmtSelect.setInt(1, idReserva);
            ResultSet rs = stmtSelect.executeQuery();
            if (rs.next()) {
                idVuelo = rs.getInt("id_vuelo");
                cantidad = rs.getInt("cantidad_asientos");
            } else {
                conn.rollback();
                return false; // No existe la reserva
            }
        }

        // 2. Eliminar la reserva
        try (PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete)) {
            stmtDelete.setInt(1, idReserva);
            stmtDelete.executeUpdate();
        }

        // 3. Devolver los asientos al vuelo
        try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdateVuelo)) {
            stmtUpdate.setInt(1, cantidad);
            stmtUpdate.setInt(2, idVuelo);
            stmtUpdate.executeUpdate();
        }

        conn.commit();
        return true;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }
public List<ReservaInfo> obtenerReservasPorUsuario(int idUsuario) {
    List<ReservaInfo> lista = new ArrayList<>();

    String sql = """
        SELECT r.id_reserva, v.aerolinea AS nombre_vuelo, v.origen, v.destino, 
               v.fecha_salida, r.cantidad_asientos, r.fecha_reserva
        FROM reservas r
        JOIN vuelos v ON r.id_vuelo = v.id_vuelo
        WHERE r.id_usuario = ?
        ORDER BY r.fecha_reserva DESC
    """;

    try (Connection con = ConexionBD.conectar();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, idUsuario);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            ReservaInfo r = new ReservaInfo();
            r.setIdReserva(rs.getInt("id_reserva"));
            r.setNombreVuelo(rs.getString("nombre_vuelo"));
            r.setOrigen(rs.getString("origen"));
            r.setDestino(rs.getString("destino"));
            r.setFechaVuelo(rs.getString("fecha_salida"));
            r.setCantidad(rs.getInt("cantidad_asientos"));
            r.setFechaReserva(rs.getString("fecha_reserva"));
            lista.add(r);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}
}
    