/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Conexion.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author Emesis
 */
/**
 * DAO (Data Access Object) para manejar operaciones sobre reservas.
 * Incluye creación, cancelación y obtención de reservas por usuario.
 */
public class ReservaDAO {

    private static final Logger LOGGER = Logger.getLogger(ReservaDAO.class.getName());

    // Consultas SQL parametrizadas
    private static final String SQL_INSERT = """
        INSERT INTO reservas (id_vuelo, id_usuario, nombre_cliente, documento_cliente, cantidad_asientos)
        VALUES (?, ?, ?, ?, ?)
    """;

    private static final String SQL_UPDATE_ASIENTOS = """
        UPDATE vuelos
        SET asientos_disponibles = asientos_disponibles - ?
        WHERE id_vuelo = ? AND asientos_disponibles >= ?
    """;

    private static final String SQL_SELECT_RESERVA = """
        SELECT id_vuelo, cantidad_asientos FROM reservas WHERE id_reserva = ?
    """;

    private static final String SQL_DELETE_RESERVA = """
        DELETE FROM reservas WHERE id_reserva = ?
    """;

    private static final String SQL_UPDATE_VUELO_ASIENTOS = """
        UPDATE vuelos SET asientos_disponibles = asientos_disponibles + ? WHERE id_vuelo = ?
    """;

    private static final String SQL_OBTENER_RESERVAS_POR_USUARIO = """
        SELECT r.id_reserva, v.aerolinea AS nombre_vuelo, v.origen, v.destino, 
               v.fecha_salida, r.cantidad_asientos, r.fecha_reserva
        FROM reservas r
        JOIN vuelos v ON r.id_vuelo = v.id_vuelo
        WHERE r.id_usuario = ?
        ORDER BY r.fecha_reserva DESC
    """;

    /**
     * Inserta una nueva reserva y actualiza los asientos disponibles del vuelo.
     *
     * @param idVuelo   ID del vuelo.
     * @param idUsuario ID del usuario que realiza la reserva.
     * @param nombre    Nombre del cliente.
     * @param documento Documento del cliente.
     * @param cantidad  Cantidad de asientos a reservar.
     * @return true si se reservó con éxito, false si hubo error o no hay asientos disponibles.
     */
    public boolean insertarReserva(int idVuelo, int idUsuario, String nombre, String documento, int cantidad) {
        Connection conn = null;

        try {
            conn = ConexionBD.conectar();
            if (conn == null) {
                LOGGER.warning("Conexión fallida al intentar insertar reserva.");
                return false;
            }

            conn.setAutoCommit(false); // Iniciar transacción manual

            // 1. Verificar y actualizar los asientos disponibles
            try (PreparedStatement stmtUpdate = conn.prepareStatement(SQL_UPDATE_ASIENTOS)) {
                stmtUpdate.setInt(1, cantidad);
                stmtUpdate.setInt(2, idVuelo);
                stmtUpdate.setInt(3, cantidad);

                int filasAfectadas = stmtUpdate.executeUpdate();
                if (filasAfectadas == 0) {
                    conn.rollback(); // No hay suficientes asientos
                    return false;
                }
            }

            // 2. Insertar la reserva
            try (PreparedStatement stmtInsert = conn.prepareStatement(SQL_INSERT)) {
                stmtInsert.setInt(1, idVuelo);
                stmtInsert.setInt(2, idUsuario);
                stmtInsert.setString(3, nombre);
                stmtInsert.setString(4, documento);
                stmtInsert.setInt(5, cantidad);
                stmtInsert.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error al hacer rollback de insertarReserva", ex);
                }
            }
            LOGGER.log(Level.SEVERE, "Error al insertar reserva", e);
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "Error al cerrar conexión en insertarReserva", e);
                }
            }
        }
    }

    /**
     * Cancela una reserva y devuelve los asientos al vuelo correspondiente.
     *
     * @param idReserva ID de la reserva a cancelar.
     * @return true si se canceló con éxito, false en caso de error.
     */
    public boolean cancelarReserva(int idReserva) {
        Connection conn = null;

        try {
            conn = ConexionBD.conectar();
            if (conn == null) {
                LOGGER.warning("Conexión fallida al intentar cancelar reserva.");
                return false;
            }

            conn.setAutoCommit(false);

            int idVuelo = -1;
            int cantidad = 0;

            // 1. Obtener información de la reserva
            try (PreparedStatement stmtSelect = conn.prepareStatement(SQL_SELECT_RESERVA)) {
                stmtSelect.setInt(1, idReserva);
                try (ResultSet rs = stmtSelect.executeQuery()) {
                    if (rs.next()) {
                        idVuelo = rs.getInt("id_vuelo");
                        cantidad = rs.getInt("cantidad_asientos");
                    } else {
                        conn.rollback();
                        return false; // No se encontró la reserva
                    }
                }
            }

            // 2. Eliminar la reserva
            try (PreparedStatement stmtDelete = conn.prepareStatement(SQL_DELETE_RESERVA)) {
                stmtDelete.setInt(1, idReserva);
                stmtDelete.executeUpdate();
            }

            // 3. Devolver asientos al vuelo
            try (PreparedStatement stmtUpdate = conn.prepareStatement(SQL_UPDATE_VUELO_ASIENTOS)) {
                stmtUpdate.setInt(1, cantidad);
                stmtUpdate.setInt(2, idVuelo);
                stmtUpdate.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.SEVERE, "Error al hacer rollback de cancelarReserva", ex);
                }
            }
            LOGGER.log(Level.SEVERE, "Error al cancelar reserva", e);
            return false;

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "Error al cerrar conexión en cancelarReserva", e);
                }
            }
        }
    }

    /**
     * Obtiene la lista de reservas de un usuario, incluyendo datos del vuelo reservado.
     *
     * @param idUsuario ID del usuario.
     * @return Lista de objetos ReservaInfo con los datos de las reservas.
     */
    public List<ReservaInfo> obtenerReservasPorUsuario(int idUsuario) {
        List<ReservaInfo> lista = new ArrayList<>();

        try (Connection con = ConexionBD.conectar();
             PreparedStatement pst = con.prepareStatement(SQL_OBTENER_RESERVAS_POR_USUARIO)) {

            pst.setInt(1, idUsuario);

            try (ResultSet rs = pst.executeQuery()) {
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
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener reservas por usuario", e);
        }

        return lista;
    }
}  