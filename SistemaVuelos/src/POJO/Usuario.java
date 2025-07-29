/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author USER
 */
public class usuario {
    private int idusuario;
    private String nombre;
    private String correo;
    private String contrasena;
    
    
    public usuario(){}

    public usuario(int idusuario, String nombre, String correo, String contrasena) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idusuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idusuario + ", nombre=" + nombre + ", correo=" + correo + ", contrasena=" + contrasena + '}';
    }
    
    

}
