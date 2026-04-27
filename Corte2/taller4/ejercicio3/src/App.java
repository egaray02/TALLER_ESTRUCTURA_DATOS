/*3. Editor de Capas (Diseño Gráfico)
Los editores gráficos organizan el diseño en capas. El usuario navega a la capa superior (siguiente) o inferior (anterior) y puede ocultar o mostrar cada capa. La estructura es circular: después de la capa más alta vuelve a la más baja.

La Clase Capa (Nodo): Debe contener nombre (String), visible (boolean) y tipo (String — "fondo", "objeto", "texto").
El Problema: El editor mantiene un puntero capaActiva a la capa seleccionada. El usuario puede moverse entre capas, alternar la visibilidad de la activa y eliminarla (el foco pasa a la siguiente).
Reto: Implementa los métodos subirCapa(), bajarCapa(), toggleVisibilidad(), eliminarActiva() y mostrarCapas(). mostrarCapas() imprime todas las capas marcando la activa con [✓] e indicando si cada una es visible. Simula: crea 4 capas, activa la del medio, sube una vez, oculta la activa, elimínala y muestra el estado final. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Editor editor = new Editor();
        int opcion;

        do {
            System.out.println("\n==============================================");
            System.out.println("   Editor de Capas — Menu Principal");
            System.out.println("==============================================");
            System.out.println("1. Agregar capa");
            System.out.println("2. Subir capa (siguiente)");
            System.out.println("3. Bajar capa (anterior)");
            System.out.println("4. Seleccionar capa por nombre");
            System.out.println("5. Alternar visibilidad de capa activa");
            System.out.println("6. Eliminar capa activa");
            System.out.println("7. Mostrar todas las capas");
            System.out.println("8. Cargar simulacion del enunciado");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la capa: ");
                    String nombre = sc.nextLine();
                    System.out.print("Tipo (fondo / objeto / texto): ");
                    String tipo = sc.nextLine();
                    System.out.print("Visible? (true/false): ");
                    boolean visible = sc.nextBoolean();
                    sc.nextLine();
                    editor.agregarCapa(nombre, visible, tipo);
                    System.out.println("Capa '" + nombre + "' agregada.");
                    break;

                case 2:
                    editor.subirCapa();
                    break;

                case 3:
                    editor.bajarCapa();
                    break;

                case 4:
                    System.out.print("Nombre de la capa: ");
                    String buscar = sc.nextLine();
                    editor.seleccionarCapa(buscar);
                    break;

                case 5:
                    editor.toggleVisibilidad();
                    break;

                case 6:
                    editor.eliminarActiva();
                    break;

                case 7:
                    editor.mostrarCapas();
                    break;

                case 8:
                    editor = new Editor();

                    System.out.println("--- Agregando 4 capas ---");
                    editor.agregarCapa("Fondo degradado", true,  "fondo");
                    editor.agregarCapa("Figura principal", true, "objeto");
                    editor.agregarCapa("Sombra",           true, "objeto");
                    editor.agregarCapa("Titulo",           true, "texto");

                    System.out.println("\nEstado inicial:");
                    editor.mostrarCapas();

                    System.out.println("\n--- Seleccionando capa del medio (Figura principal) ---");
                    editor.seleccionarCapa("Figura principal");

                    System.out.println("\n--- Subiendo una vez ---");
                    editor.subirCapa();

                    System.out.println("\n--- Ocultando la capa activa ---");
                    editor.toggleVisibilidad();

                    System.out.println("\n--- Eliminando la capa activa ---");
                    editor.eliminarActiva();

                    System.out.println("\n--- Estado final ---");
                    editor.mostrarCapas();
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