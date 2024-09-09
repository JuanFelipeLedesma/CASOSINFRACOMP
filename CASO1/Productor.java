public class Productor extends Thread {
    private Deposito depositoProduccion;
    private String tipoProducto;
    private int numProductos;

    public Productor(Deposito deposito, String tipo, int numProductos) {
        this.depositoProduccion = deposito;
        this.tipoProducto = tipo;
        this.numProductos = numProductos;
    }

    @Override
    public void run() {
        for (int i = 0; i < numProductos; i++) {
            depositoProduccion.almacenarProducto(new Producto(tipoProducto));
        }
        // Producto de terminación
        depositoProduccion.almacenarProducto(new Producto("FIN_" + tipoProducto));
    }
}
