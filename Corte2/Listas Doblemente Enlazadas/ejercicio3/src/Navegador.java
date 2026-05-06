/**
 * Lista DOBLEMENTE enlazada NO circular — Navegador de pestañas.
 * DIFERENCIA CLAVE respecto a Historial/Editor/Galeria anterior:
 *
 *   Lista doble CIRCULAR     → cola.siguiente = cabeza / cabeza.anterior = cola
 *   Lista doble NO circular  → cola.siguiente = null   / cabeza.anterior = null
 *
 * Consecuencia: la navegación tiene límites reales (no da la vuelta),
 * por eso aparecen los mensajes "Ya estás en la PRIMERA/ÚLTIMA pestaña".
 */
public class Navegador {

    // ── Atributos ────────────────────────────────────────────────────────────
    private pestaña cabeza;  // Primer nodo  — su anterior = null
    private pestaña cola;    // Último nodo  — su siguiente = null
    private pestaña actual;  // Pestaña activa (foco del navegador)
    private int     tamano;

    public Navegador() {
        cabeza = null;
        cola   = null;
        actual = null;
        tamano = 0;
    }

    // ── Inserción al final (lista doble NO circular) ─────────────────────────
    /**
     * REUTILIZABLE: Patrón de inserción en lista doble NO circular.
     * Solo se enlazan 2 punteros (en lugar de 4 en la circular):
     *   nueva.anterior = cola    ← nuevo apunta hacia atrás
     *   cola.siguiente = nueva   ← ex-cola apunta hacia adelante
     * NO se toca cabeza.anterior ni nueva.siguiente — quedan en null.
     *
     * Caso vacío: cabeza = cola = actual = nueva  (sin apuntar a nadie más)
     */
    public void abrirPestana(String titulo, String url, String hora) {
        pestaña nueva = new pestaña(titulo, url, hora);
        if (cabeza == null) {
            cabeza = nueva;
            cola   = nueva;
            actual = nueva;
        } else {
            nueva.anterior = cola;   // Nuevo apunta hacia atrás
            cola.siguiente = nueva;  // Ex-cola apunta hacia adelante
            cola           = nueva;
            actual         = nueva;  // El foco va a la nueva pestaña
        }
        tamano++;
        System.out.println("  Pestana abierta: " + nueva.tituloPagina);
    }

    // ── Navegación con límites (no circular) ─────────────────────────────────
    /**
     * REUTILIZABLE: En lista NO circular la condición de límite es:
     *   actual.siguiente == null  → estás en la última
     *   actual.anterior  == null  → estás en la primera
     * En lista circular nunca llegarías a null — siempre daría la vuelta.
     */
    public void siguientePestana() {
        if (actual == null) { System.out.println("  No hay pestanas abiertas."); return; }
        if (actual.siguiente == null) {
            System.out.println("  Ya estas en la ULTIMA pestana.");
        } else {
            actual = actual.siguiente;
            System.out.println("  Avanzaste a: " + actual.tituloPagina);
        }
    }

    public void pestanaAnterior() {
        if (actual == null) { System.out.println("  No hay pestanas abiertas."); return; }
        if (actual.anterior == null) {
            System.out.println("  Ya estas en la PRIMERA pestana.");
        } else {
            actual = actual.anterior;
            System.out.println("  Retrocediste a: " + actual.tituloPagina);
        }
    }

    // ── Mostrar todas (recorrido con while, no do-while) ─────────────────────
    /**
     * REUTILIZABLE: En lista NO circular se recorre con while(cursor != null)
     * en lugar del do-while(cursor != cabeza) de la lista circular.
     *   Lista circular    → do-while (cursor != cabeza)
     *   Lista NO circular → while    (cursor != null)
     */
    public void mostrarTodas() {
        if (cabeza == null) { System.out.println("  No hay pestanas abiertas."); return; }
        System.out.println("  === Pestanas abiertas (" + tamano + ") ===");
        pestaña cursor = cabeza;
        int pos = 1;
        while (cursor != null) {
            String marca = (cursor == actual) ? " <-- ACTIVA" : "";
            System.out.println("  " + pos + ". " + cursor + marca);
            cursor = cursor.siguiente;
            pos++;
        }
    }

