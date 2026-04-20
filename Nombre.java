public class Nombre {

    private Tratamiento tratamiento;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;

    public Nombre(Tratamiento tratamiento, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.tratamiento = tratamiento;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;

    }

    public Tratamiento getTratamiento() {

        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @Override
    public String toString() {
        return "Tratamiento"+ tratamiento + "Nombre:" + nombre + "Apellido Paterno: " + apellidoPaterno + "Apellido Materno: "+  apellidoMaterno;

    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (otro == null || getClass() != otro.getClass()) return false;

        Nombre n = (Nombre) otro;

        return nombre.equals(n.nombre) &&
                apellidoPaterno.equals(n.apellidoPaterno) &&
                apellidoMaterno.equals(n.apellidoMaterno);

    }


}