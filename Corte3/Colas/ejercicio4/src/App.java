/*4. Peaje Inteligente (Control de Tráfico)
En una autopista de peaje, los vehículos ingresan a un carril y avanzan en fila hasta la cabina de cobro. El sistema registra automáticamente cada vehículo al entrar al carril. El cajero cobra en el orden estricto de llegada.

La Clase Vehiculo (Nodo): Debe contener placa (String), tipoVehiculo (String), tarifa (double) y esExento (boolean).
El Problema: Al cerrar el turno, el sistema debe calcular el total recaudado, ignorando los vehículos exentos de pago.
Reto: Implementa un método cerrarTurno() que extraiga (dequeue) todos los vehículos de la cola, acumule la tarifa solo de los que tengan esExento = false e imprima el total recaudado al finalizar.
 */
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Peaje colaPeaje = new Peaje();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE PEAJE INTELIGENTE ---");
            System.out.println("1. Ver vehículos en carril");
            System.out.println("2. Registrar vehículo");
            System.out.println("3. Cobrar siguiente vehículo");
            System.out.println("4. Cerrar turno (recaudo total)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nEstado actual del carril:");
                    colaPeaje.imprimir();
                    break;
                case 2:
                    System.out.print("Placa: "); String placa = scanner.nextLine();
                    System.out.print("Tipo de vehículo: "); String tipo = scanner.nextLine();
                    System.out.print("Tarifa: "); double tarifa = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("¿Es exento? (true/false): "); boolean esExento = scanner.nextBoolean();
                    scanner.nextLine();
                    colaPeaje.enqueue(new Vehiculo(placa, tipo, tarifa, esExento));
                    System.out.println("Vehículo registrado correctamente.");
                    break;
                case 3:
                    Vehiculo cobrado = colaPeaje.dequeue();
                    if (cobrado != null) {
                        System.out.println("\nAtendiendo vehículo:");
                        System.out.println("  Placa   : " + cobrado.placa);
                        System.out.println("  Tipo    : " + cobrado.tipoVehiculo);
                        System.out.println("  Tarifa  : $" + cobrado.tarifa);
                        System.out.println("  Estado  : " + (cobrado.esExento ? "Exento de pago" : "Pago realizado"));
                    } else {
                        System.out.println("No hay vehículos en el carril.");
                    }
                    break;
                case 4:
                    colaPeaje.cerrarTurno();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
