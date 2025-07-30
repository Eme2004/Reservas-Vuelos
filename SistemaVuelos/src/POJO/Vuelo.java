/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author USER
 */
public class Vuelo {
    private int idVuelo;
    private String aerolinea;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private LocalTime horaSalida;
    private String escalas;  
    private double precio;
    private int asientosDisponibles;
    
    public Vuelo(){}

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

