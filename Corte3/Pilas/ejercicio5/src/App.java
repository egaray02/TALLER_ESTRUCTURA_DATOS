/*  5. Ruta de Rescate (Espeleología Geológica)
Un equipo de rescatistas entra en una cueva inexplorada. Para no perderse, van dejando "Estaciones de Seguridad" representadas por una baliza que registra los datos del entorno.

La Clase Estacion (Nodo): Debe contener nombrePunto (String), profundidad (int) y nivelOxigeno (double).
El Problema: Para salir de la cueva, el equipo debe seguir las estaciones en el orden inverso al que fueron colocadas (de la más profunda a la entrada).
Reto: Implementa el método retrocederASuperficie(). Este debe mostrar el nombre de cada estación a medida que se desapila. Importante: Si en alguna estación el nivelOxigeno es inferior al 18%, el sistema debe imprimir una alerta de "Uso de Tanque de Emergencia Requerido" al pasar por ese punto.
 */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        PilaRuta ruta = new PilaRuta();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE RUTA DE RESCATE (PILAS) ---");
            System.out.println("1. Ver estaciones registradas");
            System.out.println("2. Registrar nueva estación (Push)");
            System.out.println("3. Eliminar última estación (Pop)");
            System.out.println("4. Retroceder a la superficie");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nEstado actual de la ruta:");
                    ruta.imprimir();
                    break;
                case 2:
                    System.out.print("Nombre del punto: "); String nombre = scanner.nextLine();
                    System.out.print("Profundidad (m): "); int profundidad = scanner.nextInt();
                    System.out.print("Nivel de oxígeno (%): "); double oxigeno = scanner.nextDouble();
                    scanner.nextLine();
                    ruta.push(new Estacion(nombre, profundidad, oxigeno));
                    System.out.println("Estación registrada correctamente.");
                    break;
                case 3:
                    Estacion retirada = ruta.pop();
                    if (retirada != null) {
                        System.out.println("Estación eliminada: " + retirada);
                    } else {
                        System.out.println("No hay estaciones en la ruta.");
                    }
                    break;
                case 4:
                    ruta.retrocederASuperficie();
                    break;
                case 5:
                    System.out.println("Cerrando sistema de rescate...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}