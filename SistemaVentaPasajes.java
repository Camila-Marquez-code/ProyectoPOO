package controlador;
//Camila Valeska Márquez Burgos

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import modelo.*;
import utilidades.*;

public class SistemaVentaPasajes {
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayList<Pasajero> pasajeros = new ArrayList<Pasajero>();
    private ArrayList<Bus> buses = new ArrayList<Bus>();
    private ArrayList<Viaje> viajes = new ArrayList<Viaje>();
    private ArrayList<Venta> ventas = new ArrayList<Venta>();

    public boolean createCliente(IdPersona id, Nombre nom, String fono, String email) {
        Optional<Cliente> optionalCliente = findCliente(id);
        Cliente existente;

        if (optionalCliente.isPresent()) {
            existente = optionalCliente.get();
        } else {
            existente = null;
        }

        if (existente != null) {
            return false;
        }

        Cliente cliente = new Cliente(id, nom, email);
        cliente.setTelefono(fono);
        clientes.add(cliente);
        return true;
    }

    public boolean createPasajero(IdPersona idPersona, Nombre nom, String fono, Nombre nomContacto, String fonoContacto) {
        if (findPasajero(idPersona).isPresent()) {
            return false;
        }
        Pasajero pasajero = new Pasajero(idPersona, nom);
        pasajero.setTelefono(fono);
        pasajero.setNomContacto(nomContacto);
        pasajero.setFonoContacto(fonoContacto);
        pasajeros.add(pasajero);
        return true;
    }

    public boolean createBus(String patente, String marca, String modelo, int nroAsientos) {
        Optional<Bus> optionalBus = findBus(patente);

        if (optionalBus.isPresent()) {
            return false;
        }

        Bus bus = new Bus(patente, nroAsientos);
        bus.setMarca(marca);
        bus.setModelo(modelo);
        buses.add(bus);
        return true;
    }

    public boolean createViaje(LocalDate fecha, LocalTime hora, int precio, String patBus) {
        Optional<Bus> optionalBus = findBus(patBus);
        Bus busEncontrado;

        if (optionalBus.isPresent()) {
            busEncontrado = optionalBus.get();
        } else {
            busEncontrado = null;
        }

        if (busEncontrado == null) {
            return false;
        }

        Optional<Viaje> optionalViaje = findViaje(fecha.toString(), hora.toString(), patBus);
        Viaje viajeExistente;

        if (optionalViaje.isPresent()) {
            viajeExistente = optionalViaje.get();
        } else {
            viajeExistente = null;
        }

        if (viajeExistente != null) {
            return false;
        }

        Viaje viaje = new Viaje(fecha, hora, precio, busEncontrado, 0, null, null, null, null);
        viajes.add(viaje);
        busEncontrado.addViaje(viaje);
        return true;
    }

    public boolean iniciaVenta(String idDoc, TipoDocumento tipo, LocalDate fechaVenta, IdPersona idCliente) {
        Optional<Venta> optionalVenta = findVenta(idDoc, tipo);
        Venta existente;

        if (optionalVenta.isPresent()) {
            existente = optionalVenta.get();
        } else {
            existente = null;
        }

        if (existente != null) {
            return false;
        }

        Optional<Cliente> optionalCliente = findCliente(idCliente);
        Cliente clienteEncontrado;

        if (optionalCliente.isPresent()) {
            clienteEncontrado = optionalCliente.get();
        } else {
            clienteEncontrado = null;
        }

        if (clienteEncontrado == null) {
            return false;
        }

        Venta venta = new Venta(idDoc, tipo, fechaVenta, clienteEncontrado);
        ventas.add(venta);
        clienteEncontrado.addVenta(venta);
        return true;
    }

