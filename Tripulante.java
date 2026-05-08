//Camila Valeska Márquez Burgos
public class Tripulante extends Persona {
    private Direccion direccion;

    public Tripulante(IdPersona idPersona, Nombre nombre, Direccion direccion) {
        super(idPersona, nombre);
        this.direccion = direccion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void addViaje(Viaje viaje) {
        //Desarrollar
    }

    public int getNroViaje() {
        //Desarrollar
    }
}
