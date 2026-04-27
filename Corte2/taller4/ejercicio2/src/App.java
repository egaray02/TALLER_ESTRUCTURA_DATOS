/*2. Historial de Comandos de Terminal
Los terminales guardan un historial de comandos. Al presionar flecha arriba el usuario retrocede al comando anterior; flecha abajo avanza al más reciente. El historial es circular: después del más antiguo vuelve al más nuevo.

La Clase Comando (Nodo): Debe contener texto (String), exitoso (boolean — si ejecutó sin errores) y directorio (String — el path desde donde se ejecutó).
El Problema: El historial mantiene un puntero cursor al comando que se está consultando. Navegar con "arriba" mueve el cursor al anterior (anterior); "abajo" lo mueve al siguiente (siguiente). El usuario puede eliminar el comando actual (para borrar contraseñas escritas por error), y el cursor pasa automáticamente al siguiente.
Reto: Implementa los métodos arriba(), abajo(), mostrarCursor() y eliminarActual(). Simula: agrega 5 comandos, navega 3 veces hacia arriba, elimina el comando actual, navega una vez hacia abajo y muestra el historial completo con el cursor marcado. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Historial historial = new Historial();
        int opcion;

        do {
            System.out.println("\n==============================================");
            System.out.println("   Historial de Terminal — Menu Principal");
            System.out.println("==============================================");
            System.out.println("1. Agregar comando");
            System.out.println("2. Navegar arriba (comando anterior)");
            System.out.println("3. Navegar abajo (comando siguiente)");
            System.out.println("4. Ver comando actual (cursor)");
            System.out.println("5. Eliminar comando actual");
            System.out.println("6. Mostrar historial completo");
            System.out.println("7. Cargar simulacion del enunciado");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    System.out.print("Texto del comando: ");
                    String texto = sc.nextLine();
                    System.out.print("Directorio (path): ");
                    String directorio = sc.nextLine();
                    System.out.print("Exitoso? (true/false): ");
                    boolean exitoso = sc.nextBoolean();
                    sc.nextLine();
                    historial.agregarComando(texto, exitoso, directorio);
                    System.out.println("Comando agregado.");
                    break;

                case 2:
                    System.out.println("Navegando hacia arriba...");
                    historial.arriba();
                    break;

                case 3:
                    System.out.println("Navegando hacia abajo...");
                    historial.abajo();
                    break;

                case 4:
                    historial.mostrarCursor();
                    break;

                case 5:
                    historial.eliminarActual();
                    break;

                case 6:
                    historial.mostrarHistorial();
                    break;

                case 7:
                    historial = new Historial();

                    System.out.println("--- Agregando 5 comandos ---");
                    historial.agregarComando("ls -la",               true,  "/home/user");
                    historial.agregarComando("cd proyectos",         true,  "/home/user");
                    historial.agregarComando("git status",           true,  "/home/user/proyectos");
                    historial.agregarComando("export TOKEN=abc123",  true,  "/home/user/proyectos");
                    historial.agregarComando("./deploy.sh",          false, "/home/user/proyectos");

                    System.out.println("\nCursor inicial (ultimo comando agregado):");
                    historial.mostrarCursor();

                    System.out.println("\n--- Navegando 3 veces hacia arriba ---");
                    historial.arriba();
                    historial.arriba();
                    historial.arriba();

                    System.out.println("\n--- Eliminando comando actual ---");
                    historial.eliminarActual();

                    System.out.println("\nCursor despues de eliminar:");
                    historial.mostrarCursor();

                    System.out.println("\n--- Navegando una vez hacia abajo ---");
                    historial.abajo();

                    System.out.println("\n--- Historial completo ---");
                    historial.mostrarHistorial();
                    break;

                case 0:
                    System.out.println("Hasta luego.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}