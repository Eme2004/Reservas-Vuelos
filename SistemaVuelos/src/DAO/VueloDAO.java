/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.ConexionBD;
import POJO.Vuelo;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class VueloDAO {
    // CREATE - Insertar vuelo
    public boolean insertar(Vuelo vuelo) {
        String sql = "INSERT INTO vuelos (aerolinea, origen, destino, fecha_salida, hora_salida, escalas, precio, asientos_disponibles) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vuelo.getAerolinea());
            stmt.setString(2, vuelo.getOrigen());
            stmt.setString(3, vuelo.getDestino());
            stmt.setDate(4, Date.valueOf(vuelo.getFechaSalida()));
            stmt.setTime(5, Time.valueOf(vuelo.getHoraSalida()));
            stmt.setString(6, vuelo.getEscalas());  
            stmt.setDouble(7, vuelo.getPrecio());
            stmt.setInt(8, vuelo.getAsientosDisponibles());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar vuelo: " + e.getMessage());
            return false;
        }
    }

    // READ - Obtener todos los vuelos
    public List<Vuelo> obtenerTodos() {
        List<Vuelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vuelos ORDER BY fecha_salida, hora_salida";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Vuelo vuelo = new Vuelo(
                    rs.getInt("id_vuelo"),
                    rs.getString("aerolinea"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getDate("fecha_salida").toLocalDate(),
                    rs.getTime("hora_salida").toLocalTime(),
                    rs.getString("escalas"),  
                    rs.getDouble("precio"),
                    rs.getInt("asientos_disponibles")
                );
                lista.add(vuelo);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener vuelos: " + e.getMessage());
        }
        return lista;
    }

    // READ - Buscar vuelos filtrando por origen, destino y fecha
    public List<Vuelo> buscarVuelos(String origen, String destino, LocalDate fecha) {
        List<Vuelo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vuelos WHERE origen = ? AND destino = ? AND fecha_salida = ? ORDER BY hora_salida";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, origen);
            stmt.setString(2, destino);
            stmt.setDate(3, Date.valueOf(fecha));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vuelo vuelo = new Vuelo(
                    rs.getInt("id_vuelo"),
                    rs.getString("aerolinea"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getDate("fecha_salida").toLocalDate(),
                    rs.getTime("hora_salida").toLocalTime(),
                    rs.getString("escalas"),  
                    rs.getDouble("precio"),
                    rs.getInt("asientos_disponibles")
                );
                lista.add(vuelo);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar vuelos: " + e.getMessage());
        }

        return lista;
    }

    // UPDATE - Actualizar vuelo
    public boolean actualizar(Vuelo vuelo) {
        String sql = "UPDATE vuelos SET aerolinea=?, origen=?, destino=?, fecha_salida=?, hora_salida=?, escalas=?, precio=?, asientos_disponibles=? WHERE id_vuelo=?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vuelo.getAerolinea());
            stmt.setString(2, vuelo.getOrigen());
            stmt.setString(3, vuelo.getDestino());
            stmt.setDate(4, Date.valueOf(vuelo.getFechaSalida()));
            stmt.setTime(5, Time.valueOf(vuelo.getHoraSalida()));
            stmt.setString(6, vuelo.getEscalas());  
            stmt.setDouble(7, vuelo.getPrecio());
            stmt.setInt(8, vuelo.getAsientosDisponibles());
            stmt.setInt(9, vuelo.getIdVuelo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar vuelo: " + e.getMessage());
            return false;
        }
    }

    // DELETE - Eliminar vuelo por ID
    public boolean eliminar(int idVuelo) {
        String sql = "DELETE FROM vuelos WHERE id_vuelo=?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVuelo);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar vuelo: " + e.getMessage());
            return false;
        }
    }
}
