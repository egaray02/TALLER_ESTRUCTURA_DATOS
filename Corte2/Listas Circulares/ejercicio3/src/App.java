import java.util.Scanner;

/**
 * Menú interactivo del Planificador Round Robin.
 * REUTILIZABLE: Mismo patrón de menú do-while + Scanner del ejercicio anterior.
 * Única diferencia: "pidAuto" para asignar IDs automáticos sin pedirlos al usuario.
 */
public class App {

    public static void main(String[] args) {

        Scanner      sc       = new Scanner(System.in);
        Planificador cpu      = new Planificador();
        int          opcion;
        int          pidAuto  = 1; // ID autoincremental — evita pedirle el PID al usuario

        do {
            System.out.println("\n═════════════════════════════");
            System.out.println("\nPLANIFICADOR ROUND ROBIN");
            System.out.println("\n═════════════════════════════");
            System.out.println("1. Agregar proceso");
            System.out.println("2. Ver procesos");
            System.out.println("3. Ejecutar planificador");
            System.out.println("0. Salir");
            System.out.println("\n═════════════════════════════");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el salto de línea que deja nextInt()

            switch (opcion) {

                case 1: // ── Agregar proceso ──────────────────────────────────
                    System.out.print("Nombre         : ");
                    String nombre = sc.nextLine();
                    System.out.print("Tiempo total   : ");
                    int tiempo = sc.nextInt();
                    System.out.print("Prioridad (1-3): ");
                    int prioridad = sc.nextInt(); sc.nextLine();
                    // pidAuto++ asigna el ID actual y luego lo incrementa para el siguiente
                    cpu.agregar(new Proceso(nombre, pidAuto++, tiempo, prioridad));
                    System.out.println("Proceso agregado.");
                    break;

                case 2: // ── Ver procesos ──────────────────────────────────────
                    System.out.println("\nProcesos en cola:");
                    cpu.listar();
                    break;

                case 3: // ── Ejecutar Round Robin ──────────────────────────────
                    System.out.print("Quantum (segundos): ");
                    int cantidad = sc.nextInt(); sc.nextLine();
                    cpu.ejecutar(cantidad);
                    break;

                case 0: // ── Salir ─────────────────────────────────────────────
                    System.out.println("Saliendo.");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}