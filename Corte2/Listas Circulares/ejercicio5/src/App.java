/*5. Torneo de Fútbol (Fixture Round Robin)
En un torneo de fútbol por todos contra todos, cada equipo debe enfrentarse exactamente una vez contra cada uno de los demás equipos. El algoritmo Round Robin fija los partidos rotando los equipos en un ciclo.

La Clase Equipo (Nodo): Debe contener nombre (String), ciudad (String), puntos (int) y golesFavor (int).
El Problema: El fixture se genera rotando la lista: en cada jornada, se enfrentan los equipos opuestos del ciclo (el primero con el último, el segundo con el penúltimo, etc.). Un equipo queda fijo (la cabeza) y el resto rota una posición hacia adelante en cada jornada.
Reto: Implementa el método generarFixture() que imprima todos los partidos de una jornada completa para 6 equipos (3 partidos por jornada). Luego, rota los equipos una posición y repite hasta que todos se hayan enfrentado. Al final, imprime la tabla de posiciones ordenando los equipos de mayor a menor puntaje (puedes asignar puntos aleatorios o fijos para la simulación). */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TorneoRound torneo = new TorneoRound();
        int opcion;

        System.out.print("Cuantos equipos desea registrar (par): ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\nEquipo " + i + ":");
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("Puntos: ");
            int puntos = sc.nextInt();
            System.out.print("Goles a favor: ");
            int goles = sc.nextInt();
            sc.nextLine();
            torneo.agregarEquipo(nombre, ciudad, puntos, goles);
        }

        do {
            System.out.println("\n═════════════════════════════");
            System.out.println("\nTORNEO ROUND ROBIN");
            System.out.println("\n═════════════════════════════");
            System.out.println("1. Agregar equipo");
            System.out.println("2. Generar fixture");
            System.out.println("3. Tabla de posiciones");
            System.out.println("0. Salir");
            System.out.println("\n═════════════════════════════");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ciudad: ");
                    String ciudad = sc.nextLine();
                    System.out.print("Puntos: ");
                    int puntos = sc.nextInt();
                    System.out.print("Goles a favor: ");
                    int goles = sc.nextInt();
                    sc.nextLine();
                    torneo.agregarEquipo(nombre, ciudad, puntos, goles);
                    System.out.println("Equipo agregado.");
                    break;
                case 2:
                    torneo.generarFixture();
                    break;
                case 3:
                    torneo.tablaPosiciones();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
