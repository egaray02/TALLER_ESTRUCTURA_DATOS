import java.util.ArrayList;

/**
 * Lista circular simplemente enlazada que simula un planificador Round Robin.
 * REUTILIZABLE: La lógica de eliminar nodos en una lista circular y el
 * recorrido con "mientras cabeza no sea null" sirve para cualquier problema
 * donde debas ir eliminando nodos hasta vaciar la lista.
 */
public class Planificador {

    // ── Atributos ────────────────────────────────────────────────────────────
    private Proceso           cabeza;
    private Proceso           cola;
    private int               tamaño;
    private ArrayList<String> ordenFinalizacion; // Guarda el orden en que terminan los procesos

    // ── Constructor ──────────────────────────────────────────────────────────
    public Planificador() {
        cabeza            = null;
        cola              = null;
        tamaño            = 0;
        ordenFinalizacion = new ArrayList<>();
    }

    // ── Inserción al final ───────────────────────────────────────────────────
    /**
     * Igual que CarruselAnuncio.agregar() — patrón estándar de lista circular.
     * REUTILIZABLE: Dos casos: lista vacía / lista con nodos.
     */
    public void agregar(Proceso nuevo) {
        if (cabeza == null) {
            cabeza         = nuevo;
            cola           = nuevo;
            cola.siguiente = cabeza; // El único nodo se apunta a sí mismo
        } else {
            cola.siguiente = nuevo;  // Ex-cola apunta al nuevo
            cola           = nuevo;  // El nuevo es la nueva cola
            cola.siguiente = cabeza; // Cerrar el círculo
        }
        tamaño++;
    }

    // ── Eliminación de un nodo específico ────────────────────────────────────
    /**
     * Elimina un nodo de la lista circular dado su nodo anterior.
     * REUTILIZABLE: Patrón de eliminación en lista circular — siempre
     * hay 4 casos que cubrir:
     *   1. Único nodo      → la lista queda vacía
     *   2. Eliminar cabeza → el siguiente pasa a ser la nueva cabeza
     *   3. Eliminar cola   → el anterior pasa a ser la nueva cola
     *   4. Nodo del medio  → el anterior salta al siguiente del eliminado
     *
     * IMPORTANTE: Siempre necesitas llevar un puntero "anterior" mientras
     * recorres la lista para poder llamar este método.
     */
    private void eliminar(Proceso anterior, Proceso aEliminar) {
        if (tamaño == 1) {
            // Caso 1: queda vacía
            cabeza = null;
            cola   = null;
        } else if (aEliminar == cabeza) {
            // Caso 2: se elimina la cabeza
            cabeza         = cabeza.siguiente;
            cola.siguiente = cabeza; // Mantener circularidad
        } else if (aEliminar == cola) {
            // Caso 3: se elimina la cola
            cola           = anterior;
            cola.siguiente = cabeza; // Mantener circularidad
        } else {
            // Caso 4: nodo intermedio — saltar sobre él
            anterior.siguiente = aEliminar.siguiente;
        }
        tamaño--;
    }

    // ── Algoritmo Round Robin ────────────────────────────────────────────────
    /**
     * Recorre la lista circular descontando "cantidad" segundos por turno.
     * Cuando un proceso agota su tiempo, se elimina de la lista.
     * El ciclo termina cuando la lista queda vacía.
     *
     * REUTILIZABLE: El patrón "while(cabeza != null) + eliminar al vuelo"
     * sirve para cualquier problema donde debas procesar y eliminar nodos
     * en una lista circular hasta vaciarla (ej: Problema de Josefo).
     *
     * CLAVE: Después de eliminar, "actual" debe apuntar al siguiente antes
     * de eliminarlo — si no, pierdes la referencia al resto de la lista.
     */
    public void ejecutar(int cantidad) {
        if (cabeza == null) {
            System.out.println("No hay procesos en la cola.");
            return;
        }

        ordenFinalizacion.clear(); // Reiniciar por si se llama varias veces

        System.out.println("\nIniciando Round Robin  quantum=" + cantidad);
        System.out.println("─".repeat(45));

        int turno    = 1;
        Proceso anterior = cola;    // Empieza apuntando al último (antes de cabeza)
        Proceso actual   = cabeza;  // Empieza en el primer proceso

        // Continuar mientras haya procesos en la lista
        while (cabeza != null) {
            System.out.printf("Turno %2d | %s | restante antes: %ds%n",
                    turno++, actual, actual.getTiempoRestante());

            actual.descontarTiempo(cantidad); // Descontar el quantum

            if (actual.getTiempoRestante() <= 0) {
                // ── Proceso terminado: eliminar de la lista ──────────────────
                System.out.printf("         | TERMINO %s%n", actual.getNombre());
                ordenFinalizacion.add(actual.getNombre());

                Proceso siguiente = actual.siguiente; // Guardar el siguiente ANTES de eliminar
                eliminar(anterior, actual);
                // "anterior" no cambia — el nuevo actual es el siguiente del eliminado
                actual = (cabeza == null) ? null : siguiente;
            } else {
                // ── Proceso aún vivo: avanzar al siguiente ───────────────────
                System.out.printf("         | restante ahora: %ds%n",
                        actual.getTiempoRestante());
                anterior = actual;          // El actual pasa a ser el anterior
                actual   = actual.siguiente; // Avanzar al siguiente nodo
            }

            System.out.println("─".repeat(45));
        }

        // ── Resumen: orden de finalización ───────────────────────────────────
        System.out.println("\nOrden de finalizacion:");
        for (int i = 0; i < ordenFinalizacion.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + ordenFinalizacion.get(i));
        }
    }

    // ── Listar procesos ──────────────────────────────────────────────────────
    /**
     * Recorre una vuelta completa con do-while.
     * REUTILIZABLE: Mismo patrón de siempre para listar lista circular.
     */
    public void listar() {
        if (cabeza == null) {
            System.out.println("  (sin procesos)");
            return;
        }
        Proceso actual = cabeza;
        int i = 1;
        do {
            System.out.printf("  %d. %s  tiempo=%ds%n",
                    i++, actual, actual.getTiempoRestante());
            actual = actual.siguiente;
        } while (actual != cabeza); // Parar al volver a cabeza
    }

    public int getTamaño() { return tamaño; }
}