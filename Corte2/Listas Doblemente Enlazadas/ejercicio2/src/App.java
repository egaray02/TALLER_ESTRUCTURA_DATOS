/*2. Carrusel de Imágenes (Galería Interactiva)
Simula el comportamiento de una galería de fotos en una aplicación móvil.

La Clase Fotografia (Nodo): Debe contener nombreArchivo (String), tamanoMB (double) y resolucion (String).
El Problema: El usuario puede avanzar a la "Siguiente Foto" o retroceder a la "Foto Anterior". Si llega al final, no puede avanzar más (a menos que sea circular, pero por ahora manténlo lineal).
Reto: Crea un método reproducirGaleria() que recorra toda la lista hacia adelante y luego toda la lista hacia atrás para mostrar todas las fotos. */
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Galeria galeria = new Galeria();

        System.out.println("══════════════════════════════════════════");
        System.out.println("       CARRUSEL DE IMÁGENES               ");
        System.out.println("══════════════════════════════════════════");

        
        cargarFotos(galeria);

    
        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("  Elige una opción: ");

            switch (opcion) {
                case 1 -> cargarFotos(galeria);
                case 2 -> galeria.verFotoActual();
                case 3 -> galeria.siguienteFoto();
                case 4 -> galeria.fotoAnterior();
                case 5 -> galeria.reproducirGaleria();
                case 0 -> System.out.println("\n  Cerrando galería. ¡Hasta luego!");
                default -> System.out.println("  Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }

    // ── Pedir fotos al usuario ──
    static void cargarFotos(Galeria galeria) {
        System.out.print("\n  ¿Cuántas fotos deseas agregar? ");
        int n = leerEntero("");

        if (n <= 0) {
            System.out.println("  Número inválido.");
            return;
        }

        System.out.println();
        for (int i = 1; i <= n; i++) {
            System.out.println("  ─── Foto " + i + " ───");

            System.out.print("  Nombre del archivo (ej: foto.jpg): ");
            String nombre = sc.nextLine().trim();

            double mb = leerDecimal("  Tamaño en MB (ej: 3.5): ");

            System.out.print("  Resolución (ej: 4K 3840x2160): ");
            String res = sc.nextLine().trim();

            galeria.agregarFoto(nombre, mb, res);
            System.out.println();
        }
    }


    static void mostrarMenu() {
        System.out.println("\n═════════════════════════════════════════");
        System.out.println("               MENÚ GALERÍA               ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("  1. Agregar más fotos                    ");
        System.out.println("  2. Ver foto actual                      ");
        System.out.println("  3. Siguiente foto  ►                    ");
        System.out.println("  4. Foto anterior   ◄                    ");
        System.out.println("  5. Reproducir galería (ida y vuelta)    ");
        System.out.println("  0. Salir                                ");
        
    }

   
    static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(sc.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Ingresa un número entero válido.");
            }
        }
    }

    static double leerDecimal(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Ingresa un número decimal válido (usa punto o coma).");
            }
        }
    }
}