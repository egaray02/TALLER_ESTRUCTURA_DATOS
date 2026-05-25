/*5. Fábrica de Pedidos (E-commerce)
Una tienda en línea recibe pedidos de manera continua. Cada pedido ingresa a una cola central de procesamiento. El sistema de bodega toma los pedidos en orden de llegada para preparar el envío. Si un pedido ya fue cancelado por el cliente, debe ser descartado automáticamente al momento de ser procesado.

La Clase Pedido (Nodo): Debe contener numeroPedido (String), cliente (String), totalPagar (double) y cancelado (boolean).
El Problema: La bodega necesita procesar solo los pedidos vigentes. Los cancelados deben ser eliminados sin ser despachados, pero registrados en un conteo.
Reto: Implementa un método procesarPedidos() que recorra y vacíe la cola. Para cada pedido: si cancelado = true, lo descarta e incrementa un contador de cancelados; si cancelado = false, lo "despacha" imprimiendo sus datos. Al finalizar, muestra el total despachado y el total cancelado. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        ColaPedidos colaPedidos = new ColaPedidos();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE PEDIDOS E-COMMERCE ---");
            System.out.println("1. Ver pedidos en cola");
            System.out.println("2. Registrar pedido");
            System.out.println("3. Procesar siguiente pedido");
            System.out.println("4. Procesar todos los pedidos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nEstado actual de la cola:");
                    colaPedidos.imprimir();
                    break;
                case 2:
                    System.out.print("Número de pedido: "); String numeroPedido = scanner.nextLine();
                    System.out.print("Cliente: "); String cliente = scanner.nextLine();
                    System.out.print("Total a pagar: "); double totalPagar = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("¿Está cancelado? (true/false): "); boolean cancelado = scanner.nextBoolean();
                    scanner.nextLine();
                    colaPedidos.enqueue(new Pedido(numeroPedido, cliente, totalPagar, cancelado));
                    System.out.println("Pedido registrado correctamente.");
                    break;
                case 3:
                    Pedido siguiente = colaPedidos.dequeue();
                    if (siguiente != null) {
                        System.out.println("\nProcesando pedido:");
                        System.out.println("  Número  : " + siguiente.numeroPedido);
                        System.out.println("  Cliente : " + siguiente.cliente);
                        System.out.println("  Total   : $" + siguiente.totalPagar);
                        System.out.println("  Estado  : " + (siguiente.cancelado ? "Cancelado - descartado" : "Vigente - despachado"));
                    } else {
                        System.out.println("No hay pedidos en la cola.");
                    }
                    break;
                case 4:
                    colaPedidos.procesarPedidos();
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