    public String[][] getHorariosDisponibles(LocalDate fechaViaje) {
        ArrayList<Viaje> lista = new ArrayList<>();
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).getFecha().equals(fechaViaje)) {
                lista.add(viajes.get(i));
            }
        }
        String[][] resultado = new String[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i][0] = lista.get(i).getBus().getPatente();
            resultado[i][1] = lista.get(i).getHora().toString();
            resultado[i][2] = String.valueOf(lista.get(i).getPrecio());
            resultado[i][3] = String.valueOf(lista.get(i).getNroAsientosDisponibles());
        }

        return resultado;
    }

    public String[][] listAsientosDeViaje(LocalDate fecha, LocalTime hora, String patBus) {
        Optional<Viaje> optionalViaje = findViaje(fecha.toString(), hora.toString(), patBus);
        Viaje viaje;

        if (optionalViaje.isPresent()) {
            viaje = optionalViaje.get();
        } else {
            viaje = null;
        }

        if (viaje == null) {
            return new String[0][0];
        }

        return viaje.getAsientos();
    }

    public Optional<Integer> getMontoVenta(String idDocumento, TipoDocumento tipo) {
        Optional<Venta> optionalVenta = findVenta(idDocumento, tipo);
        Venta venta;

        if (optionalVenta.isPresent()) {
            venta = optionalVenta.get();
        } else {
            venta = null;
        }

        if (venta == null) {
            return Optional.empty();
        }

        return Optional.of(venta.getMonto());
    }

    public Optional<String> getNombrePasajero(IdPersona idPasajero) {
        Optional<Pasajero> optionalPasajero = findPasajero(idPasajero);
        Pasajero pasajero;

        if (optionalPasajero.isPresent()) {
            pasajero = optionalPasajero.get();
        } else {
            pasajero = null;
        }

        if (pasajero == null) {
            return Optional.empty();
        }

        return Optional.of(pasajero.getNombreCompleto().toString());
    }

    public boolean vendePasaje(String idDoc, LocalDate fecha, LocalTime hora, String patBus, int asiento, IdPersona idPasajero) {

        Optional<Venta> optionalVenta = findVenta(idDoc, TipoDocumento.BOLETA);
        Venta venta;

        if (optionalVenta.isPresent()) {
            venta = optionalVenta.get();
        } else {
            optionalVenta = findVenta(idDoc, TipoDocumento.FACTURA);

            if (optionalVenta.isPresent()) {
                venta = optionalVenta.get();
            } else {
                venta = null;
            }
        }

        if (venta == null) {
            return false;
        }

        Optional<Viaje> optionalViaje = findViaje(fecha.toString(), hora.toString(), patBus);
        Viaje viaje;

        if (optionalViaje.isPresent()) {
            viaje = optionalViaje.get();
        } else {
            viaje = null;
        }

        if (viaje == null) {
            return false;
        }

        Optional<Pasajero> optionalPasajero = findPasajero(idPasajero);
        Pasajero pasajero;

        if (optionalPasajero.isPresent()) {
            pasajero = optionalPasajero.get();
        } else {
            pasajero = null;
        }

        if (pasajero == null) {
            return false;
        }

        if (asiento < 1 || asiento > viaje.getBus().getNroAsientos()) {
            return false;
        }

        String[][] asientos = viaje.getAsientos();
        for (int i = 0; i < asientos.length; i++) {
            if (asientos[i][0].equals(String.valueOf(asiento))
                    && asientos[i][1].equals("*")) {
                return false;
            }
        }

        venta.createPasaje(asiento, viaje, pasajero);
        return true;
    }

    public String[][] listPasajeros(LocalDate fecha, LocalTime hora, String patBus) {
        Optional<Viaje> optionalViaje = findViaje(fecha.toString(), hora.toString(), patBus);
        Viaje viaje;

        if (optionalViaje.isPresent()) {
            viaje = optionalViaje.get();
        } else {
            viaje = null;
        }

        if (viaje == null) {
            return new String[0][0];
        }

        return viaje.getListaPasajeros();
    }

    public String[][] listViajes() {
        String[][] resultado = new String[viajes.size()][5];
        for (int i = 0; i < viajes.size(); i++) {
            Viaje viaj = viajes.get(i);
            resultado[i][0] = viaj.getFecha().toString();
            resultado[i][1] = viaj.getHora().toString();
            resultado[i][2] = String.valueOf(viaj.getPrecio());
            resultado[i][3] = String.valueOf(viaj.getNroAsientosDisponibles());
            resultado[i][4] = viaj.getBus().getPatente();
        }

        return resultado;
    }

    private Optional<Cliente> findCliente(IdPersona id) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.getIdPersona().equals(id)) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    private Optional<Venta> findVenta(String idDocumento, TipoDocumento tipoDocumento) {
        for (int i = 0; i < ventas.size(); i++) {
            Venta venta = ventas.get(i);

            if (venta.getIdDocumento().equals(idDocumento)
                    && venta.getTipo() == tipoDocumento) {
                return Optional.of(venta);
            }
        }

        return Optional.empty();
    }

    private Optional<Bus> findBus(String patente) {
    /* Incluido a pesar de no estar en el UML debido a su presencia
       en la entrega 1, de no ser necesario no considerar en esta entrega.
       Se incluye el Optional<dato> al igual que en los otros find
    */
        for (int i = 0; i < buses.size(); i++) {
            Bus bus = buses.get(i);

            if (bus.getPatente().equals(patente)) {
                return Optional.of(bus);
            }
        }

        return Optional.empty();
    }

    private Optional<Viaje> findViaje(String fecha, String hora, String patenteBus) {
        for (int i = 0; i < viajes.size(); i++) {
            Viaje viaje = viajes.get(i);

            if (viaje.getFecha().toString().equals(fecha)
                    && viaje.getHora().toString().equals(hora)
                    && viaje.getBus().getPatente().equals(patenteBus)) {
                return Optional.of(viaje);
            }
        }

        return Optional.empty();
    }

    private Optional<Pasajero> findPasajero(IdPersona idPersona) {
        for (int i = 0; i < pasajeros.size(); i++) {
            Pasajero pasajero = pasajeros.get(i);

            if (pasajero.getIdPersona().equals(idPersona)) {
                return Optional.of(pasajero);
            }
        }

        return Optional.empty();
    }
}