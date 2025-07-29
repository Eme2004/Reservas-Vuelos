/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;
import java.time.LocalDateTime;
/**
 *
 * @author USER
 */
public class Reservas {
    private int idReserva;
    private int idUsuario;
    private int idVuelo;
    private LocalDateTime fechaReserva;
    private int cantidadAsientos;
    
    public Reservas(){}

    public Reservas(int idReserva, int idUsuario, int idVuelo, LocalDateTime fechaReserva, int cantidadAsientos) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idVuelo = idVuelo;
        this.fechaReserva = fechaReserva;
        this.cantidadAsientos = cantidadAsientos;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    public void setCantidadAsientos(int cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    @Override
    public String toString() {
        return "Reservas{" + "idReserva=" + idReserva + ", idUsuario=" + idUsuario + ", idVuelo=" + idVuelo + ", fechaReserva=" + fechaReserva + ", cantidadAsientos=" + cantidadAsientos + '}';
    }
    
    
}
