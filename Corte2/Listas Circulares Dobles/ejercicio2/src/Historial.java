/**
 * Lista circular DOBLEMENTE enlazada para historial de comandos tipo terminal.
 * REUTILIZABLE: Es la estructura más completa vista hasta ahora.
 * Novedad principal: cada nodo tiene "siguiente" Y "anterior" — permite
 * navegar en ambas direcciones (arriba/abajo) sin recorrer toda la lista.
 *
 * Lista simple   → solo "siguiente"  → navegación hacia adelante
 * Lista doble    → "siguiente" y "anterior" → navegación bidireccional
 */
public class Historial {

    // ── Atributos ────────────────────────────────────────────────────────────
    Comando cabeza;  // Primer nodo
    Comando cola;    // Último nodo
    Comando cursor;  // Nodo actualmente seleccionado (como el cursor de un editor)
    int     tamanio;

    // ── Constructor ──────────────────────────────────────────────────────────
    public Historial() {
        this.cabeza  = null;
        this.cola    = null;
        this.cursor  = null;
        this.tamanio = 0;
    }

    public boolean estaVacio() {
        return cabeza == null;
    }

    // ── Inserción al final (lista doble circular) ────────────────────────────
    /**
     * REUTILIZABLE: Patrón de inserción en lista DOBLE circular.
     * Diferencia clave respecto a la lista simple:
     *   - Hay que enlazar CUATRO punteros en lugar de dos:
     *       cola.siguiente  → nuevo
     *       nuevo.anterior  → cola
     *       nuevo.siguiente → cabeza
     *       cabeza.anterior → nuevo   ← este es el enlace extra de la lista doble
     *
     * Caso vacío — el único nodo se apunta a sí mismo en AMBAS direcciones:
     *       nuevo.siguiente = nuevo
     *       nuevo.anterior  = nuevo
     */
    public void agregarComando(String texto, boolean exitoso, String directorio) {
        Comando nuevo = new Comando(texto, exitoso, directorio);
        if (cabeza == null) {
            // Único nodo: circularidad en ambas direcciones
            nuevo.siguiente = nuevo;
            nuevo.anterior  = nuevo;
            cabeza          = nuevo;
            cola            = nuevo;
        } else {
            // Enganchar al final manteniendo doble circularidad
            cola.siguiente  = nuevo;   // Ex-cola apunta al nuevo por delante
            nuevo.anterior  = cola;    // Nuevo apunta a ex-cola por detrás
            nuevo.siguiente = cabeza;  // Nuevo apunta a cabeza por delante
            cabeza.anterior = nuevo;   // Cabeza apunta al nuevo por detrás
            cola            = nuevo;   // El nuevo es la nueva cola
        }
        cursor  = cola;   // El cursor siempre queda en el último comando agregado
        tamanio++;
    }

    // ── Navegación bidireccional ─────────────────────────────────────────────
    /**
     * REUTILIZABLE: La ventaja de la lista doble — moverse en cualquier
     * dirección en O(1) sin necesidad de recorrer la lista.
     * Como es circular, "arriba" desde cabeza lleva a cola y viceversa.
     */
    public void arriba() {
        if (estaVacio()) { System.out.println("El historial esta vacio."); return; }
        cursor = cursor.anterior; // Ir al comando anterior
        mostrarCursor();
    }

    public void abajo() {
        if (estaVacio()) { System.out.println("El historial esta vacio."); return; }
        cursor = cursor.siguiente; // Ir al comando siguiente
        mostrarCursor();
    }

    public void mostrarCursor() {
        if (estaVacio()) { System.out.println("El historial esta vacio."); return; }
        System.out.println("  [cursor] " + cursor.directorio + " $ " + cursor.texto
                + "  (" + (cursor.exitoso ? "OK" : "ERROR") + ")");
    }

    // ── Eliminación del nodo en cursor (lista doble) ─────────────────────────
    /**
     * REUTILIZABLE: Eliminación en lista DOBLE circular.
     * Más simple que en lista simple porque no necesitas el puntero "anterior"
     * por separado — cada nodo ya lo tiene integrado.
     *
     * Pasos:
     *   1. Guardar referencia al nodo a eliminar
     *   2. Mover el cursor al siguiente (antes de desconectar)
     *   3. Desconectar el nodo en AMBAS direcciones:
     *        eliminado.anterior.siguiente = eliminado.siguiente
     *        eliminado.siguiente.anterior = eliminado.anterior
     *   4. Actualizar cabeza/cola si el eliminado era alguno de ellos
     *
     * Casos:
     *   A. Único nodo  → todo queda null
     *   B. Varios nodos → desconectar en ambas direcciones + ajustar cabeza/cola
     */
    public void eliminarActual() {
        if (estaVacio()) { System.out.println("El historial esta vacio."); return; }

        System.out.println("Eliminando: " + cursor.texto);
        Comando eliminado = cursor;

        // Caso A: único nodo
        if (tamanio == 1) {
            cabeza  = null;
            cola    = null;
            cursor  = null;
            tamanio = 0;
            System.out.println("El historial quedo vacio.");
            return;
        }

        // Caso B: varios nodos
        cursor = eliminado.siguiente; // Mover cursor ANTES de desconectar

        // Desconectar en ambas direcciones — la gran ventaja de la lista doble
        eliminado.anterior.siguiente = eliminado.siguiente;
        eliminado.siguiente.anterior = eliminado.anterior;

        // Ajustar cabeza o cola si el eliminado era uno de ellos
        if (eliminado == cabeza) cabeza = eliminado.siguiente;
        if (eliminado == cola)   cola   = eliminado.anterior;

        tamanio--;
    }

    // ── Mostrar historial completo ───────────────────────────────────────────
    /**
     * Recorre una vuelta completa con do-while marcando el nodo del cursor.
     * REUTILIZABLE: Mismo patrón de siempre — la lista doble se recorre
     * igual que la simple usando "siguiente"; "anterior" solo se usa
     * cuando necesitas ir hacia atrás.
     */
    public void mostrarHistorial() {
        if (estaVacio()) { System.out.println("El historial esta vacio."); return; }

        System.out.println("=== Historial de Comandos ===");
        Comando actual = cabeza;
        int     numero = 1;
        do {
            String marca = (actual == cursor) ? " <-- cursor" : "";
            System.out.println("  " + numero + ". [" + actual.directorio + "] $ "
                    + actual.texto
                    + "  (" + (actual.exitoso ? "OK" : "ERROR") + ")"
                    + marca);
            actual = actual.siguiente;
            numero++;
        } while (actual != cabeza);
    }
}