public class Editor {
    Capa cabeza;
    Capa cola;
    Capa capaActiva;
    int tamanio;

    public Editor() {
        this.cabeza = null;
        this.cola = null;
        this.capaActiva = null;
        this.tamanio = 0;
    }

    public boolean estaVacio() {
        return cabeza == null;
    }

    public void agregarCapa(String nombre, boolean visible, String tipo) {
        Capa nueva = new Capa(nombre, visible, tipo);
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
        capaActiva = cola;
        tamanio++;
    }

    public void subirCapa() {
        if (estaVacio()) {
            System.out.println("No hay capas en el editor.");
            return;
        }
        capaActiva = capaActiva.siguiente;
        System.out.println("Capa activa: " + capaActiva.nombre);
    }

    public void bajarCapa() {
        if (estaVacio()) {
            System.out.println("No hay capas en el editor.");
            return;
        }
        capaActiva = capaActiva.anterior;
        System.out.println("Capa activa: " + capaActiva.nombre);
    }

    public void toggleVisibilidad() {
        if (estaVacio()) {
            System.out.println("No hay capas en el editor.");
            return;
        }
        capaActiva.visible = !capaActiva.visible;
        System.out.println("Capa '" + capaActiva.nombre + "' ahora es "
                + (capaActiva.visible ? "visible" : "oculta") + ".");
    }

    public void eliminarActiva() {
        if (estaVacio()) {
            System.out.println("No hay capas en el editor.");
            return;
        }

        System.out.println("Eliminando capa: " + capaActiva.nombre);
        Capa eliminada = capaActiva;

        if (tamanio == 1) {
            cabeza = null;
            cola = null;
            capaActiva = null;
            tamanio = 0;
            System.out.println("El editor quedo sin capas.");
            return;
        }

        capaActiva = eliminada.siguiente;

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

    public void mostrarCapas() {
        if (estaVacio()) {
            System.out.println("No hay capas en el editor.");
            return;
        }
        System.out.println("=== Capas del Editor ===");
        Capa actual = cabeza;
        int numero = 1;
        do {
            String activa = (actual == capaActiva) ? "[*] " : "    ";
            String visibilidad = actual.visible ? "visible" : "oculta ";
            System.out.println("  " + activa + numero + ". " + actual.nombre
                    + "  [" + actual.tipo + "]"
                    + "  (" + visibilidad + ")");
            actual = actual.siguiente;
            numero++;
        } while (actual != cabeza);
    }

    private Capa buscarCapa(String nombre) {
        if (estaVacio()) return null;
        Capa actual = cabeza;
        do {
            if (actual.nombre.equalsIgnoreCase(nombre)) return actual;
            actual = actual.siguiente;
        } while (actual != cabeza);
        return null;
    }

    public void seleccionarCapa(String nombre) {
        Capa encontrada = buscarCapa(nombre);
        if (encontrada == null) {
            System.out.println("Capa '" + nombre + "' no encontrada.");
            return;
        }
        capaActiva = encontrada;
        System.out.println("Capa activa: " + capaActiva.nombre);
    }
}