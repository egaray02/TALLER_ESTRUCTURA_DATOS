/*3. Logística de Distribución (Callejón sin Salida)
Un centro de logística urbana tiene un muelle de carga ubicado al final de un callejón muy estrecho. Los camiones de reparto entran uno tras otro y quedan "atrapados" en el orden de llegada. El último camión en entrar debe ser obligatoriamente el primero en salir para permitir que los demás se retiren.

La Clase Camion (Nodo): Debe contener placa (String), conductor (String) y cargaToneladas (double).
El Problema: El supervisor necesita saber cuánta carga total hay en el callejón sin mover los camiones físicamente (solo consultando la estructura).
Reto: Implementa un método que recorra la pila (sin destruirla permanentemente, o reconstruyéndola) y calcule la suma total de cargaToneladas de todos los camiones estacionados. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        PilaCallejon callejon = new PilaCallejon();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE LOGÍSTICA DE DISTRIBUCIÓN (PILAS) ---");
            System.out.println("1. Ver camiones en el callejón");
            System.out.println("2. Registrar nuevo camión (Push)");
            System.out.println("3. Retirar camión de la entrada (Pop)");
            System.out.println("4. Calcular carga total en el callejón");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nEstado actual del callejón:");
                    callejon.imprimir();
                    break;
                case 2:
                    System.out.print("Placa del camión: "); String placa = scanner.nextLine();
                    System.out.print("Conductor: "); String conductor = scanner.nextLine();
                    System.out.print("Carga (toneladas): "); double carga = scanner.nextDouble();
                    scanner.nextLine();
                    callejon.push(new Camion(placa, conductor, carga));
                    System.out.println("Camión registrado en el callejón.");
                    break;
                case 3:
                    Camion retirado = callejon.pop();
                    if (retirado != null) {
                        System.out.println("Camión retirado: " + retirado);
                    } else {
                        System.out.println("El callejón está vacío.");
                    }
                    break;
                case 4:
                    double totalCarga = callejon.calcularCargaTotal();
                    System.out.printf("Carga total en el callejón: %.2f toneladas%n", totalCarga);
                    break;
                case 5:
                    System.out.println("Cerrando sistema de logística...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}