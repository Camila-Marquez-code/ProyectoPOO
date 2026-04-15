
 public class Mascota {


     protected String nombre;
     protected int edad;

     public Mascota(String nombre, int edad){
         this.nombre= nombre;
         this.edad=edad;
     }


     public void hacerSonido() {

       System.out.println ("Sonido: ");
     }

     public void mostrarInfo(){
      System.out.println("Nombre: " + nombre);
      System.out.println("Edad: "+ edad );

     }


 }