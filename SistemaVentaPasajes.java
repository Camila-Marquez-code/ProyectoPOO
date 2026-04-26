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
        Cliente existente = findCliente(id);
        if (existente != null){
            return false;
        }


        Cliente cliente = new Cliente(id, nom, email);
        clientes.add(cliente);
        return true;
    }

    public boolean createPasajero (IdPersona id, Nombre nom, String fono, Nombre nomContacto, String fonoContacto){
        Pasajero existente = findPasajero(id);
        if (existente != null){
            return false;
        }


        Pasajero pasajeroo = new Pasajero(id, nom, fono, nomContacto, fonoContacto);
        pasajeros.add(pasajeroo);
        return true;
    }

    public boolean createBus(String patente, String marca, String modelo, int nroAsientos){
        Bus existente = findBus(patente);
        if (existente != null){
            return false;
        }

        Bus buss = new Bus(patente, marca, modelo, nroAsientos);
        buses.add(buss);
        return true;
    }

    public boolean createViaje (LocalDate fecha, LocalTime hora, int precio, String patBus){
        Bus busEncontrado = findBus(patBus);
        if (busEncontrado == null){
            return false;
        }
        Viaje viajee = new Viaje(fecha, hora, precio, busEncontrado);
        viajes.add(viajee);
        return true;
    }

    public boolean iniciaVenta (String idDoc, TipoDocumento tipo, LocalDate fechaVenta, IdPersona idCliente){
        Venta existente = findVenta(idDoc, tipo);
        if (existente != null){
            return false;
        }
        Cliente clienteEncontrado = findCliente(idCliente);
        if (clienteEncontrado== null){
            return false;
        }
        Venta ventaa = new Venta(idDoc, tipo, fechaVenta, clienteEncontrado);
        ventas.add(ventaa);
        clienteEncontrado.addVenta(ventaa);
        return true;
    }

    public String[][] getHorariosDisponibles(LocalDate fechaViaje) {
        ArrayList<Viaje> lista = new ArrayList<>();
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getFecha().equals(fechaViaje)) {
                lista.add(viajes.get(i));
            }
        }
        String[][] resultado = new String[lista.size()][2];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i][0] = lista.get(i).getHora().toString();
            resultado[i][1] = lista.get(i).getBus().getPatente();
        }

        return resultado;
    }

    public String[][] listAsientosDeViaje(LocalDate fecha, LocalTime hora, String patBus){
        Viaje viaje = findViaje(fecha, hora, patBus);
        if (viaje == null){
            return new String[0][0];
        }
        return viaje.getAsientos();
    }

    public int getMontoVenta (String idDocumento, TipoDocumento tipo){
        Venta venta = findVenta(idDocumento, tipo);
        if (venta == null){
            return 0;
        }
        return venta.getTotal();
    }

    public String getNombrePasajero (IdPersona idPasajero){
        Pasajero BuscarPasajero = findPasajero(idPasajero);
        if (BuscarPasajero == null) {
            return "Pasajero no existe";
        }

        return BuscarPasajero.getNombre().toString();
    }

    public boolean vendePasaje (String idDoc, LocalDate fecha, LocalTime hora, String patBus, int asiento, IdPersona idPasajero){
        Venta venta = null;
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getIdDocumento().equals(idDoc)) {
                venta = ventas.get(i);
                break;
            }
        }
        if (venta == null){
            return false;
        }
        Viaje viaje = findViaje(fecha, hora, patBus);
        if (viaje == null){
            return false;
        }
        boolean[] asientos = viaje.getAsientos();
        if (asiento < 0 || asiento >= asientos.length){
            return false;
        }
        Pasajero pasajero = findPasajero(idPasajero);
        if (pasajero == null){
            return false;
        }
        if (!viaje.asientoDisponible(asiento)){
            return false;
        }
        Pasaje pasaje = new Pasaje(viaje, asiento, pasajero);
        venta.agregarPasaje(pasaje);
        viaje.ocuparAsiento(asiento);

        return true;
    }

    public String[][] listVentas(){
        String[][] resultado = new String[ventas.size()][3];
        for (int i = 0; i < ventas.size(); i++) {
            Venta vent = ventas.get(i);
            resultado[i][0] = vent.getIdDocumento();
            resultado[i][1] = vent.getTipoDocumento().toString();
            resultado[i][2] = String.valueOf(vent.getTotal());
        }
        return resultado;
    }

    public String[][] listViajes(){
        String[][] resultado = new String[viajes.size()][4];
        for (int i = 0; i < viajes.size(); i++) {
            Viaje viaj = viajes.get(i);
            resultado[i][0] = viaj.getFecha().toString();
            resultado[i][1] = viaj.getHora().toString();
            resultado[i][2] = viaj.getBus().getPatente();
            resultado[i][3] = String.valueOf(viaj.getPrecio());
        }

        return resultado;
    }

    public String[][] listPasajeros (LocalDate fecha, LocalTime hora, String patBus){
        Viaje viaje = findViaje(fecha, hora, patBus);
        if (viaje == null){
            return new String[0][0];
        }
        ArrayList<Pasaje> pasajes = viaje.getPasajes();
        String[][] resultado = new String[pasajes.size()][2];
        for (int i = 0; i < pasajes.size(); i++) {
            resultado[i][0] = pasajes.get(i).getPasajero().getId().getValor();
            resultado[i][1] = pasajes.get(i).getPasajero().getNombre().toString();
        }
        return resultado;
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

            if (venta.getIdDocumento().equals(idDocumento) && venta.getTipoDocumento() == tipoDocumento){
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