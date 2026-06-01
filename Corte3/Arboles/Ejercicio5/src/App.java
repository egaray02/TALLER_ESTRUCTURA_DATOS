/*5. Control de Inventario (Almacén de Repuestos)
Un taller mecánico almacena los repuestos de su inventario en un BST organizado por código de referencia (un número entero único). Cuando un repuesto se agota, su nodo queda como hoja (no tiene hijos) porque no ha sido reemplazado por variantes. El administrador quiere saber cuántos repuestos son "únicos" (sin variantes relacionadas) y cuál es el recorrido completo del inventario en PreOrden para realizar una copia de seguridad del árbol.

La Clase Repuesto (Nodo): Debe contener codigoRef (int, clave del BST), descripcion (String), marca (String) y stock (int).
El Problema: El administrador necesita dos reportes: el primero lista todos los repuestos en PreOrden (para poder reconstruir el árbol en otro sistema con el mismo orden de inserción), y el segundo informa cuántos repuestos son hojas del árbol (sin variantes relacionadas).
Reto: Implementa backupPreOrden() que imprima todos los repuestos en recorrido PreOrden con el formato [codigoRef] descripcion - marca (stock uds). Implementa también reporteRepuestosUnicos() que use contarHojas() e imprima un mensaje indicando cuántos repuestos no tienen variantes relacionadas en el catálogo. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArbolInventario arbol = new ArbolInventario();
        Scanner scanner = new Scanner(System.in);

        arbol.insertar(5040, "Filtro de aceite",       "Bosch",     12);
        arbol.insertar(2310, "Pastilla de freno",      "Brembo",     8);
        arbol.insertar(7820, "Correa de distribucion", "Gates",      5);
        arbol.insertar(1150, "Bujia de encendido",     "NGK",       20);
        arbol.insertar(3760, "Amortiguador delantero", "Monroe",     3);
        arbol.insertar(6490, "Bomba de agua",          "Dayco",      6);
        arbol.insertar(9100, "Radiador",               "Valeo",      2);
        arbol.insertar(4200, "Sensor de oxigeno",      "Denso",      9);

        int opcion;
        do {
            System.out.println("\n--- CONTROL DE INVENTARIO ALMACEN DE REPUESTOS (BST) ---");
            System.out.println("1. Ver inventario ordenado por codigo (InOrden)");
            System.out.println("2. Backup en PreOrden");
            System.out.println("3. Insertar nuevo repuesto");
            System.out.println("4. Buscar repuesto por codigo");
            System.out.println("5. Reporte de repuestos unicos");
            System.out.println("6. Propiedades del arbol (altura y hojas)");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println();
                    arbol.inOrden();
                    break;

                case 2:
                    System.out.println();
                    arbol.backupPreOrden();
                    break;

                case 3:
                    System.out.print("Codigo de referencia: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Descripcion: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Stock: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine();
                    arbol.insertar(codigo, descripcion, marca, stock);
                    System.out.println("Repuesto insertado correctamente.");
                    break;

                case 4:
                    System.out.print("Codigo a buscar: ");
                    int busqueda = scanner.nextInt();
                    scanner.nextLine();
                    Repuesto encontrado = arbol.buscar(busqueda);
                    if (encontrado != null)
                        System.out.println("Repuesto encontrado: " + encontrado);
                    else
                        System.out.println("No se encontró ningun repuesto con codigo " + busqueda + ".");
                    break;

                case 5:
                    System.out.println();
                    arbol.reporteRepuestosUnicos();
                    break;

                case 6:
                    System.out.println("Altura del arbol:  " + arbol.altura());
                    System.out.println("Nodos hoja:        " + arbol.contarHojas());
                    break;

                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 7);

        scanner.close();
    }
}
