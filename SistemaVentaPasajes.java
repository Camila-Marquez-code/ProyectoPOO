//Marisol Yañez Borquez
//Juan Henríquez Vergara

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class SistemaVentaPasajes {

    private ArrayList<Cliente> clientes;
    private ArrayList<Pasajero> pasajeros;
    private ArrayList<Bus> buses;
    private ArrayList<Viaje> viajes;
    private ArrayList<Venta> ventas;

    public SistemaVentaPasajes() {
        clientes = new ArrayList<>();
        pasajeros = new ArrayList<>();
        buses = new ArrayList<>();
        viajes = new ArrayList<>();
        ventas = new ArrayList<>();
    }

    public boolean createCliente(IdPersona id, Nombre nom, String fono, String email){
        Cliente clientee = new Cliente(id, nom, fono, email);
        clientes.add(clientee);
        return true;
    }

    public boolean createPasajero (IdPersona id, Nombre nom, String fono, Nombre nomContacto, String fonoContacto){
        Pasajero pasajeroo = new Pasajero(id, nom, fono, nomContacto, fonoContacto);
        pasajeros.add(pasajeroo);
        return true;
    }

    public boolean createBus(String patente, String marca, String modelo, int nroAsientos){
        Bus buss = new Bus(patente, marca, modelo, nroAsientos);
        buses.add(buss);
        return true;
    }

    public boolean createViaje (LocalDate fecha, LocalTime hora, int precio, String patBus){
        Viaje viajee = new Viaje(fecha, hora, precio, patBus);
            viajes.add(viajee);
            return true;
    }

    public boolean iniciaVenta (String idDoc, TipoDocumento tipo, LocalDate fechaVenta, IdPersona idCliente){
        Cliente clienteEncontrado = findCliente(idCliente);
        if (clienteEncontrado== null){
            return false;
        }
        Venta ventaa = new Venta(idDoc, tipo, fechaVenta, clienteEncontrado);
        ventas.add(ventaa);
        return true;
    }

    public String[][] getHorariosDisponibles(LocalDate fechaViaje) {
        return new String[0][0];
    }

    public String[][] listAsientosDeViaje(LocalDate fecha, LocalTime hora, String patBus){
        return new String[0][0];
    }

    public int getMontoVenta (String idDocumento, TipoDocumento tipo){
        return 0;
    }

    public String getNombrePasajero (IdPersona idPasajero){
        Pasajero BuscarPasajero = findPasajero(idPasajero);
        if (BuscarPasajero == null) {
            return "Pasajero no existe";
        }

        return BuscarPasajero.getNombre().toString();
    }

    public boolean vendePasaje (String idDoc, LocalDate fecha, LocalTime hora, String patBus, int asiento, IdPersona idPasajero){
        return false;
    }

    public String[][] listVentas(){
        return new String[0][0];
    }

    public String[][] listViajes(){
        return new String[0][0];
    }

    public String[][] listPasajeros (LocalDate fecha, LocalTime hora, String patBus){
        return new String[0][0];
    }

    private Cliente findCliente(IdPersona id) {
        for (int i = 0; i < clientes.size(); i++) {

            Cliente cliente = clientes.get(i);

            if (cliente.getId().getValor().equals(id.getValor())) {
                return cliente;
            }
        }
        return null;
    }


    private Venta findVenta (String idDocumento, TipoDocumento tipoDocumento){
        for (int i = 0; i < ventas.size(); i++) {

            Venta venta = ventas.get(i);

            if (venta.getIdDocumento().equals(idDocumento)) {
                return venta;
            }
        }
        return null;
    }

    private Bus findBus (String patente){
        for (int i = 0; i < buses.size(); i++) {

            Bus bus = buses.get(i);

            if (bus.getPatente().equals(patente)) {
                return bus;
            }
        }
        return null;
    }
    private Viaje findViaje (LocalDate fecha, LocalTime hora, String patenteBus){
        for (int i = 0; i < viajes.size(); i++) {
            Viaje viaje = viajes.get(i);

            if (viaje.getFecha().equals(fecha)) {
                if (viaje.getHora().equals(hora)) {
                    if (viaje.getBus().getPatente().equals(patenteBus)) {
                        return viaje;
                    }
                }
            }
        }
        return null;
    }
    private Pasajero  findPasajero (IdPersona idPersona){
            for (int i = 0; i < pasajeros.size(); i++) {

                Pasajero pasajero = pasajeros.get(i);

                if (pasajero.getId().getValor().equals(idPersona.getValor())) {
                    return pasajero;
                }
            }
            return null;
        }


}