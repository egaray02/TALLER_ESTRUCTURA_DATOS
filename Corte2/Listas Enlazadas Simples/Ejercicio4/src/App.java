/*4. Monitoreo Industrial (Sensores en Tiempo Real)
Una planta química registra lecturas de sus tanques cada hora en una lista enlazada para auditoría.

La Clase Lectura (Nodo): Debe contener idSensor (int), temperatura (double), presion (double) y hora (String).
El Problema: Las lecturas se van agregando al inicio para que la más reciente sea siempre la primera que vea el supervisor.
Reto: Implementa un método que busque y muestre la lectura con la temperatura más alta registrada en el historial. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        HistorialSensores historial = new HistorialSensores();

        do {
            System.out.println("\n==============================================");
            System.out.println("   MONITOREO INDUSTRIAL - Menú Principal");
            System.out.println("==============================================");
            System.out.println("1. Registrar lectura de sensor");
            System.out.println("2. Ver historial completo");
            System.out.println("3. Reportar temperatura máxima");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println("==============================================");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (opcion) {

                case 1:
                    System.out.print("ID Sensor    : ");
                    int idSensor = sc.nextInt();
                    System.out.print("Temperatura (°C): ");
                    double temperatura = sc.nextDouble();
                    System.out.print("Presión (bar): ");
                    double presion = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Hora (ej. 14:00): ");
                    String hora = sc.nextLine();

                    Lectura nueva = new Lectura(idSensor, temperatura, presion, hora);
                    historial.agregarLectura(nueva);
                    break;

                case 2:
                    historial.imprimirHistorial();
                    break;

                case 3:
                    historial.reportarTemperaturaMaxima();
                    break;

                case 0:
                    System.out.println("¡Saliendo del módulo de Monitoreo Industrial!");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
