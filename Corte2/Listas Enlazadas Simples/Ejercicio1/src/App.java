/* 1. El Tren de Carga (Logística Ferroviaria)
Diseña un sistema para gestionar los vagones de un tren de carga internacional.

La Clase Vagon (Nodo): Debe contener contenido (String), pesoToneladas (double), origen (String) y destino (String).
El Problema: El tren sale de una estación y se le van enganchando vagones al final.
Reto: Implementa un método que recorra el tren y calcule el peso total de toda la carga transportada. */



import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int opcion;

        colatren tren = new colatren();

        do {
            System.out.println("\n==============================================");
            System.out.println("   TREN DE CARGA - Menú Principal");
            System.out.println("==============================================");
            System.out.println("1. Nuevo vagon (Agregar al final del tren)");
            System.out.println("2. Calcular peso total de la carga");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println("==============================================");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            System.out.println();

            switch (opcion) {

                case 1:
                    System.out.print("Ingrese el contenido del vagon: ");
                    sc.nextLine();
                    String contenido = sc.nextLine();
                    System.out.print("Ingrese el peso del vagon (en toneladas): ");
                    double pesoToneladas = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Ingrese el origen del vagon: ");
                    String origen = sc.nextLine();
                    System.out.print("Ingrese el destino del vagon: ");
                    String destino = sc.nextLine();

                    vagon nuevoVagon = new vagon(contenido, pesoToneladas, origen, destino);
                    tren.agregarVagon(nuevoVagon);
                    break;
                case 2:
                    double pesoTotal = tren.calcularPesoTotal();
                    System.out.println("El peso total de la carga es: " + pesoTotal + " toneladas.");
                    break;
                case 0:
                    System.out.println("¡Saliendo del módulo de Tren de Carga!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
