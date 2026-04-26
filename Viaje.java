import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Viaje {
    private LocalDate fecha;
    private LocalTime hora;
    private int precio;
    private Bus bus;
    private List<Pasaje> pasajes = new ArrayList<>();

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
        return bus;
    }

    public String[][] getAsientos() {
        int capacidad = bus.getNroAsientos();
        String[][] asientos = new String[capacidad][2];

        for (int i = 0; i < capacidad; i++) {
            int numAsiento = i + 1;
            asientos[i][0] = String.valueOf(numAsiento);
            //libre
            asientos[i][1] = String.valueOf(numAsiento);

            for (Pasaje p : pasajes) {
                if (p.getAsiento() == numAsiento) {
                    asientos[i][1] = "*"; //Ocupado
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
        String[][] lista = new String[pasajes.size()][4];
        for (int i = 0; i < pasajes.size(); i++) {
            Pasaje p = pasajes.get(i);
            Pasajero pas = p.getPasajero();
            lista[i][0] = pas.getIdPersona().toString();
            lista[i][1] = pas.getNombreCompleto().getNombre();
            lista[i][2] = pas.getNomContacto().getNombre();
            lista[i][3] = pas.getFonoContacto();
        }
        return lista;
    }


    public boolean existeDisponibilidad() {
        return getNroAsientosDisponibles() > 0;
    }

    public int getNroAsientosDisponibles() {
        return bus.getNroAsientos() - pasajes.size();
    }
}