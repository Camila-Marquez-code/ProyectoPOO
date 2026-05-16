//Marisol Yañez Borquez
public class PagoTarjeta extends Pago {

    private long nroTarjeta;

    Public PagoTarjeta (int monto, long nroTarjeta){
        this.nroTarjeta=nroTarjeta;
        super(monto);
    }

    public long getNroTarjeta() {
        return nroTarjeta;
    }
}