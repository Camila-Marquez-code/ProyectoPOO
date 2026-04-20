package Proyecto;

import java.time.LocalDate;
import java.time.LocalTime;

public class Viaje {
    private LocalDate fecha;
    private LocalTime hora;
    private int precio;

    public Viaje(LocalDate fecha, LocalTime hora, int precio, Bus bus) {
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.bus = bus;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Bus getBus() {
        return Bus;
    }

    public String getAsientos(){
        return Asientos;
    }

    public void addPasaje(Pasaje pasaje){
        this.Pasaje = Pasaje;
    }

    public String getListaPasajeros(){
        return ListaPasajeros;
    }

    public boolean existeDisponibilidad(){
        return existeDisponibilidad;
    }

    public int getnroAsientosDisponibles(){
        return getnroAsientosDisponibles;
    }
}
