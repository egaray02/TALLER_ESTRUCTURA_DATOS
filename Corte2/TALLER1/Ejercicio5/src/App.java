/*5. Spotify Lite (Lista de Reproducción)
Crea un reproductor de música simplificado que gestione una lista de canciones.

La Clase Cancion (Nodo): Debe contener titulo (String), artista (String), duracionSegundos (int) y genero (String).
El Problema: El usuario puede agregar canciones "A continuación" (insertar después de la actual) o "Al final de la cola".
Reto: Implementa un método que sume la duración de todas las canciones y muestre el tiempo total de la lista en formato MM:SS.
 */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        ListaReproduccion playlist = new ListaReproduccion();

        do {
            System.out.println("\n==============================================");
            System.out.println("   SPOTIFY LITE - Menú Principal");
            System.out.println("==============================================");
            System.out.println("1. Agregar canción al final");
            System.out.println("2. Agregar canción a continuación");
            System.out.println("3. Siguiente canción");
            System.out.println("4. Ver lista de reproducción");
            System.out.println("5. Ver duración total");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println("==============================================");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (opcion) {

                case 1:
                case 2:
                    System.out.print("Título    : ");
                    String titulo = sc.nextLine();
                    System.out.print("Artista   : ");
                    String artista = sc.nextLine();
                    System.out.print("Duracion (minutos): ");
                    int minutos = sc.nextInt();
                    System.out.print("Duracion (segundos): ");
                    int segundos = sc.nextInt();
                    int duracion = (minutos * 60) + segundos;
                    sc.nextLine();
                    System.out.print("Género    : ");
                    String genero = sc.nextLine();

                    Cancion nueva = new Cancion(titulo, artista, duracion, genero);
                    if (opcion == 1) playlist.agregarAlFinal(nueva);
                    else            playlist.agregarAContinuacion(nueva);
                    break;

                case 3:
                    playlist.siguiente();
                    break;

                case 4:
                    playlist.imprimirLista();
                    break;

                case 5:
                    playlist.mostrarDuracionTotal();
                    break;

                case 0:
                    System.out.println("Saliendo de Spotify");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
