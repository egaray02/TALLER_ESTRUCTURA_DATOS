import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist playlist = new Playlist();
        String entrada;

        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║   Lista Circular Doble Enlazada    ║");
        System.out.println("╚════════════════════════════════════╝");

        do {
            System.out.println("\n┌─────────────────────────────────┐");
            System.out.println("│           MENÚ PRINCIPAL         │");
            System.out.println("├─────────────────────────────────┤");
            System.out.println("│  A) Agregar canción              │");
            System.out.println("│  B) Siguiente canción          │");
            System.out.println("│  C) Canción anterior          │");
            System.out.println("│  D) Ver playlist               │");
            System.out.println("│  E) Canción actual             │");
            System.out.println("│  F) Salir                      │");
            System.out.println("└─────────────────────────────────┘");
            System.out.print("  Elige una opción: ");

            entrada = scanner.nextLine().trim().toUpperCase();

            switch (entrada) {

                case "A":
                    System.out.println("\n── Agregar nueva canción ──");
                    System.out.print("  Título  : ");
                    String titulo = scanner.nextLine().trim();
                    System.out.print("  Artista : ");
                    String artista = scanner.nextLine().trim();

                    if (titulo.isEmpty() || artista.isEmpty()) {
                        System.out.println("El título y el artista no pueden estar vacíos.");
                    } else {
                        playlist.agregarCancion(titulo, artista);
                    }
                    break;

                case "B":
                    System.out.println("\n── Avanzar al siguiente ──");
                    playlist.siguienteCancion();
                    break;

                case "C":
                    System.out.println("\n── Retroceder al anterior ──");
                    playlist.cancionAnterior();
                    break;

                case "D":
                    playlist.verPlaylist();
                    break;

                case "E":
                    System.out.println("\n── Canción en reproducción ──");
                    playlist.mostrarActual();
                    break;

                case "F":
                    System.out.println("\nsalir...\n");
                    break;

                default:
                    System.out.println("⚠ Opción inválida. Intenta de nuevo (A-F).");
            }

        } while (!entrada.equals("F"));

        scanner.close();
    }
}