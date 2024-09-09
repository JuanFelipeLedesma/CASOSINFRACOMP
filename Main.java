import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer parámetros desde consola
        System.out.print("Ingrese el número de productos: ");
        int numProductos = scanner.nextInt();
        System.out.print("Ingrese la capacidad del depósito de producción: ");
        int capDepProd = scanner.nextInt();
        System.out.print("Ingrese la capacidad del depósito de distribución: ");
        int capDepDist = scanner.nextInt();

        // Crear depósitos
        Deposito depositoProduccion = new Deposito(capDepProd);
        Deposito depositoDistribucion = new Deposito(capDepDist);
        
        // Crear cinta transportadora
        CintaTransportadora cinta = new CintaTransportadora();

        // Crear operarios internos
        OperarioInterno operarioInterno1 = new OperarioInterno(cinta, depositoProduccion, depositoDistribucion);
        OperarioInterno operarioInterno2 = new OperarioInterno(cinta, depositoProduccion, depositoDistribucion);

        // Crear productores y distribuidores
        Productor productorA1 = new Productor(depositoProduccion, "A", numProductos);
        Productor productorA2 = new Productor(depositoProduccion, "A", numProductos);
        Productor productorB1 = new Productor(depositoProduccion, "B", numProductos);
        Productor productorB2 = new Productor(depositoProduccion, "B", numProductos);

        Distribuidor distribuidorA1 = new Distribuidor(depositoDistribucion, "A");
        Distribuidor distribuidorA2 = new Distribuidor(depositoDistribucion, "A");
        Distribuidor distribuidorB1 = new Distribuidor(depositoDistribucion, "B");
        Distribuidor distribuidorB2 = new Distribuidor(depositoDistribucion, "B");

        // Iniciar hilos
        productorA1.start();
        productorA2.start();
        productorB1.start();
        productorB2.start();
        
        distribuidorA1.start();
        distribuidorA2.start();
        distribuidorB1.start();
        distribuidorB2.start();

        operarioInterno1.start();
        operarioInterno2.start();

        try {
            // Esperar a que todos los hilos terminen
            productorA1.join();
            productorA2.join();
            productorB1.join();
            productorB2.join();
            
            distribuidorA1.join();
            distribuidorA2.join();
            distribuidorB1.join();
            distribuidorB2.join();
            
            operarioInterno1.join();
            operarioInterno2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
