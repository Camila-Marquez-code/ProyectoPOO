package Proyecto;

public class Pasaporte {
    private String numero;
    private String nacionalidad;

    private Pasaporte(String numero, String nacionalidad) {
        this.numero = numero;
        this.nacionalidad = nacionalidad;
    }

    public String getnumero() {
        return this.numero;
    }

    public String getnacionalidad() {
        return this.nacionalidad;
    }

    public static Pasaporte of(String num, String nacionalidad) {
        return new Pasaporte (num, nacionalidad);
    }
}