/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Emesis
 */
/**
 * Clase POJO (Plain Old Java Object) que representa la información de una reserva
 * de vuelo realizada por un usuario. Esta clase es utilizada para transportar 
 * datos desde la base de datos hacia la interfaz gráfica o lógica de negocio.
 */
public class ReservaInfo {

    /** Identificador único de la reserva */
    private int idReserva;

    /** Nombre del vuelo (aerolínea) asociado a la reserva */
    private String nombreVuelo;

    /** Ciudad o aeropuerto de origen del vuelo */
    private String origen;

    /** Ciudad o aeropuerto de destino del vuelo */
    private String destino;

    /** Fecha y hora de salida del vuelo */
    private String fechaVuelo;

    /** Cantidad de asientos reservados por el cliente */
    private int cantidad;

    /** Fecha en la que se realizó la reserva */
    private String fechaReserva;

    // ======= Getters y Setters =======

    /**
     * Obtiene el ID de la reserva.
     * @return ID de la reserva.
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * Establece el ID de la reserva.
     * @param idReserva ID único de la reserva.
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Obtiene el nombre del vuelo.
     * @return Nombre del vuelo o aerolínea.
     */
    public String getNombreVuelo() {
        return nombreVuelo;
    }

    /**
     * Establece el nombre del vuelo.
     * @param nombreVuelo Nombre del vuelo o aerolínea.
     */
    public void setNombreVuelo(String nombreVuelo) {
        this.nombreVuelo = nombreVuelo;
    }

    /**
     * Obtiene la ciudad o aeropuerto de origen del vuelo.
     * @return Origen del vuelo.
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Establece el origen del vuelo.
     * @param origen Ciudad o aeropuerto de origen.
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * Obtiene la ciudad o aeropuerto de destino del vuelo.
     * @return Destino del vuelo.
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Establece el destino del vuelo.
     * @param destino Ciudad o aeropuerto de destino.
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * Obtiene la fecha y hora del vuelo.
     * @return Fecha de salida del vuelo.
     */
    public String getFechaVuelo() {
        return fechaVuelo;
    }

    /**
     * Establece la fecha y hora del vuelo.
     * @param fechaVuelo Fecha de salida del vuelo.
     */
    public void setFechaVuelo(String fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    /**
     * Obtiene la cantidad de asientos reservados.
     * @return Cantidad de asientos.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de asientos reservados.
     * @param cantidad Número de asientos.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene la fecha en la que se realizó la reserva.
     * @return Fecha de la reserva.
     */
    public String getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Establece la fecha de la reserva.
     * @param fechaReserva Fecha en que se hizo la reserva.
     */
    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
