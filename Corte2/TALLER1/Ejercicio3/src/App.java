/*3. Torre de Control (Aterrizajes de Emergencia)
Simula la cola de aviones esperando para aterrizar en un aeropuerto congestionado.

La Clase Vuelo (Nodo): Debe contener numeroVuelo (String), aerolinea (String), combustibleRestante (int) y pasajeros (int).
El Problema: Normalmente los vuelos se forman al final de la cola. Sin embargo, si un vuelo reporta menos de 10 unidades de combustible, debe ser movido inmediatamente al inicio de la lista (Cabeza).
Reto: Implementar el método reportarEmergencia(String numeroVuelo) que busque un vuelo en la cola y lo mueva al principio */

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        ColaTorre torre = new ColaTorre();

        do {
            System.out.println("\n==============================================");
            System.out.println("   TORRE DE CONTROL - Menú Principal");
            System.out.println("==============================================");
            System.out.println("1. Agregar vuelo a la cola");
            System.out.println("2. Reportar emergencia (mover al inicio)");
            System.out.println("3. Ver cola de aterrizaje");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println("==============================================");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (opcion) {

                case 1:
                    System.out.print("Número de vuelo   : ");
                    String numeroVuelo = sc.nextLine();
                    System.out.print("Aerolínea         : ");
                    String aerolinea = sc.nextLine();
                    System.out.print("Combustible restante: ");
                    int combustible = sc.nextInt();
                    System.out.print("Pasajeros         : ");
                    int pasajeros = sc.nextInt();
                    sc.nextLine();

                    Vuelo nuevo = new Vuelo(numeroVuelo, aerolinea, combustible, pasajeros);
                    torre.agregarVuelo(nuevo);
                    break;

                case 2:
                    System.out.print("Número de vuelo en emergencia: ");
                    String vuelo = sc.nextLine();
                    torre.reportarEmergencia(vuelo);
                    break;

                case 3:
                    torre.imprimirCola();
                    break;

                case 0:
                    System.out.println("¡Saliendo del módulo Torre de Control!");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
