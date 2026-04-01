/*  2. Inventario de Alimentos (Control de Caducidad)
Un supermercado necesita gestionar su estante de lácteos. Los productos que vencen más pronto deben colocarse al principio para ser vendidos primero.

La Clase Producto (Nodo): Debe contener nombre (String), cantidad (int) y diasParaVencer (int).
El Problema: Si llega un producto que vence en menos de 3 días, debe insertarse al inicio de la lista (prioridad de venta). Si vence en más tiempo, se pone al final.
Reto: Crea un método que imprima solo los productos que tienen menos de 5 días para vencer. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        listaproductos estante = new listaproductos();

        do {
            System.out.println("\n==============================================");
            System.out.println("   INVENTARIO LÁCTEOS - Menú Principal");
            System.out.println("==============================================");
            System.out.println("1. Agregar producto");
            System.out.println("2. Ver estante completo");
            System.out.println("3. Ver productos próximos a vencer (< 5 días)");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println("==============================================");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            System.out.println();

            switch (opcion) {

                case 1:
                    sc.nextLine();
                    System.out.print("Nombre del producto    : ");
                    String nombre = sc.nextLine();
                    System.out.print("Cantidad               : ");
                    int cantidad = sc.nextInt();
                    System.out.print("Días para vencer       : ");
                    int dias = sc.nextInt();

                    producto nuevo = new producto(nombre, cantidad, dias);
                    estante.agregar(nuevo);
                    break;

                case 2:
                    estante.imprimirEstante();
                    break;

                case 3:
                    estante.imprimirProximosAVencer();
                    break;

                case 0:
                    System.out.println("¡Saliendo del módulo de Inventario Lácteos!");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }
}