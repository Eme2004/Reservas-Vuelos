/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Emesis
 */
/**
 * Clase que representa un usuario del sistema de reservas de vuelos.
 * Contiene los atributos básicos como ID, nombre, correo y contraseña.
 */
public class usuario {

    // Identificador único del usuario
    private int idusuario;

    // Nombre del usuario
    private String nombre;

    // Correo electrónico del usuario
    private String correo;

    // Contraseña del usuario (debe protegerse en entornos reales)
    private String contrasena;

    /**
     * Constructor vacío. Útil para frameworks y herramientas que requieren un constructor por defecto.
     */
    public usuario() {}

    /**
     * Constructor con todos los atributos para inicializar un objeto usuario.
     * 
     * @param idusuario     ID del usuario
     * @param nombre        Nombre del usuario
     * @param correo        Correo electrónico
     * @param contrasena    Contraseña del usuario
     */
    public usuario(int idusuario, String nombre, String correo, String contrasena) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el ID del usuario.
     * 
     * @return idusuario
     */
    public int getIdUsuario() {
        return idusuario;
    }

    /**
     * Establece el ID del usuario.
     * 
     * @param idUsuario nuevo ID del usuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idusuario = idUsuario;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param correo nuevo correo electrónico
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return contraseña
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param contrasena nueva contraseña
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Retorna una representación en texto del objeto usuario.
     * 
     * @return cadena con los atributos del usuario
     */
    @Override
    public String toString() {
        return "Usuario{" + 
            "idUsuario=" + idusuario + 
            ", nombre=" + nombre + 
            ", correo=" + correo + 
            ", contrasena=" + contrasena + 
            '}';
    }
}