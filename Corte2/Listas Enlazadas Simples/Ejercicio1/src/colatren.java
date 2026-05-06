/**
 * Lista SIMPLEMENTE enlazada NO circular — Cola de vagones de tren.
 * Es la estructura más básica: cada nodo solo tiene "siguiente", sin "anterior".
 * Sin puntero "cola" — inserta recorriendo hasta el último nodo.
 *
 * APORTES: Inserción simple + acumulador de campo numérico.
 */
public class colatren {

    vagon cabeza; // Único puntero necesario en lista simple sin "cola"

    public colatren() {
        this.cabeza = null;
    }

    // ── Inserción al final sin puntero cola ──────────────────────────────────
    /**
     * REUTILIZABLE: Recorrer hasta el último nodo (actual.siguiente == null)
     * y enganchar el nuevo ahí. Sin puntero "cola" es O(n) pero más simple.
     * Ya visto en TorneoRound — mismo patrón.
     */
    public void agregarVagon(vagon nuevoVagon) {
        if (cabeza == null) {
            cabeza = nuevoVagon;
        } else {
            vagon actual = cabeza;
            while (actual.siguiente != null) { // Buscar el último nodo
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoVagon; // Enganchar al final
        }
    }

    // ── Acumulador numérico ──────────────────────────────────────────────────
    /**
     * REUTILIZABLE: Mismo patrón de duracionTotal() en ReproductorMusica.
     * Recorrer con while(actual != null) sumando un campo de cada nodo.
     * Aquí retorna el valor en lugar de imprimirlo — más flexible.
     */
    public double calcularPesoTotal() {
        double pesoTotal = 0.0;
        vagon  actual    = cabeza;
        while (actual != null) {
            pesoTotal += actual.pesoToneladas;
            actual     = actual.siguiente;
        }
        return pesoTotal;
    }
}