    // ── Cerrar pestaña por URL (búsqueda + eliminación) ──────────────────────
    /**
     * REUTILIZABLE: Patrón completo de búsqueda + eliminación en lista doble.
     * A diferencia de eliminarActual() (que ya tenía el nodo), aquí hay que
     * buscar primero por URL. Casos de eliminación en lista doble NO circular:
     *
     *   A. Único nodo      → cabeza = cola = actual = null
     *   B. Eliminar cabeza → nueva cabeza = cabeza.siguiente, su anterior = null
     *   C. Eliminar cola   → nueva cola   = cola.anterior,   su siguiente = null
     *   D. Nodo intermedio → anterior.siguiente = cursor.siguiente
     *                        siguiente.anterior = cursor.anterior
     *
     * En todos los casos: limpiar los punteros del nodo eliminado (= null)
     * para que el GC de Java pueda liberarlo.
     */
    public void cerrarPestanaActual(String url) {
        if (cabeza == null) { System.out.println("  No hay pestanas abiertas."); return; }

        // Paso 1: Buscar el nodo por URL
        pestaña cursor = cabeza;
        while (cursor != null) {
            if (cursor.url.equalsIgnoreCase(url)) break;
            cursor = cursor.siguiente;
        }
        if (cursor == null) {
            System.out.println("  No se encontro ninguna pestana con esa URL.");
            return;
        }

        String tituloEliminado = cursor.tituloPagina;

        // Paso 2: Eliminar según el caso
        if (tamano == 1) {
            // Caso A: único nodo
            cabeza = null; cola = null; actual = null;

        } else if (cursor == cabeza) {
            // Caso B: eliminar cabeza — el siguiente pasa a ser la nueva cabeza
            cabeza          = cabeza.siguiente;
            cabeza.anterior = null;              // La nueva cabeza no tiene anterior
            if (actual == cursor) actual = cabeza; // Reubicar foco

        } else if (cursor == cola) {
            // Caso C: eliminar cola — el anterior pasa a ser la nueva cola
            cola           = cola.anterior;
            cola.siguiente = null;               // La nueva cola no tiene siguiente
            if (actual == cursor) actual = cola;   // Reubicar foco

        } else {
            // Caso D: nodo intermedio — saltar sobre él en ambas direcciones
            cursor.anterior.siguiente = cursor.siguiente;
            cursor.siguiente.anterior = cursor.anterior;
            if (actual == cursor) actual = cursor.anterior; // Reubicar foco
        }

        // Paso 3: Limpiar punteros del nodo eliminado (buena práctica en Java)
        cursor.anterior = null;
        cursor.siguiente = null;

        tamano--;
        System.out.println("  Pestana cerrada: " + tituloEliminado);
        if (actual != null) System.out.println("  Foco ahora en: " + actual.tituloPagina);
        else                System.out.println("  No quedan pestanas abiertas.");
    }

    // ── Posición actual (búsqueda por referencia) ─────────────────────────────
    /**
     * REUTILIZABLE: Contar posición de un nodo recorriendo desde cabeza.
     * Condición de parada doble: cursor != null (fin de lista) O cursor == actual
     * (encontramos el nodo). Aplica igual en lista circular y no circular.
     */
    private int posicionActual() {
        pestaña cursor = cabeza;
        int pos = 1;
        while (cursor != null && cursor != actual) {
            cursor = cursor.siguiente;
            pos++;
        }
        return pos;
    }

    public void verPestanaActual() {
        if (actual == null) { System.out.println("  No hay pestanas abiertas."); return; }
        int pos = posicionActual();
        System.out.println("  Pestana activa " + pos + " de " + tamano + ":");
        System.out.println("  " + actual);
    }

    public boolean estaVacio() { return tamano == 0; }
    public int getTamano()     { return tamano; }
}