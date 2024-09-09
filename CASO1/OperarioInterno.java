public class OperarioInterno extends Thread {
    private CintaTransportadora cinta;
    private Deposito depositoOrigen;
    private Deposito depositoDestino;

    public OperarioInterno(CintaTransportadora cinta, Deposito origen, Deposito destino) {
        this.cinta = cinta;
        this.depositoOrigen = origen;
        this.depositoDestino = destino;
    }

    @Override
    public void run() {
        while (true) {
            // Mueve productos desde el depósito de producción a la cinta
            Producto producto = depositoOrigen.retirarProducto("A");  // O "B" dependiendo de la implementación
            if (producto.getTipo().startsWith("FIN")) {
                break;
            }
            cinta.moverProducto(producto);

            // Mueve el producto de la cinta al depósito de distribución
            Producto movido = cinta.retirarProducto();
            depositoDestino.almacenarProducto(movido);
        }
    }
}
