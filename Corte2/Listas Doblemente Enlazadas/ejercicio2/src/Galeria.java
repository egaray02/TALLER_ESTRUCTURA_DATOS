/**
 * Lista DOBLEMENTE enlazada NO circular — Galería de fotografías.
 * ESTRUCTURA: Idéntica a Navegador.java.
 *
 * ÚNICO APORTE NUEVO: reproducirGaleria() — recorre la lista en ambas
 * direcciones usando los dos punteros de la lista doble.
 */
public class Galeria {

    private Fotografia cabeza;
    private Fotografia cola;
    private Fotografia actual;
    private int        tamano;

    public Galeria() {
        cabeza = null; cola = null; actual = null; tamano = 0;
    }

    // Inserción — idéntico a Navegador.abrirPestana() sin reubicar actual
    public void agregarFoto(String nombre, double mb, String res) {
        Fotografia nueva = new Fotografia(nombre, mb, res);
        if (cabeza == null) {
            cabeza = nueva; cola = nueva; actual = nueva;
        } else {
            nueva.anterior = cola;
            cola.siguiente = nueva;
            cola           = nueva;
            // OJO: actual NO se mueve a la nueva foto — se queda donde estaba
        }
        tamano++;
        System.out.println("  Foto agregada: " + nueva.nombreArchivo);
    }

    // Navegación con límites — idéntico a Navegador
    public void siguienteFoto() {
        if (actual == null) { System.out.println("  La galeria esta vacia."); return; }
        if (actual.siguiente == null) {
            System.out.println("  Ya estas en la ULTIMA foto. No puedes avanzar mas.");
        } else {
            actual = actual.siguiente;
            System.out.println("  Siguiente foto:\n  " + actual);
        }
    }

    public void fotoAnterior() {
        if (actual == null) { System.out.println("  La galeria esta vacia."); return; }
        if (actual.anterior == null) {
            System.out.println("  Ya estas en la PRIMERA foto. No puedes retroceder.");
        } else {
            actual = actual.anterior;
            System.out.println("  Foto anterior:\n  " + actual);
        }
    }

    public void verFotoActual() {
        if (actual == null) { System.out.println("  La galeria esta vacia."); return; }
        System.out.println("  Foto " + posicionActual() + " de " + tamano + ":\n  " + actual);
    }

    // ── Reproducción bidireccional ────────────────────────────────────────────
    /**
     * REUTILIZABLE: La gran ventaja de la lista doble — recorrer en ambos sentidos.
     *   Hacia adelante: empezar en cabeza, avanzar con .siguiente hasta null
     *   Hacia atrás:    empezar en cola,   retroceder con .anterior hasta null
     * En una lista simple esto sería imposible sin guardar un arreglo auxiliar.
     */
    public void reproducirGaleria() {
        if (cabeza == null) { System.out.println("  La galeria esta vacia."); return; }

        // Recorrido hacia adelante: cabeza → cola
        System.out.println("\n  ==== Reproduciendo hacia ADELANTE ====");
        Fotografia cursor = cabeza;
        int pos = 1;
        while (cursor != null) {
            System.out.println("  " + pos + " de " + tamano + " | " + cursor);
            cursor = cursor.siguiente;
            pos++;
        }

        // Recorrido hacia atrás: cola → cabeza
        System.out.println("\n  ==== Reproduciendo hacia ATRAS ====");
        cursor = cola;
        pos    = tamano;
        while (cursor != null) {
            System.out.println("  " + pos + " de " + tamano + " | " + cursor);
            cursor = cursor.anterior; // ← solo posible en lista doble
            pos--;
        }

        System.out.println("\n  Reproduccion completa.");
    }

    // posicionActual — idéntico a Navegador
    private int posicionActual() {
        Fotografia cursor = cabeza;
        int pos = 1;
        while (cursor != null && cursor != actual) {
            cursor = cursor.siguiente; pos++;
        }
        return pos;
    }

    public boolean estaVacia() { return tamano == 0; }
    public int getTamano()     { return tamano; }
}