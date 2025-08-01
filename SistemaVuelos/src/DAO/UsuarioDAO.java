/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import POJO.usuario;
import Conexion.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Emesis
 */
public class usuarioDAO {
    // CREATE - Crear usuario
    public boolean insertar(usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, contrasena) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getContrasena());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(" Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    // READ - Leer/buscar usuario por correo
    public usuario buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena")
                );
            }

        } catch (SQLException e) {
            System.out.println(" Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

    // READ - Obtener todos los usuarios
    public List<usuario> obtenerTodos() {
        List<usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuario u = new usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena")
                );
                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println(" Error al obtener usuarios: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE - Actualizar usuario
    public boolean actualizar(usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, correo = ?, contrasena = ? WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getContrasena());
            stmt.setInt(4, usuario.getIdUsuario());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(" Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // DELETE - Eliminar usuario por ID
    public boolean eliminar(int idUsuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(" Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    // LOGIN - Autenticación de usuario que devuelve el usuario completo si existe, o null si no
public usuario autenticar(String correo, String contrasena) {
    String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
    try (Connection conn = ConexionBD.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, correo);
        stmt.setString(2, contrasena);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            // Retornar objeto usuario con todos los datos, incluido id_usuario
            return new usuario(
                rs.getInt("id_usuario"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("contrasena")
            );
        }

    } catch (SQLException e) {
        System.out.println(" Error al autenticar usuario: " + e.getMessage());
    }
    return null; // no autenticado
}
    }
