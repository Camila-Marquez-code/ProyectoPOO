//Tomás Meza

public class Conductor extends Tripulante {

    public Conductor(IdPersona id, Nombre nom, Direccion direccion) {
        super(id, nom, direccion);
    }

    @Override
    public void addViaje(Viaje viaje) {
        viajes.add(viaje);
    }

    @Override
    public int getNroViajes() {
        return viajes.size();
    }
}