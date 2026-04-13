public class ReproductorMusica {

    private cancion cabeza;
    private cancion cola;
    private cancion actual;
    private int tamano;
    private boolean modoRepeticion;

    public ReproductorMusica() {
        cabeza         = null;
        cola           = null;
        actual         = null;
        tamano         = 0;
        modoRepeticion = false;
    }

    public void agregarCancion(String titulo, String artista, int duracion) {
        cancion nueva = new cancion(titulo, artista, duracion);
        if (cabeza == null) {
            cabeza = cola = actual = nueva;
        } else {
            nueva.anterior = cola;
            cola.siguiente = nueva;
            cola           = nueva;
        }
        tamano++;
        System.out.println("  Cancion agregada: " + nueva.titulo + " - " + nueva.artista);
    }

    public void verCancionActual() {
        if (actual == null) { System.out.println("  El reproductor esta vacio."); return; }
        System.out.println("  Reproduciendo " + posicionActual() + " de " + tamano + ":");
        System.out.println("  " + actual);
    }

    public void saltarAdelante() {
        if (actual == null) { System.out.println("  El reproductor esta vacio."); return; }
        if (actual.siguiente == null) {
            if (modoRepeticion) { actual = cabeza; System.out.println("  Repeticion: volviendo al inicio. Reproduciendo: " + actual.titulo); }
            else                { System.out.println("  Ya estas en la ULTIMA cancion."); }
        } else {
            actual = actual.siguiente;
            System.out.println("  Siguiente: " + actual);
        }
    }

    public void saltarAtras() {
        if (actual == null)          { System.out.println("  El reproductor esta vacio."); return; }
        if (actual.anterior == null) { System.out.println("  Error: ya estas en la PRIMERA cancion."); return; }
        actual = actual.anterior;
        System.out.println("  Anterior: " + actual);
    }

    public void eliminarCancion(String titulo) {
        if (cabeza == null) { System.out.println("  El reproductor esta vacio."); return; }

        cancion cursor = cabeza;
        while (cursor != null && !cursor.titulo.equalsIgnoreCase(titulo))
            cursor = cursor.siguiente;

        if (cursor == null) { System.out.println("  No se encontro: " + titulo); return; }

        if (tamano == 1) {
            cabeza = cola = actual = null;
        } else if (cursor == cabeza) {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            if (actual == cursor) actual = cabeza;
        } else if (cursor == cola) {
            cola = cola.anterior;
            cola.siguiente = null;
            if (actual == cursor) actual = cola;
        } else {
            cursor.anterior.siguiente = cursor.siguiente;
            cursor.siguiente.anterior = cursor.anterior;
            if (actual == cursor) actual = cursor.anterior;
        }

        cursor.anterior = cursor.siguiente = null;
        tamano--;
        System.out.println("  Eliminada: " + titulo);
        if (actual != null) System.out.println("  Reproduciendo ahora: " + actual.titulo);
    }

    public void mostrarLista() {
        if (cabeza == null) { System.out.println("  El reproductor esta vacio."); return; }
        System.out.println("  === Lista (" + tamano + " canciones) ===");
        cancion cursor = cabeza;
        int pos = 1;
        while (cursor != null) {
            System.out.println("  " + pos + ". " + cursor + (cursor == actual ? "  <<< REPRODUCIENDO" : ""));
            cursor = cursor.siguiente;
            pos++;
        }
    }

    public void toggleRepeticion() {
        modoRepeticion = !modoRepeticion;
        System.out.println("  Modo repeticion " + (modoRepeticion ? "ACTIVADO." : "DESACTIVADO."));
    }

    public void duracionTotal() {
        if (cabeza == null) { System.out.println("  El reproductor esta vacio."); return; }
        int total = 0;
        cancion cursor = cabeza;
        while (cursor != null) { total += cursor.duracion; cursor = cursor.siguiente; }
        int h = total / 3600, m = (total % 3600) / 60, s = total % 60;
        System.out.println("  Duracion total: " + (h > 0 ? h + "h " : "") + m + "m " + s + "s");
    }

    private int posicionActual() {
        cancion cursor = cabeza;
        int pos = 1;
        while (cursor != null && cursor != actual) { cursor = cursor.siguiente; pos++; }
        return pos;
    }

    public boolean estaVacio() { return tamano == 0; }
    public int getTamano()     { return tamano; }
}