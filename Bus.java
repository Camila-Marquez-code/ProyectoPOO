import java.util.*;

public class Bus {
    private String patente;
    private String marca;
    private String modelo;
    private int nroAsientos;
    private List<Viaje> viajes = new ArrayList<Viaje>();

    public Bus(String patente, int nroAsientos) {
        this.patente = patente;
        this.nroAsientos = nroAsientos;
    }

    public String getPatente(){
        return patente;
    }

    public int getNroAsientos(){
        return nroAsientos;
    }

    public void setmarca(String marca){
        this.marca = marca;
    }

    public String getmodelo(){
        return modelo;
    }

    public void setmodelo(String modelo){
        this.modelo = modelo;
    }

    public void addViaje(Viaje viaje){
        viajes.add(viaje);
    }
}