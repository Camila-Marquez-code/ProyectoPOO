import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    private String idDocumento;
    private TipoDocumento tipo;
    private LocalDate fecha;
    private Cliente cliente;
    private List<Pasaje> pasajes;

    public Venta (String id, TipoDocumento tipo, LocalDate fecha, Cliente cliente) {
        this.idDocumento = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.cliente = cliente;
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
        viaje.addPasaje(nuevo);

    }
    public int getMonto() {
        int total = 0;
        for (Pasaje p : pasajes) {
            total += p.getViaje().getBus().getNroAsientos(); // simplificado
        }
        return total;
    }

}
