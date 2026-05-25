/*3. Impresora Compartida (Red de Oficina)
En una oficina de arquitectura, varias computadoras comparten una única impresora de gran formato. Cada vez que alguien envía un documento, este se agrega al final de la cola de impresión. La impresora procesa los trabajos en el orden exacto en que fueron recibidos.

La Clase Documento (Nodo): Debe contener nombreArchivo (String), usuario (String), numeroPaginas (int) y esColor (boolean).
El Problema: Antes de imprimir, el jefe de oficina quiere saber cuántas páginas en total tiene la cola de impresión pendiente.
Reto: Implementa un método calcularPaginasTotales() que recorra la cola y retorne la suma de numeroPaginas de todos los documentos en espera, sin alterar la cola.*/ 
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Impresion colaImpresion = new Impresion();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE IMPRESORA COMPARTIDA ---");
            System.out.println("1. Ver documentos en cola");
            System.out.println("2. Agregar documento");
            System.out.println("3. Imprimir siguiente documento");
            System.out.println("4. Calcular páginas totales en espera");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nEstado actual de la cola:");
                    colaImpresion.imprimir();
                    break;
                case 2:
                    System.out.print("Nombre del archivo: "); String nombreArchivo = scanner.nextLine();
                    System.out.print("Usuario: "); String usuario = scanner.nextLine();
                    System.out.print("Número de páginas: "); int paginas = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("¿Es a color? (true/false): "); boolean esColor = scanner.nextBoolean();
                    scanner.nextLine();
                    colaImpresion.enqueue(new Documento(nombreArchivo, usuario, paginas, esColor));
                    System.out.println("Documento agregado a la cola correctamente.");
                    break;
                case 3:
                    Documento impreso = colaImpresion.dequeue();
                    if (impreso != null) {
                        System.out.println("\nImprimiendo documento:");
                        System.out.println("  Archivo  : " + impreso.nombreArchivo);
                        System.out.println("  Usuario  : " + impreso.usuario);
                        System.out.println("  Páginas  : " + impreso.numeroPaginas);
                        System.out.println("  Tipo     : " + (impreso.esColor ? "Color" : "Blanco y Negro"));
                    } else {
                        System.out.println("No hay documentos en la cola.");
                    }
                    break;
                case 4:
                    int totalPaginas = colaImpresion.calcularPaginasTotales();
                    System.out.println("Total de páginas pendientes en cola: " + totalPaginas);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
