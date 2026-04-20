package use;

import java.time.LocalDate;
import java.util.ArrayList;

public class Venta {
    private String idDocumento;
    private TipoDocumento tipo;
    private LocalDate fecha;
    private Cliente cliente;
    private List<Pasaje> pasajes;

    public Venta (String id, TipoDocumento tipo, LocalDate fec, Cliente cli) {
        this.idDocumento = id;
        this.tipo = tipo;
        this.fecha = fec;
        this.cliente = cli;
        this.pasajes = new ArrayList<>();

    }

    public String getIdDocumento() {
        return idDocumento;
    }
    public TipoDocumento getTipo() {
        return tipo;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public Cliente getCliente() {
        return cliente;
    }

    public void createPasaje (int asiento, Viaje viaje, Pasajero pasajero) {
        Pasaje nuevo = new Pasaje(asiento, viaje, pasajero);
        pasajes.add(nuevo);

    }

    //Aun por terminar y mejorar


}