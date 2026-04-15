public class Perro extends Mascota {

    private String raza;

    public Perro(String nombre, int edad, String raza) {
        super(nombre, edad);
        this.raza = raza;
    }

    @Override
    public void hacerSonido() {
        System.out.println("Guaaaaau");
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Raza: " + raza);
    }
}