import java.util.Scanner; // necesario para leer lo que el usuario escribe

// Esta clase es el MENU PRINCIPAL
// Solo se encarga de mostrar opciones y llamar los metodos del Almacen
public class App {

    // Scanner sirve para leer lo que el usuario escribe en consola
    static Scanner sc = new Scanner(System.in);

    // Creamos el almacen (la lista enlazada) que usaremos en todo el programa
    static Almacen almacen = new Almacen();

    public static void main(String[] args) {

        int opcion; // guardara la opcion que elija el usuario

        // El menu se repite hasta que el usuario elija salir (opcion 5)
        do {
            mostrarMenu();
            opcion = leerEntero("  Seleccione una opcion: ");
            System.out.println();

            // Segun la opcion elegida, llamamos al metodo correspondiente
            switch (opcion) {
                case 1 -> registrar();
                case 2 -> editarStock();
                case 3 -> almacen.mostrarInventario();
                case 4 -> eliminar();
                case 5 -> System.out.println("  Hasta luego!");
                default -> System.out.println("  [ERROR] Opcion invalida.");
            }

            // Despues de cada accion, esperamos que el usuario presione ENTER
            // excepto cuando elige salir
            if (opcion != 5) pausa();

        } while (opcion != 5); // repetimos mientras no elija salir

        sc.close(); // cerramos el scanner al terminar
    }

    // =====================================================================
    // MENU: muestra las opciones disponibles
    // =====================================================================
    static void mostrarMenu() {
        System.out.println();
        System.out.println("  === INVENTARIO DE ALMACEN ===");
        System.out.println("  1. Registrar Producto");
        System.out.println("  2. Buscar y Editar Stock");
        System.out.println("  3. Ver Inventario y Valor Total");
        System.out.println("  4. Eliminar Producto");
        System.out.println("  5. Salir");
        System.out.println("  =============================");
    }

    // =====================================================================
    // OPCION 1: pide los datos al usuario y llama al metodo registrar
    // =====================================================================
    static void registrar() {
        System.out.println("  --- REGISTRAR PRODUCTO ---");
        int    codigo   = leerEntero("  Codigo:   ");
        String nombre   = leerTexto ("  Nombre:   ");
        int    cantidad = leerEntero("  Cantidad: ");
        double precio   = leerDouble("  Precio:   ");
        almacen.registrar(codigo, nombre, cantidad, precio); // llamamos al almacen
    }

    // =====================================================================
    // OPCION 2: pide el codigo y el cambio, llama al metodo buscarYEditar
    // =====================================================================
    static void editarStock() {
        System.out.println("  --- EDITAR STOCK ---");
        int codigo = leerEntero("  Codigo del producto: ");
        System.out.println("  Ingrese cantidad a sumar (+) o restar (-)");
        System.out.println("  Ejemplo: 10 para sumar, -5 para restar");
        int cambio = leerEntero("  Cambio de stock: ");
        almacen.buscarYEditar(codigo, cambio); // llamamos al almacen
    }

    // =====================================================================
    // OPCION 4: pide el codigo y llama al metodo eliminar
    // =====================================================================
    static void eliminar() {
        System.out.println("  --- ELIMINAR PRODUCTO ---");
        int codigo = leerEntero("  Codigo del producto a eliminar: ");
        almacen.eliminar(codigo); // llamamos al almacen
    }

    // =====================================================================
    // METODOS DE LECTURA SEGUROS (evitan que el programa explote si el
    // usuario escribe letras donde debe ir un numero, etc.)
    // =====================================================================

    // Lee un numero entero, y si el usuario escribe algo incorrecto lo repite
    static int leerEntero(String mensaje) {
        while (true) { // repetimos hasta que el usuario escriba un numero valido
            try {
                System.out.print(mensaje);
                int valor = Integer.parseInt(sc.nextLine().trim()); // convertimos texto a entero
                return valor; // si llega aqui, estuvo correcto
            } catch (NumberFormatException e) {
                // si el usuario escribio letras, llegamos aqui y pedimos de nuevo
                System.out.println("  [ERROR] Ingrese un numero entero valido.");
            }
        }
    }

    // Lee un numero decimal, acepta tanto punto como coma (25.99 o 25,99)
    static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String texto = sc.nextLine().trim().replace(",", "."); // cambiamos coma por punto
                double valor = Double.parseDouble(texto);
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Ingrese un numero valido. Ejemplo: 12.50");
            }
        }
    }

    // Lee un texto y no permite que quede vacio
    static String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim(); // trim() elimina espacios al inicio y al final
            if (texto.isEmpty()) {
                System.out.println("  [ERROR] El nombre no puede estar vacio.");
            }
        } while (texto.isEmpty()); // repetimos si esta vacio
        return texto;
    }

    // Pausa el programa hasta que el usuario presione ENTER
    static void pausa() {
        System.out.println();
        System.out.print("  Presione ENTER para continuar...");
        sc.nextLine();
    }
}