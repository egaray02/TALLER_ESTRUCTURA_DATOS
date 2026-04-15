/*2. Carrusel de Anuncios (Pantalla Digital)
Una tienda tiene una pantalla que muestra anuncios en rotación continua. Cuando termina el último anuncio, vuelve automáticamente al primero.

La Clase Anuncio (Nodo): Debe contener titulo (String), duracionSegundos (int), vecesRepetido (int) y categoria (String - ej: "Oferta", "Marca", "Evento").
El Problema: La pantalla necesita saber cuánto tiempo total lleva encendida y cuál es el anuncio que más veces se ha repetido.
Reto: Implementa el método reproducir(int ciclos) que simule ciclos pasadas completas por todos los anuncios, incrementando vecesRepetido en cada paso e imprimiendo qué anuncio está en pantalla. Al finalizar, muestra el anuncio más repetido y el tiempo total acumulado en pantalla. */
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner          sc       = new Scanner(System.in);
        CarruselAnuncio carrusel = new CarruselAnuncio();
        int              opcion;

        do {
            System.out.println("\n═════════════════════════════");
            System.out.println("  CARRUSEL DE ANUNCIOS   ");
            System.out.println("══════════════════════════════");
            System.out.println("  1. Agregar anuncio          ");
            System.out.println("  2. Ver anuncios             ");
            System.out.println("  3. Reproducir carrusel      ");
            System.out.println("  0. Salir                    ");
            System.out.println("══════════════════════════════");
            System.out.print("  Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("  Título      : ");
                    String tit = sc.nextLine();
                    System.out.print("  Duración (s): ");
                    int dur = sc.nextInt(); sc.nextLine();
                    System.out.print("  Categoría   : ");
                    String cat = sc.nextLine();
                    carrusel.agregar(new Anuncio(tit, dur, cat));
                    System.out.println("  Anuncio agregado.");
                    break;

                case 2:
                    System.out.println("\n  Lista de anuncios:");
                    carrusel.listar();
                    break;

                case 3:
                    System.out.print("  ¿Cuántos ciclos? ");
                    int ciclos = sc.nextInt(); sc.nextLine();
                    carrusel.reproducir(ciclos);
                    break;

                case 0:
                    System.out.println("  Hasta luego.");
                    break;

                default:
                    System.out.println("  Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
