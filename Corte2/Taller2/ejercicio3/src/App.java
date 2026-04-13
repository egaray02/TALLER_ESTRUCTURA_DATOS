/*3. Navegación de Pestañas de Navegador
Imagina un navegador donde puedes moverte entre pestañas abiertas.

La Clase Pestana (Nodo): Debe contener tituloPagina (String), url (String) y horaApertura (String).
El Problema: Las pestañas se abren una tras otra. A veces el usuario quiere cerrar la pestaña actual y el foco debe pasar a la pestaña anterior.
Reto: Implementar el método cerrarPestanaActual(String url) que busque la pestaña por URL, la elimine de la lista y reconecte el nodo anterior con el siguiente correctamente (¡Cuidado con la Cabeza y la Cola!). */
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Navegador navegador = new Navegador();

        System.out.println("══════════════════════════════════════════");
        System.out.println("       NAVEGADOR DE PESTANAS              ");
        System.out.println("══════════════════════════════════════════");

        abrirPestanas(navegador);

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("  Elige una opcion: ");
            sc.nextLine();

            switch (opcion) {
                case 1 -> abrirPestanas(navegador);
                case 2 -> navegador.verPestanaActual();
                case 3 -> navegador.siguientePestana();
                case 4 -> navegador.pestanaAnterior();
                case 5 -> navegador.mostrarTodas();
                case 6 -> {
                    System.out.print("  URL a cerrar: ");
                    String url = sc.nextLine().trim();
                    navegador.cerrarPestanaActual(url);
                }
                case 0 -> System.out.println("\n  Cerrando navegador. Hasta luego!");
                default -> System.out.println("  Opcion no valida.");
            }

        } while (opcion != 0);

        sc.close();
    }
    static void abrirPestanas(Navegador navegador) {
        int n = leerEntero("\n  Cuantas pestanas deseas abrir? ");
        sc.nextLine();

        if (n <= 0) {
            System.out.println("  Numero invalido.");
            return;
        }

        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.println("  --- Pestana " + i + " ---");

            System.out.print("  Titulo de la pagina: ");
            String titulo = sc.nextLine().trim();

            System.out.print("  URL (ej: www.google.com): ");
            String url = sc.nextLine().trim();

            System.out.print("  Hora de apertura (ej: 10:30): ");
            String hora = sc.nextLine().trim();

            navegador.abrirPestana(titulo, url, hora);
            System.out.println();
        }
    }

    
    static void mostrarMenu() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("            MENU NAVEGADOR                ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("  1. Abrir nuevas pestanas                ");
        System.out.println("  2. Ver pestana activa                   ");
        System.out.println("  3. Siguiente pestana                    ");
        System.out.println("  4. Pestana anterior                     ");
        System.out.println("  5. Mostrar todas las pestanas           ");
        System.out.println("  6. Cerrar pestana por URL               ");
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
}