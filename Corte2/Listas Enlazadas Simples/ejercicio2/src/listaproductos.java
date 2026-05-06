/**
 * Lista SIMPLEMENTE enlazada NO circular — Estante de productos con prioridad.
 * ESTRUCTURA: Lista simple, igual que colatren.
 * APORTE NUEVO: Inserción condicional — inicio o final según un criterio.
 * También introduce filtrado al recorrer (imprimir solo los que cumplen condición).
 */
public class listaproductos {

    producto cabeza;

    public listaproductos() {
        this.cabeza = null;
    }

    // ── Inserción condicional: inicio o final según criterio ─────────────────
    /**
     * REUTILIZABLE: Patrón "insertar según condición".
     * Delegar a dos métodos privados mantiene agregar() limpio y legible.
     * Cambia la condición y los métodos para cualquier regla de prioridad.
     */
    public void agregar(producto nuevo) {
        if (nuevo.diasParaVencer < 3) {
            insertarAlInicio(nuevo);  // Urgente → frente de la lista
        } else {
            insertarAlFinal(nuevo);   // Normal  → final de la lista
        }
    }

    // ── Inserción al inicio — O(1) ───────────────────────────────────────────
    /**
     * REUTILIZABLE: El nuevo nodo apunta a la cabeza actual, luego pasa a ser cabeza.
     *   nuevo.siguiente = cabeza  →  nuevo queda antes de todos
     *   cabeza          = nuevo   →  nuevo es la nueva cabeza
     * No necesita recorrer la lista — siempre O(1).
     */
    private void insertarAlInicio(producto nuevo) {
        nuevo.siguiente = cabeza; // El nuevo apunta al que antes era el primero
        cabeza          = nuevo;  // El nuevo pasa a ser la cabeza
        System.out.println(nuevo.nombre + " insertado al INICIO (vence en "
                + nuevo.diasParaVencer + " días)");
    }

    // ── Inserción al final — O(n) ────────────────────────────────────────────
    /**
     * REUTILIZABLE: Mismo patrón que colatren.agregarVagon().
     * Recorrer hasta actual.siguiente == null y enganchar ahí.
     */
    private void insertarAlFinal(producto nuevo) {
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            producto actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        System.out.println(nuevo.nombre + " insertado al FINAL   (vence en "
                + nuevo.diasParaVencer + " días)");
    }

    // ── Recorrido completo ───────────────────────────────────────────────────
    // Mismo patrón while(actual != null) de siempre en lista simple
    public void imprimirEstante() {
        System.out.println("\n==============================================");
        System.out.println("   Estante de lácteos (orden de venta)");
        System.out.println("==============================================");
        if (cabeza == null) { System.out.println("  (estante vacío)"); return; }
        producto actual = cabeza;
        int pos = 1;
        while (actual != null) {
            System.out.println("[" + pos + "] " + actual.nombre
                    + " | cantidad: " + actual.cantidad
                    + " | vence en: " + actual.diasParaVencer + " día(s)");
            actual = actual.siguiente;
            pos++;
        }
    }

    // ── Recorrido con filtro ─────────────────────────────────────────────────
    /**
     * REUTILIZABLE: Patrón "recorrer e imprimir solo los que cumplen condición".
     * Se usa una bandera booleana para saber si se encontró al menos uno.
     *   boolean hayProductos = false;
     *   while (...) { if (condicion) { imprimir; hayProductos = true; } }
     *   if (!hayProductos) { mensaje de lista vacía }
     */
    public void imprimirProximosAVencer() {
        System.out.println("\n==============================================");
        System.out.println("   Productos con menos de 5 días para vencer");
        System.out.println("==============================================");
        producto actual      = cabeza;
        boolean  hayProductos = false;
        while (actual != null) {
            if (actual.diasParaVencer < 5) { // Filtro: solo los próximos a vencer
                System.out.println("→ " + actual.nombre
                        + " | cantidad: " + actual.cantidad
                        + " | vence en: " + actual.diasParaVencer + " día(s)");
                hayProductos = true;
            }
            actual = actual.siguiente;
        }
        if (!hayProductos) System.out.println("  (ningún producto próximo a vencer)");
    }
}