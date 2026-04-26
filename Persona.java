public class Persona {

    private IdPersona idPersona;
    private Nombre nombreCompleto;
    private String telefono;


    public Persona(IdPersona idPersona, Nombre nombreCompleto) {
        this.idPersona = idPersona;
        this.nombreCompleto = nombreCompleto;
    }

    public IdPersona getIdPersona() {
        return idPersona;
    }
    public Nombre getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto (Nombre nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }


    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Persona (idPersona: " + idPersona + ", nombre Completo: " + nombreCompleto + ", telefono: " + telefono + ")";
    }
    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (otro == null || getClass() != otro.getClass()) return false;

        Persona persona = (Persona) otro;
        return idPersona.equals(persona.idPersona);
    }
    
}
