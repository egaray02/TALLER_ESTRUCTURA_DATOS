public class Galeria {
    Foto cabeza;
    Foto cola;
    Foto actual;
    int tamanio;

    public Galeria() {
        this.cabeza = null;
        this.cola = null;
        this.actual = null;
        this.tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregarFoto(String titulo, String fecha, boolean esFavorita) {
        Foto nueva = new Foto(titulo, fecha, esFavorita);
        if (cabeza == null) {
            nueva.siguiente = nueva;
            nueva.anterior = nueva;
            cabeza = nueva;
            cola = nueva;
        } else {
            cola.siguiente = nueva;
            nueva.anterior = cola;
            nueva.siguiente = cabeza;
            cabeza.anterior = nueva;
            cola = nueva;
        }
        actual = cabeza;
        tamanio++;
    }

    public void siguiente() {
        if (estaVacia()) {
            System.out.println("La galeria esta vacia.");
            return;
        }
        actual = actual.siguiente;
        System.out.println("Foto actual: " + actual.titulo);
    }

    public void anterior() {
        if (estaVacia()) {
            System.out.println("La galeria esta vacia.");
            return;
        }
        actual = actual.anterior;
        System.out.println("Foto actual: " + actual.titulo);
    }

    public void toggleFavorita() {
        if (estaVacia()) {
            System.out.println("La galeria esta vacia.");
            return;
        }
        actual.esFavorita = !actual.esFavorita;
        System.out.println("'" + actual.titulo + "' ahora es "
                + (actual.esFavorita ? "favorita." : "no favorita."));
    }

    public void eliminarActual() {
        if (estaVacia()) {
            System.out.println("La galeria esta vacia.");
            return;
        }

        System.out.println("Eliminando foto: " + actual.titulo);
        Foto eliminada = actual;

        if (tamanio == 1) {
            cabeza = null;
            cola = null;
            actual = null;
            tamanio = 0;
            System.out.println("La galeria quedo vacia.");
            return;
        }

        actual = eliminada.siguiente;

        eliminada.anterior.siguiente = eliminada.siguiente;
        eliminada.siguiente.anterior = eliminada.anterior;

        if (eliminada == cabeza) {
            cabeza = eliminada.siguiente;
        }
        if (eliminada == cola) {
            cola = eliminada.anterior;
        }

        tamanio--;
    }

    public void mostrarGaleria() {
        if (estaVacia()) {
            System.out.println("La galeria esta vacia.");
            return;
        }
        System.out.println("=== Galeria de Fotos ===");
        Foto f = cabeza;
        int numero = 1;
        do {
            String marcaActual = (f == actual)    ? "[>] " : "    ";
            String marcaFav    = f.esFavorita     ? "[*] " : "    ";
            System.out.println("  " + marcaActual + numero + ". " + f.titulo
                    + "  (" + f.fecha + ")"
                    + "  " + marcaFav);
            f = f.siguiente;
            numero++;
        } while (f != cabeza);
        System.out.println("  Total: " + tamanio + " foto(s)");
    }
}