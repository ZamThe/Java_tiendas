import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Clase que representa un producto
class Producto {
    String nombre;
    double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
}

// Clase que representa a un cliente
class Cliente {
    String nombre;
    List<Producto> productos;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
}

// Clase que representa a una cajera
class Cajera {
    String nombre;

    public Cajera(String nombre) {
        this.nombre = nombre;
    }

    // Método que simula el proceso de cobro
    public void cobrar(List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            System.out.println("La cajera " + nombre + " está cobrando al cliente " + cliente.nombre);

            // Simulamos el tiempo que toma cobrar cada producto
            for (Producto producto : cliente.productos) {
                System.out.println("Cobrando " + producto.nombre + " por $" + producto.precio);
                try {
                    Thread.sleep(1000); // Simula el tiempo de cobro por cada producto (1 segundo)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            double total = calcularTotal(cliente);
            double iva = total * 0.12; // Supongamos que el IVA es del 12%
            System.out.println("El total a pagar para el cliente " + cliente.nombre + " es: $" + total);
            System.out.println("El IVA a pagar es: $" + iva);
            System.out.println("El tiempo total de cobro fue de " + cliente.productos.size() + " segundos.");
            System.out.println();
        }
    }

    private double calcularTotal(Cliente cliente) {
        double total = 0;
        for (Producto producto : cliente.productos) {
            total += producto.precio;
        }
        return total;
    }
}

// Clase principal que simula el proceso
public class Supermercado {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar nombre de la cajera
        System.out.print("Ingrese el nombre de la cajera: ");
        String nombreCajera = scanner.nextLine();

        // Solicitar cantidad de clientes en la cola
        System.out.print("Ingrese la cantidad de clientes en la cola: ");
        int cantidadClientes = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        // Crear cajera
        Cajera cajera = new Cajera(nombreCajera);

        // Crear lista de clientes
        List<Cliente> clientes = new ArrayList<>();

        // Solicitar información de cada cliente
        for (int i = 0; i < cantidadClientes; i++) {
            System.out.print("Ingrese el nombre del cliente " + (i + 1) + ": ");
            String nombreCliente = scanner.nextLine();

            Cliente cliente = new Cliente(nombreCliente);

            System.out.print("Ingrese la cantidad de productos que va a comprar: ");
            int cantidadProductos = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            for (int j = 0; j < cantidadProductos; j++) {
                System.out.print("Ingrese el nombre del producto " + (j + 1) + ": ");
                String nombreProducto = scanner.nextLine();

                System.out.print("Ingrese el precio del producto " + (j + 1) + ": ");
                double precioProducto = scanner.nextDouble();
                scanner.nextLine(); // Consumir el salto de línea

                Producto producto = new Producto(nombreProducto, precioProducto);
                cliente.agregarProducto(producto);
            }

            clientes.add(cliente);
        }

        // Simular el proceso de cobro para todos los clientes
        cajera.cobrar(clientes);

        // Cerrar el scanner al finalizar
        scanner.close();
    }
}
