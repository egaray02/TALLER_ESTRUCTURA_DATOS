/*. Reproductor de Música Premium
Mejora el ejercicio de Spotify de la semana pasada usando listas dobles.

La Clase Cancion (Nodo): Debe contener titulo (String), artista (String) y duracion (int).
El Problema: El reproductor ahora permite la función "Canción Anterior" de manera eficiente sin tener que recorrer toda la lista desde el principio.
Reto: Implementa un método saltarAtras() que retroceda una posición y saltarAdelante() que avance una. Si se intenta saltar atrás desde la primera canción, debe mostrar un mensaje de error. */
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ReproductorMusica reproductor = new ReproductorMusica();

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║       REPRODUCTOR DE MUSICA PREMIUM      ║");
        System.out.println("╚══════════════════════════════════════════╝");

        agregarCanciones(reproductor);

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("  Elige una opcion: ");
            sc.nextLine();

            switch (opcion) {
                case 1 -> agregarCanciones(reproductor);
                case 2 -> reproductor.verCancionActual();
                case 3 -> reproductor.saltarAdelante();
                case 4 -> reproductor.saltarAtras();
                case 5 -> reproductor.mostrarLista();
                case 6 -> {
                    System.out.print("  Titulo a eliminar: ");
                    String titulo = sc.nextLine().trim();
                    reproductor.eliminarCancion(titulo);
                }
                case 7 -> reproductor.toggleRepeticion();
                case 8 -> reproductor.duracionTotal();
                case 0 -> System.out.println("\n  Cerrando reproductor. Hasta luego!");
                default -> System.out.println("  Opcion no valida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    static void agregarCanciones(ReproductorMusica reproductor) {
        int n = leerEntero("\n  Cuantas canciones deseas agregar? ");
        sc.nextLine();

        if (n <= 0) {
            System.out.println("  Numero invalido.");
            return;
        }

        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.println("  --- Cancion " + i + " ---");

            System.out.print("  Titulo: ");
            String titulo = sc.nextLine().trim();

            System.out.print("  Artista: ");
            String artista = sc.nextLine().trim();

            int duracion = 0;
            while (duracion <= 0) {
                duracion = leerEntero("  Duracion en segundos (ej: 213): ");
                sc.nextLine();
                if (duracion <= 0) {
                    System.out.println("  La duracion debe ser mayor a 0.");
                }
            }

            reproductor.agregarCancion(titulo, artista, duracion);
            System.out.println();
        }
    }

    static void mostrarMenu() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("          MENU REPRODUCTOR                ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("  1. Agregar canciones                    ");
        System.out.println("  2. Ver cancion actual                   ");
        System.out.println("  3. Saltar adelante                      ");
        System.out.println("  4. Saltar atras                         ");
        System.out.println("  5. Ver lista completa                   ");
        System.out.println("  6. Eliminar cancion                     ");
        System.out.println("  7. Activar/desactivar repeticion        ");
        System.out.println("  8. Ver duracion total                   ");
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