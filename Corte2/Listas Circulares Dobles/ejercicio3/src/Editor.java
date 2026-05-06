/**
 * Lista circular DOBLEMENTE enlazada para editor de capas tipo Photoshop/Figma.
 * REUTILIZABLE: Misma estructura base que Historial.java (lista doble circular).
 * Novedad principal: búsqueda por nombre con equalsIgnoreCase() y
 * toggle de un atributo booleano sobre el nodo activo.
 *
 * Si ya entendiste Historial.java, este es prácticamente el mismo patrón
 * con distinto dominio (capas en lugar de comandos).
 */
public class Editor {

    // ── Atributos ────────────────────────────────────────────────────────────
    Capa cabeza;      // Primera capa de la lista
    Capa cola;        // Última capa de la lista
    Capa capaActiva;  // Capa actualmente seleccionada (equivalente a "cursor" en Historial)
    int  tamanio;

    // ── Constructor ──────────────────────────────────────────────────────────
    public Editor() {
        this.cabeza     = null;
        this.cola       = null;
        this.capaActiva = null;
        this.tamanio    = 0;
    }

    public boolean estaVacio() {
        return cabeza == null;
    }

    // ── Inserción al final ───────────────────────────────────────────────────
    /**
     * Idéntico a Historial.agregarComando() — lista doble circular.
     * REUTILIZABLE: Mismo patrón de 4 punteros:
     *   cola.siguiente / nueva.anterior / nueva.siguiente / cabeza.anterior
     */
    public void agregarCapa(String nombre, boolean visible, String tipo) {
        Capa nueva = new Capa(nombre, visible, tipo);
        if (cabeza == null) {
            nueva.siguiente = nueva;  // Único nodo: se apunta a sí mismo
            nueva.anterior  = nueva;  // en ambas direcciones
            cabeza          = nueva;
            cola            = nueva;
        } else {
            cola.siguiente  = nueva;   // Ex-cola apunta al nueva por delante
            nueva.anterior  = cola;    // Nueva apunta a ex-cola por detrás
            nueva.siguiente = cabeza;  // Nueva apunta a cabeza por delante
            cabeza.anterior = nueva;   // Cabeza apunta a nueva por detrás
            cola            = nueva;
        }
        capaActiva = cola; // La capa recién agregada queda activa
        tamanio++;
    }

    // ── Navegación bidireccional ─────────────────────────────────────────────
    /**
     * Idéntico a Historial.arriba() / abajo() — misma lógica, distinto nombre.
     * Como la lista es circular, navegar más allá de cola lleva a cabeza y viceversa.
     */
    public void subirCapa() {
        if (estaVacio()) { System.out.println("No hay capas en el editor."); return; }
        capaActiva = capaActiva.siguiente;
        System.out.println("Capa activa: " + capaActiva.nombre);
    }

    public void bajarCapa() {
        if (estaVacio()) { System.out.println("No hay capas en el editor."); return; }
        capaActiva = capaActiva.anterior;
        System.out.println("Capa activa: " + capaActiva.nombre);
    }

    // ── Toggle de visibilidad ────────────────────────────────────────────────
    /**
     * REUTILIZABLE: Patrón toggle — invertir un booleano con "!".
     * Sirve para cualquier atributo on/off: visible, activo, bloqueado, etc.
     *   campo = !campo   →   true pasa a false, false pasa a true
     */
    public void toggleVisibilidad() {
        if (estaVacio()) { System.out.println("No hay capas en el editor."); return; }
        capaActiva.visible = !capaActiva.visible; // Invertir el estado
        System.out.println("Capa '" + capaActiva.nombre + "' ahora es "
                + (capaActiva.visible ? "visible" : "oculta") + ".");
    }

    // ── Eliminación del nodo activo ──────────────────────────────────────────
    /**
     * Idéntico a Historial.eliminarActual() — misma lógica, distinto nombre.
     * REUTILIZABLE: Patrón de eliminación en lista doble circular:
     *   1. Guardar referencia al nodo a eliminar
     *   2. Mover capaActiva al siguiente ANTES de desconectar
     *   3. Desconectar en ambas direcciones
     *   4. Ajustar cabeza/cola si era alguno de ellos
     */
    public void eliminarActiva() {
        if (estaVacio()) { System.out.println("No hay capas en el editor."); return; }

        System.out.println("Eliminando capa: " + capaActiva.nombre);
        Capa eliminada = capaActiva;

        // Caso A: única capa — lista queda vacía
        if (tamanio == 1) {
            cabeza     = null;
            cola       = null;
            capaActiva = null;
            tamanio    = 0;
            System.out.println("El editor quedo sin capas.");
            return;
        }

        // Caso B: varias capas — desconectar en ambas direcciones
        capaActiva = eliminada.siguiente; // Mover ANTES de desconectar

        eliminada.anterior.siguiente = eliminada.siguiente;
        eliminada.siguiente.anterior = eliminada.anterior;

        if (eliminada == cabeza) cabeza = eliminada.siguiente;
        if (eliminada == cola)   cola   = eliminada.anterior;

        tamanio--;
    }

    // ── Mostrar todas las capas ──────────────────────────────────────────────
    /**
     * Recorre una vuelta completa marcando la capa activa con [*].
     * REUTILIZABLE: Mismo patrón do-while de siempre.
     * La comparación "actual == capaActiva" es por referencia — compara
     * si es el MISMO objeto en memoria, no si tienen el mismo nombre.
     */
    public void mostrarCapas() {
        if (estaVacio()) { System.out.println("No hay capas en el editor."); return; }

        System.out.println("=== Capas del Editor ===");
        Capa actual = cabeza;
        int  numero = 1;
        do {
            String activa      = (actual == capaActiva) ? "[*] " : "    ";
            String visibilidad = actual.visible ? "visible" : "oculta ";
            System.out.println("  " + activa + numero + ". " + actual.nombre
                    + "  [" + actual.tipo + "]"
                    + "  (" + visibilidad + ")");
            actual = actual.siguiente;
            numero++;
        } while (actual != cabeza);
    }

    // ── Búsqueda por nombre ──────────────────────────────────────────────────
    /**
     * REUTILIZABLE: Patrón de búsqueda en lista circular con do-while.
     * Retorna el nodo si lo encuentra, null si no existe.
     *
     * equalsIgnoreCase() → compara ignorando mayúsculas/minúsculas
     * equals()           → compara exacto (sensible a mayúsculas)
     * Usa equalsIgnoreCase() cuando el usuario escribe el nombre a mano.
     *
     * OJO: En lista circular siempre verifica estaVacio() antes de entrar
     * al do-while — si cabeza es null, "actual != cabeza" nunca se cumple
     * y entrarías en bucle infinito.
     */
    private Capa buscarCapa(String nombre) {
        if (estaVacio()) return null;
        Capa actual = cabeza;
        do {
            if (actual.nombre.equalsIgnoreCase(nombre)) return actual; // Encontrado
            actual = actual.siguiente;
        } while (actual != cabeza);
        return null; // No encontrado
    }

    // ── Seleccionar capa por nombre ──────────────────────────────────────────
    /**
     * REUTILIZABLE: Patrón "buscar + actuar si se encontró".
     * Delegar la búsqueda a buscarCapa() y solo manejar el resultado aquí
     * mantiene el código limpio y separado por responsabilidades.
     */
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