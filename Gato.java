public class Gato extends Mascota {

        private boolean esDeInterior;

        public Gato(String nombre, int edad, boolean esDeInterior) {
            super(nombre, edad);
            this.esDeInterior = esDeInterior;
        }

        @Override
        public void hacerSonido() {
            System.out.println("MIAAAAAU");
        }

        @Override
        public void mostrarInfo() {
            super.mostrarInfo();
            System.out.println("Es de interior " + esDeInterior);
        }
    }
