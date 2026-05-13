/*4. Farmacia Automatizada (Dispensador Tubo LIFO)
En una farmacia de alta tecnología, los medicamentos de alta rotación se almacenan en tubos dispensadores verticales. El personal introduce las cajas por la parte superior y las retira de la misma forma (el último lote en llegar es el primero en ser despachado).

La Clase Medicamento (Nodo): Debe contener nombre (String), lote (String) y diasParaVencer (int).
El Problema: Por norma de seguridad, no se puede despachar un medicamento si le quedan menos de 10 días para vencer.
Reto: Implementa un método validarDespacho() que revise el medicamento en el tope. Si está a punto de vencer, debe ser retirado automáticamente y el sistema debe revisar el siguiente. El proceso se repite hasta que el tope sea un medicamento seguro o la pila quede vacía. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        PilaDispensador dispensador = new PilaDispensador();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE FARMACIA AUTOMATIZADA (PILAS) ---");
            System.out.println("1. Ver medicamentos en el dispensador");
            System.out.println("2. Ingresar nuevo medicamento (Push)");
            System.out.println("3. Retirar medicamento del tope (Pop)");
            System.out.println("4. Validar despacho (revisar vencimientos)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\nEstado actual del dispensador:");
                    dispensador.imprimir();
                    break;
                case 2:
                    System.out.print("Nombre del medicamento: "); String nombre = scanner.nextLine();
                    System.out.print("Lote: "); String lote = scanner.nextLine();
                    System.out.print("Días para vencer: "); int dias = scanner.nextInt();
                    scanner.nextLine();
                    dispensador.push(new Medicamento(nombre, lote, dias));
                    System.out.println("Medicamento ingresado al dispensador.");
                    break;
                case 3:
                    Medicamento retirado = dispensador.pop();
                    if (retirado != null) {
                        System.out.println("Medicamento retirado: " + retirado);
                    } else {
                        System.out.println("El dispensador está vacío.");
                    }
                    break;
                case 4:
                    dispensador.validarDespacho();
                    break;
                case 5:
                    System.out.println("Cerrando sistema de farmacia...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}