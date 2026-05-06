/**
 * Lista DOBLEMENTE enlazada NO circular — Reproductor de música.
 * ESTRUCTURA: Idéntica a Navegador.java y Galeria.java.
 * APORTES NUEVOS:
 *   1. modoRepeticion — bandera booleana que cambia el comportamiento de navegación
 *   2. duracionTotal() — acumular un valor numérico recorriendo la lista
 */
public class ReproductorMusica {

    private cancion cabeza;
    private cancion cola;
    private cancion actual;
    private int     tamano;
    private boolean modoRepeticion; // ← NUEVO: estado extra que modifica el comportamiento

    public ReproductorMusica() {
        cabeza         = null;
        cola           = null;
        actual         = null;
        tamano         = 0;
        modoRepeticion = false; // Por defecto desactivado
    }

    // Inserción — idéntico a Navegador/Galeria
    public void agregarCancion(String titulo, String artista, int duracion) {
        cancion nueva = new cancion(titulo, artista, duracion);
        if (cabeza == null) {
            cabeza = cola = actual = nueva; // Los tres apuntan al único nodo
        } else {
            nueva.anterior = cola;
            cola.siguiente = nueva;
            cola           = nueva;
        }
        tamano++;
        System.out.println("  Cancion agregada: " + nueva.titulo + " - " + nueva.artista);
    }

    // ── Navegación con modo repetición ───────────────────────────────────────
    /**
     * REUTILIZABLE: Patrón de navegación con comportamiento condicional.
     * Cuando llegas al límite, en lugar de bloquearte puedes volver al inicio
     * si hay una bandera activa. Útil para cualquier reproductor o carrusel
     * que necesite modo "loop" opcional.
     *
     *   modoRepeticion = false → comportamiento normal con límites (como Navegador)
     *   modoRepeticion = true  → al llegar al final, vuelve a cabeza
     */
    public void saltarAdelante() {
        if (actual == null) { System.out.println("  El reproductor esta vacio."); return; }
        if (actual.siguiente == null) {
            if (modoRepeticion) {
                actual = cabeza; // Volver al inicio
                System.out.println("  Repeticion: volviendo al inicio. Reproduciendo: " + actual.titulo);
            } else {
                System.out.println("  Ya estas en la ULTIMA cancion.");
            }
        } else {
            actual = actual.siguiente;
            System.out.println("  Siguiente: " + actual);
        }
    }

    // Sin modo repetición hacia atrás — idéntico a Navegador.pestanaAnterior()
    public void saltarAtras() {
        if (actual == null)          { System.out.println("  El reproductor esta vacio."); return; }
        if (actual.anterior == null) { System.out.println("  Error: ya estas en la PRIMERA cancion."); return; }
        actual = actual.anterior;
        System.out.println("  Anterior: " + actual);
    }

    // Búsqueda por título + eliminación — idéntico a Navegador.cerrarPestanaActual()
    public void eliminarCancion(String titulo) {
        if (cabeza == null) { System.out.println("  El reproductor esta vacio."); return; }

        cancion cursor = cabeza;
        while (cursor != null && !cursor.titulo.equalsIgnoreCase(titulo))
            cursor = cursor.siguiente;

        if (cursor == null) { System.out.println("  No se encontro: " + titulo); return; }

        if (tamano == 1) {
            cabeza = cola = actual = null;
        } else if (cursor == cabeza) {
            cabeza          = cabeza.siguiente;
            cabeza.anterior = null;
            if (actual == cursor) actual = cabeza;
        } else if (cursor == cola) {
            cola           = cola.anterior;
            cola.siguiente = null;
            if (actual == cursor) actual = cola;
        } else {
            cursor.anterior.siguiente = cursor.siguiente;
            cursor.siguiente.anterior = cursor.anterior;
            if (actual == cursor) actual = cursor.anterior;
        }

        cursor.anterior = cursor.siguiente = null; // Limpiar punteros
        tamano--;
        System.out.println("  Eliminada: " + titulo);
        if (actual != null) System.out.println("  Reproduciendo ahora: " + actual.titulo);
    }

    // Listar — idéntico a Navegador.mostrarTodas()
    public void mostrarLista() {
        if (cabeza == null) { System.out.println("  El reproductor esta vacio."); return; }
        System.out.println("  === Lista (" + tamano + " canciones) ===");
        cancion cursor = cabeza;
        int pos = 1;
        while (cursor != null) {
            System.out.println("  " + pos + ". " + cursor
                    + (cursor == actual ? "  <<< REPRODUCIENDO" : ""));
            cursor = cursor.siguiente;
            pos++;
        }
    }

    // Toggle — idéntico al patrón de Editor/Galeria
    public void toggleRepeticion() {
        modoRepeticion = !modoRepeticion;
        System.out.println("  Modo repeticion " + (modoRepeticion ? "ACTIVADO." : "DESACTIVADO."));
    }

    // ── Acumulador sobre la lista ─────────────────────────────────────────────
    /**
     * REUTILIZABLE: Patrón de acumulación — sumar un campo de todos los nodos.
     * Sirve para: duración total, peso total, suma de precios, promedio, etc.
     *   int total = 0;
     *   while (cursor != null) { total += cursor.campo; cursor = cursor.siguiente; }
     *
     * Conversión de segundos a h/m/s:
     *   horas   = total / 3600
     *   minutos = (total % 3600) / 60
     *   segundos = total % 60
     */
    public void duracionTotal() {
        if (cabeza == null) { System.out.println("  El reproductor esta vacio."); return; }
        int total  = 0;
        cancion cursor = cabeza;
        while (cursor != null) {
            total += cursor.duracion; // Acumular
            cursor = cursor.siguiente;
        }
        int h = total / 3600, m = (total % 3600) / 60, s = total % 60;
        System.out.println("  Duracion total: " + (h > 0 ? h + "h " : "") + m + "m " + s + "s");
    }

    private int posicionActual() {
        cancion cursor = cabeza;
        int pos = 1;
        while (cursor != null && cursor != actual) { cursor = cursor.siguiente; pos++; }
        return pos;
    }

    public void verCancionActual() {
        if (actual == null) { System.out.println("  El reproductor esta vacio."); return; }
        System.out.println("  Reproduciendo " + posicionActual() + " de " + tamano + ":");
        System.out.println("  " + actual);
    }

    public boolean estaVacio() { return tamano == 0; }
    public int getTamano()     { return tamano; }
}