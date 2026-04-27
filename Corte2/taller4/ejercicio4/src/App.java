/*4. Galería de Fotos
Una app de galería muestra las fotos de un álbum de una en una. El usuario puede pasar a la foto siguiente, volver a la anterior, y marcar cualquier foto como favorita. Al eliminar una foto, la galería continúa desde la siguiente sin interrupciones.

La Clase Foto (Nodo): Debe contener titulo (String), fecha (String) y esFavorita (boolean).
El Problema: La galería mantiene un puntero actual a la foto que se está viendo. Navegar con "siguiente" o "anterior" mueve ese puntero. Marcar como favorita simplemente alterna el campo esFavorita de la foto actual. Eliminar la foto actual desconecta el nodo en O(1) y mueve actual a la siguiente.
Reto: Implementa los métodos siguiente(), anterior(), toggleFavorita(), eliminarActual() y mostrarGaleria(). mostrarGaleria() recorre todas las fotos marcando con [★] las favoritas y con [▶] la foto actual. Simula: carga 5 fotos, avanza dos veces, marca la actual como favorita, retrocede una, elimina esa foto y muestra el estado final de la galería. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Galeria galeria = new Galeria();
        int opcion;

        do {
            System.out.println("\n==============================================");
            System.out.println("   Galeria de Fotos — Menu Principal");
            System.out.println("==============================================");
            System.out.println("1. Agregar foto");
            System.out.println("2. Foto siguiente");
            System.out.println("3. Foto anterior");
            System.out.println("4. Marcar / desmarcar favorita");
            System.out.println("5. Eliminar foto actual");
            System.out.println("6. Mostrar galeria completa");
            System.out.println("7. Cargar simulacion del enunciado");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    System.out.print("Titulo de la foto: ");
                    String titulo = sc.nextLine();
                    System.out.print("Fecha (dd/mm/aaaa): ");
                    String fecha = sc.nextLine();
                    galeria.agregarFoto(titulo, fecha, false);
                    System.out.println("Foto '" + titulo + "' agregada.");
                    break;

                case 2:
                    galeria.siguiente();
                    break;

                case 3:
                    galeria.anterior();
                    break;

                case 4:
                    galeria.toggleFavorita();
                    break;

                case 5:
                    galeria.eliminarActual();
                    break;

                case 6:
                    galeria.mostrarGaleria();
                    break;

                case 7:
                    galeria = new Galeria();

                    System.out.println("--- Cargando 5 fotos ---");
                    galeria.agregarFoto("Amanecer en la montana", "03/01/2025", false);
                    galeria.agregarFoto("Puerto viejo",           "14/02/2025", false);
                    galeria.agregarFoto("Mercado de flores",      "22/03/2025", false);
                    galeria.agregarFoto("Lluvia en la ciudad",    "05/04/2025", false);
                    galeria.agregarFoto("Atardecer en el parque", "18/05/2025", false);

                    System.out.println("\nEstado inicial (actual apunta a la primera):");
                    galeria.mostrarGaleria();

                    System.out.println("\n--- Avanzando dos veces ---");
                    galeria.siguiente();
                    galeria.siguiente();

                    System.out.println("\n--- Marcando como favorita ---");
                    galeria.toggleFavorita();

                    System.out.println("\n--- Retrocediendo una ---");
                    galeria.anterior();

                    System.out.println("\n--- Eliminando foto actual ---");
                    galeria.eliminarActual();

                    System.out.println("\n--- Estado final ---");
                    galeria.mostrarGaleria();
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