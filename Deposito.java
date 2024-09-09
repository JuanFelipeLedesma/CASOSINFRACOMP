import java.util.LinkedList;
import java.util.List;

public class Deposito {
    private List<Producto> productos;
    private int capacidadMaxima;

    public Deposito(int capacidad) {
        this.capacidadMaxima = capacidad;
        this.productos = new LinkedList<>();
    }

    public synchronized boolean estaLleno() {
        return productos.size() >= capacidadMaxima;
    }

    public synchronized void almacenarProducto(Producto producto) {
        while (estaLleno()) {
            try {
                wait();  // Espera hasta que haya espacio
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        productos.add(producto);
        System.out.println("Producto almacenado: " + producto.getTipo());
        notifyAll();  // Notifica a todos los threads en espera
    }

    public synchronized Producto retirarProducto(String tipoProducto) {
        while (productos.isEmpty() || !hayProducto(tipoProducto)) {
            try {
                wait();  // Espera hasta que haya un producto disponible
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Producto producto = productos.stream().filter(p -> p.getTipo().equals(tipoProducto)).findFirst().orElse(null);
        if (producto != null) {
            productos.remove(producto);
            System.out.println("Producto retirado: " + producto.getTipo());
            notifyAll();  // Notifica a los threads en espera
        }
        return producto;
    }

    public synchronized boolean hayProducto(String tipoProducto) {
        return productos.stream().anyMatch(p -> p.getTipo().equals(tipoProducto));
    }
}
