public class Distribuidor extends Thread {
    private Deposito depositoDistribucion;
    private String tipoProducto;

    public Distribuidor(Deposito deposito, String tipo) {
        this.depositoDistribucion = deposito;
        this.tipoProducto = tipo;
    }

    @Override
    public void run() {
        while (true) {
            Producto producto = depositoDistribucion.retirarProducto(tipoProducto);
            if (producto != null && producto.getTipo().equals("FIN_" + tipoProducto)) {
                break;  // Termina cuando encuentra el producto de fin
            }
        }
    }
}
