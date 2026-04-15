/*4.Gestión de Mesas en Restaurante (Lista de Espera Circular)
Un restaurante popular tiene un sistema de lista de espera. Cuando una mesa se libera, el siguiente grupo en espera ocupa el turno y pasa al final del ciclo por si quiere pedir la carta nuevamente (para clientes VIP).

La Clase Grupo (Nodo): Debe contener nombreReserva (String), numeroDPersonas (int), esVip (boolean) y minutosEsperando (int).
El Problema: Los grupos normales se atienden y salen de la lista. Los grupos VIP, al ser atendidos, vuelven al final de la lista circular para una segunda ronda de atención. El sistema debe atender un grupo por turno.
Reto: Implementa el método atenderSiguiente() que tome el grupo de la cabeza, muestre su información, y si esVip == true, lo reinserté al final; si no, lo elimine. Muestra el estado de la lista después de cada atención. Llama al método 6 veces para simular la dinámica del restaurante. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lista lista = new Lista();
        int opcion;

        System.out.println("Ingrese los grupos iniciales:");
        System.out.print("Cuantos grupos desea registrar: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println("\nGrupo " + i + ":");
            System.out.print("Nombre reserva: ");
            String nombre = sc.nextLine();
            System.out.print("Numero de personas: ");
            int personas = sc.nextInt();
            System.out.print("Es VIP (1=Si, 0=No): ");
            boolean vip = sc.nextInt() == 1;
            System.out.print("Minutos esperando: ");
            int minutos = sc.nextInt();
            sc.nextLine();
            lista.agregarGrupo(nombre, personas, vip, minutos);
        }

        do {
            System.out.println("\n═════════════════════════════");
            System.out.println("\nGESTION DE MESAS ");
            System.out.println("\n═════════════════════════════");
            System.out.println("1. Agregar grupo");
            System.out.println("2. Ver lista");
            System.out.println("3. Atender siguiente");
            System.out.println("4. Simular 6 atenciones");
            System.out.println("0. Salir");
            System.out.println("\n═════════════════════════════");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre reserva: ");
                    String nombre = sc.nextLine();
                    System.out.print("Numero de personas: ");
                    int personas = sc.nextInt();
                    System.out.print("Es VIP (1:Si, 0:No): ");
                    boolean vip = sc.nextInt() == 1;
                    System.out.print("Minutos esperando: ");
                    int minutos = sc.nextInt();
                    sc.nextLine();
                    lista.agregarGrupo(nombre, personas, vip, minutos);
                    System.out.println("Grupo agregado.");
                    break;
                case 2:
                    lista.mostrarLista();
                    break;
                case 3:
                    lista.atenderSiguiente();
                    break;
                case 4:
                    for (int i = 1; i <= 6; i++) {
                        System.out.println("\n--- Turno " + i + " ---");
                        lista.atenderSiguiente();
                    }
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}