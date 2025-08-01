/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;
import java.time.LocalDateTime;
/**
 *
 * @author Emesis
 */
/**
 * Clase que representa una reserva realizada por un usuario para un vuelo específico.
 */
public class Reservas {
    
    // Identificador único de la reserva
    private int idReserva;

    // Identificador del usuario que realiza la reserva
    private int idUsuario;

    // Identificador del vuelo reservado
    private int idVuelo;

    // Fecha y hora en que se realizó la reserva
    private LocalDateTime fechaReserva;

    // Cantidad de asientos reservados en el vuelo
    private int cantidadAsientos;

    /**
     * Constructor vacío necesario para instancias sin inicialización directa.
     */
    public Reservas(){}

    /**
     * Constructor con parámetros para inicializar todos los campos de la reserva.
     * 
     * @param idReserva ID único de la reserva
     * @param idUsuario ID del usuario que realiza la reserva
     * @param idVuelo ID del vuelo reservado
     * @param fechaReserva Fecha y hora de la reserva
     * @param cantidadAsientos Cantidad de asientos reservados
     */
    public Reservas(int idReserva, int idUsuario, int idVuelo, LocalDateTime fechaReserva, int cantidadAsientos) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idVuelo = idVuelo;
        this.fechaReserva = fechaReserva;
        this.cantidadAsientos = cantidadAsientos;
    }

    // Getters y Setters

    /**
     * @return ID único de la reserva
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * Establece el ID de la reserva
     * @param idReserva ID de la reserva
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * @return ID del usuario que realizó la reserva
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario que realiza la reserva
     * @param idUsuario ID del usuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return ID del vuelo reservado
     */
    public int getIdVuelo() {
        return idVuelo;
    }

    /**
     * Establece el ID del vuelo reservado
     * @param idVuelo ID del vuelo
     */
    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    /**
     * @return Fecha y hora en que se realizó la reserva
     */
    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Establece la fecha y hora en que se realizó la reserva
     * @param fechaReserva Fecha y hora de la reserva
     */
    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * @return Cantidad de asientos reservados
     */
    public int getCantidadAsientos() {
        return cantidadAsientos;
    }

    /**
     * Establece la cantidad de asientos reservados
     * @param cantidadAsientos Cantidad de asientos
     */
    public void setCantidadAsientos(int cantidadAsientos) {
        this.cantidadAsientos = cantidadAsientos;
    }

    /**
     * Representación en cadena de la clase Reservas
     * @return Información completa de la reserva
     */
    @Override
    public String toString() {
        return "Reservas{" +
                "idReserva=" + idReserva +
                ", idUsuario=" + idUsuario +
                ", idVuelo=" + idVuelo +
                ", fechaReserva=" + fechaReserva +
                ", cantidadAsientos=" + cantidadAsientos +
                '}';
    }
}
