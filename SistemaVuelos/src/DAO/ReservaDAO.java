/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.ConexionBD;
import POJO.Reservas;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author USER
 */
public class ReservaDAO {
     // CREATE - Insertar reserva
    public boolean insertar(Reservas reserva) {
        String sql = "INSERT INTO reservas (id_usuario, id_vuelo, fecha_reserva, cantidad_asientos) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getIdUsuario());
            stmt.setInt(2, reserva.getIdVuelo());
            stmt.setTimestamp(3, Timestamp.valueOf(reserva.getFechaReserva()));
            stmt.setInt(4, reserva.getCantidadAsientos());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar reserva: " + e.getMessage());
            return false;
        }
    }

    // READ - Obtener reservas por ID de usuario
    public List<Reservas> obtenerPorUsuario(int idUsuario) {
        List<Reservas> lista = new ArrayList<>();
        String sql = "SELECT * FROM reservas WHERE id_usuario = ? ORDER BY fecha_reserva DESC";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reservas r = new Reservas(
                    rs.getInt("id_reserva"),
                    rs.getInt("id_usuario"),
                    rs.getInt("id_vuelo"),
                    rs.getTimestamp("fecha_reserva").toLocalDateTime(),
                    rs.getInt("cantidad_asientos")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener reservas: " + e.getMessage());
        }

        return lista;
    }

    // UPDATE - Modificar cantidad de asientos
    public boolean actualizar(Reservas reserva) {
        String sql = "UPDATE reservas SET cantidad_asientos = ? WHERE id_reserva = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reserva.getCantidadAsientos());
            stmt.setInt(2, reserva.getIdReserva());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar reserva: " + e.getMessage());
            return false;
        }
    }

    // DELETE - Eliminar reserva por ID
    public boolean eliminar(int idReserva) {
        String sql = "DELETE FROM reservas WHERE id_reserva = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idReserva);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar reserva: " + e.getMessage());
            return false;
        }
    }
}
