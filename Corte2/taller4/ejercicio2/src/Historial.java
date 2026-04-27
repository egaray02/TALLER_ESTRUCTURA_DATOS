public class Historial {
    Comando cabeza;
    Comando cola;
    Comando cursor;
    int tamanio;

    public Historial() {
        this.cabeza = null;
        this.cola = null;
        this.cursor = null;
        this.tamanio = 0;
    }

    public boolean estaVacio() {
        return cabeza == null;
    }

    public void agregarComando(String texto, boolean exitoso, String directorio) {
        Comando nuevo = new Comando(texto, exitoso, directorio);
        if (cabeza == null) {
            nuevo.siguiente = nuevo;
            nuevo.anterior = nuevo;
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
            cola = nuevo;
        }
        cursor = cola;
        tamanio++;
    }

    public void arriba() {
        if (estaVacio()) {
            System.out.println("El historial esta vacio.");
            return;
        }
        cursor = cursor.anterior;
        mostrarCursor();
    }

    public void abajo() {
        if (estaVacio()) {
            System.out.println("El historial esta vacio.");
            return;
        }
        cursor = cursor.siguiente;
        mostrarCursor();
    }

    public void mostrarCursor() {
        if (estaVacio()) {
            System.out.println("El historial esta vacio.");
            return;
        }
        System.out.println("  [cursor] " + cursor.directorio + " $ " + cursor.texto
                + "  (" + (cursor.exitoso ? "OK" : "ERROR") + ")");
    }

    public void eliminarActual() {
        if (estaVacio()) {
            System.out.println("El historial esta vacio.");
            return;
        }

        System.out.println("Eliminando: " + cursor.texto);
        Comando eliminado = cursor;

        if (tamanio == 1) {
            cabeza = null;
            cola = null;
            cursor = null;
            tamanio = 0;
            System.out.println("El historial quedo vacio.");
            return;
        }

        cursor = eliminado.siguiente;

        eliminado.anterior.siguiente = eliminado.siguiente;
        eliminado.siguiente.anterior = eliminado.anterior;

        if (eliminado == cabeza) {
            cabeza = eliminado.siguiente;
        }
        if (eliminado == cola) {
            cola = eliminado.anterior;
        }

        tamanio--;
    }

    public void mostrarHistorial() {
        if (estaVacio()) {
            System.out.println("El historial esta vacio.");
            return;
        }
        System.out.println("=== Historial de Comandos ===");
        Comando actual = cabeza;
        int numero = 1;
        do {
            String marca = (actual == cursor) ? " cursor" : "";
            System.out.println("  " + numero + ". [" + actual.directorio + "] $ "
                    + actual.texto
                    + "  (" + (actual.exitoso ? "OK" : "ERROR") + ")"
                    + marca);
            actual = actual.siguiente;
            numero++;
        } while (actual != cabeza);
    }
}