public class CintaTransportadora {
    private Producto producto;

    public synchronized void moverProducto(Producto producto) {
        while (this.producto != null) {
            try {
                wait();  // Espera a que la cinta esté libre
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        this.producto = producto;
        System.out.println("Producto movido a la cinta: " + producto.getTipo());
        notifyAll();
    }

    public synchronized Producto retirarProducto() {
        while (this.producto == null) {
            try {
                wait();  // Espera hasta que haya un producto en la cinta
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Producto temp = this.producto;
        this.producto = null;
        notifyAll();  // Notifica a los threads en espera
        return temp;
    }
}
