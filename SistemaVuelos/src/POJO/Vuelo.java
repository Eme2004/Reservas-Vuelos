/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author Emesis
 */
/**
 * Clase que representa un vuelo dentro del sistema de reservas.
 * Contiene información relevante como aerolínea, origen, destino, fechas, precio y asientos disponibles.
 */
public class Vuelo {

    // Identificador único del vuelo
    private int idVuelo;

    // Nombre de la aerolínea que opera el vuelo
    private String aerolinea;

    // Ciudad o aeropuerto de origen
    private String origen;

    // Ciudad o aeropuerto de destino
    private String destino;

    // Fecha de salida del vuelo
    private LocalDate fechaSalida;

    // Hora de salida del vuelo
    private LocalTime horaSalida;

    // Información sobre escalas del vuelo (puede ser cantidad o nombres de ciudades)
    private String escalas;

    // Precio del vuelo
    private double precio;

    // Cantidad de asientos disponibles para reservar
    private int asientosDisponibles;

    /**
     * Constructor vacío requerido para algunas operaciones como serialización o frameworks.
     */
    public Vuelo() {}

    /**
     * Constructor completo que inicializa todos los campos del vuelo.
     *
     * @param idVuelo Identificador único del vuelo
     * @param aerolinea Nombre de la aerolínea
     * @param origen Ciudad de origen
     * @param destino Ciudad de destino
     * @param fechaSalida Fecha programada de salida
     * @param horaSalida Hora programada de salida
     * @param escalas Escalas del vuelo
     * @param precio Precio del vuelo
     * @param asientosDisponibles Número de asientos disponibles
     */
    public Vuelo(int idVuelo, String aerolinea, String origen, String destino, LocalDate fechaSalida, LocalTime horaSalida, String escalas, double precio, int asientosDisponibles) {
        this.idVuelo = idVuelo;
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.escalas = escalas;
        this.precio = precio;
        this.asientosDisponibles = asientosDisponibles;
    }

    // Métodos getters y setters para acceder y modificar cada atributo

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getEscalas() {
        return escalas;
    }

    public void setEscalas(String escalas) {
        this.escalas = escalas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    /**
     * Retorna una representación textual del objeto Vuelo,
     * útil para depuración o impresión en listas.
     *
     * @return Cadena con los valores de todos los atributos del vuelo.
     */
    @Override
    public String toString() {
        return "Vuelo{" + 
            "idVuelo=" + idVuelo + 
            ", aerolinea=" + aerolinea + 
            ", origen=" + origen + 
            ", destino=" + destino + 
            ", fechaSalida=" + fechaSalida + 
            ", horaSalida=" + horaSalida + 
            ", escalas=" + escalas +  
            ", precio=" + precio + 
            ", asientosDisponibles=" + asientosDisponibles + 
            '}';
    }
}

