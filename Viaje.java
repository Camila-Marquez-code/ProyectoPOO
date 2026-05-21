//Camila Valeska Márquez Burgos
package modelo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Viaje {
    private LocalDate fecha;
    private LocalTime hora;
    private int precio;
    private Bus bus;
    private ArrayList<Pasaje> pasajes;
    private ArrayList<Venta> ventas;
    private ArrayList<Conductor> conductor;
    private ArrayList<Tripulante> tripulantes;
    private int duracion;
    private Auxiliar auxiliar;
    private Terminal sale;
    private Terminal llega;


    public Viaje(LocalDate fecha, LocalTime hora, int precio, Bus bus, int duracion, Auxiliar auxiliar, Conductor conductor, Terminal sale, Terminal llega) {
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.bus = bus;
        this.duracion = duracion;
        this.auxiliar = auxiliar;
        this.sale = sale;
        this.llega = llega;
        this.pasajes = new ArrayList<Pasaje>();
        this.ventas = new ArrayList<Venta>();
        this.tripulantes = new ArrayList<Tripulante>();
        this.conductor = new ArrayList<Conductor>();
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

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalDateTime getFechaHoraTermino() {
        return LocalDateTime.of(fecha, hora).plusMinutes(duracion);
    }

    public Bus getBus() {
        return bus;
    }

    public String[][] getAsientos() {
        int capacidad = bus.getNroAsientos();
        String[][] asientos = new String[capacidad][2];

        for (int i = 0; i < capacidad; i++) {
            int numAsiento = i + 1;
            asientos[i][0] = String.valueOf(numAsiento);
            // libre
            asientos[i][1] = String.valueOf(numAsiento);

            for (Pasaje p : pasajes) {
                if (p.getAsiento() == numAsiento) {
                    asientos[i][1] = "*"; // Ocupado
                    break;
                }
            }
        }
        return asientos;
    }

    public void addPasaje(Pasaje pasaje) {
        pasajes.add(pasaje);
    }

    public String[][] getListaPasajeros() {
        String[][] lista = new String[pasajes.size()][5];
        for (int i = 0; i < pasajes.size(); i++) {
            Pasaje p = pasajes.get(i);
            Pasajero pas = p.getPasajero();
            lista[i][0] = String.valueOf(p.getAsiento());
            lista[i][1] = pas.getIdPersona().toString();
            lista[i][2] = pas.getNombreCompleto().toString();
            lista[i][3] = pas.getNomContacto().toString();
            lista[i][4] = pas.getFonoContacto();
        }
        return lista;
    }

    public boolean existeDisponibilidad() {
        return getNroAsientosDisponibles() > 0;
    }

    public int getNroAsientosDisponibles() {
        return bus.getNroAsientos() - pasajes.size();
    }

    public Venta[] getVentas() {
        return ventas.toArray(new Venta[0]);
    }

    public void addConductor(Conductor conductor) {
        if (this.conductor.size() < 2) {
            this.conductor.add(conductor);
        } else {
            System.out.println("ERROR! Un viaje no puede tener mas de dos conductores");
        }
    }

    public Tripulante[] getTripulantes() {
        return tripulantes.toArray(new Tripulante[0]);
    }

    public Terminal getTerminalLlegada() {
        return llega;
    }

    public Terminal getTerminalSalida() {
        return sale;
    }
}