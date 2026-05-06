/*4. Turnos de Consultorio Médico (Prioridad de Emergencia)
Gestiona una fila de pacientes donde algunos pueden tener emergencias.

La Clase Paciente (Nodo): Debe contener nombre (String), edad (int) y nivelUrgencia (1 al 5).
El Problema: Los pacientes normalmente se agregan al final. Pero si un paciente tiene nivelUrgencia == 5, debe ser movido justo después de la Cabeza.
Reto: Implementa un método que recorra la lista desde la Cola hacia la Cabeza para encontrar al paciente de mayor edad y mostrar sus datos. */
import java.util.Scanner;

public class App{

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Consultorio consultorio = new Consultorio();

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       TURNOS CONSULTORIO MEDICO          ║");
        System.out.println("╚══════════════════════════════════════════╝");

        agregarPacientes(consultorio);

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("  Elige una opcion: ");
            sc.nextLine();

            switch (opcion) {
                case 1 -> agregarPacientes(consultorio);
                case 2 -> consultorio.mostrarFila();
                case 3 -> consultorio.atenderSiguiente();
                case 4 -> consultorio.pacienteMayorEdad();
                case 0 -> System.out.println("\n  Cerrando consultorio. Hasta luego!");
                default -> System.out.println("  Opcion no valida.");
            }

        } while (opcion != 0);

        sc.close();
    }

   
    static void agregarPacientes(Consultorio consultorio) {
        int n = leerEntero("\n  Cuantos pacientes deseas registrar? ");
        sc.nextLine();

        if (n <= 0) {
            System.out.println("  Numero invalido.");
            return;
        }

        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.println("  --- Paciente " + i + " ---");

            System.out.print("  Nombre: ");
            String nombre = sc.nextLine().trim();

            int edad = leerEnteroLinea("  Edad: ");

            int urgencia = 0;
            while (urgencia < 1 || urgencia > 5) {
                urgencia = leerEnteroLinea("  Nivel de urgencia (1 al 5): ");
                if (urgencia < 1 || urgencia > 5) {
                    System.out.println("  El nivel debe estar entre 1 y 5.");
                }
            }

            consultorio.agregarPaciente(nombre, edad, urgencia);
            System.out.println();
        }
    }

   
    static void mostrarMenu() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("          MENU CONSULTORIO                ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("  1. Registrar nuevos pacientes           ");
        System.out.println("  2. Ver fila de espera                   ");
        System.out.println("  3. Atender siguiente paciente           ");
        System.out.println("  4. Buscar paciente de mayor edad        ");
        System.out.println("  0. Salir                                ");
        System.out.println("══════════════════════════════════════════");
    }

    
    static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Ingresa un numero entero valido.");
            }
        }
    }

    
    static int leerEnteroLinea(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Ingresa un numero entero valido.");
            }
        }
    }
}