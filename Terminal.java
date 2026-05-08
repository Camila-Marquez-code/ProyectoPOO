//Camila Valeska Márquez Burgos
public class Terminal {
    private String nombre;
    private Direccion direccion;

    public Terminal(String nombre, Direccion direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void addLLegada(Viaje viaje) {
        //Desarrollar
    }

    public void addSalida(Viaje viaje) {
        //Desarrollar
    }

    public Viaje[] getLlegadas() {
        //Desarrollar
    }

    public Viaje[] getSalidas() {
        //Desarrollar
    }
}

