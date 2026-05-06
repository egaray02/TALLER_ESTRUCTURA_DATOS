/**
 * Lista SIMPLEMENTE enlazada NO circular — Cola de aterrizaje con prioridad.
 * ESTRUCTURA: Idéntica a listaproductos — inserción condicional inicio/final.
 * APORTE NUEVO: reportarEmergencia() — buscar por campo String y mover a cabeza
 * llevando el puntero "anterior" durante el recorrido (lista simple, sin ".anterior").
 */
public class ColaTorre {

    private Vuelo cabeza;

    // ── Inserción condicional — idéntico a listaproductos.agregar() ───────────
    /**
     * Combustible < 10 → insertar al inicio (prioridad)
     * Combustible >= 10 → insertar al final (normal)
     * Mismo patrón que insertarAlInicio() / insertarAlFinal() ya comentados.
     */
    public void agregarVuelo(Vuelo nuevo) {
        if (nuevo.combustibleRestante < 10) {
            // Inserción al inicio — O(1)
            nuevo.siguiente = cabeza;
            cabeza          = nuevo;
            System.out.println("ALERTA: Vuelo " + nuevo.numeroVuelo
                    + " con bajo combustible movido al INICIO.");
        } else {
            // Inserción al final — O(n)
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                Vuelo actual = cabeza;
                while (actual.siguiente != null) actual = actual.siguiente;
                actual.siguiente = nuevo;
            }
            System.out.println("Vuelo " + nuevo.numeroVuelo + " agregado al final.");
        }
    }

    // ── Buscar por campo y mover a cabeza (lista simple) ─────────────────────
    /**
     * REUTILIZABLE: En lista SIMPLE no tienes ".anterior" en cada nodo,
     * así que para poder desconectar un nodo necesitas llevar dos punteros
     * durante el recorrido:
     *   anterior → nodo previo al que buscas
     *   actual   → nodo que estás revisando
     *
     * Cuando encuentras el nodo:
     *   anterior.siguiente = actual.siguiente  (saltar sobre él)
     *   actual.siguiente   = cabeza            (apuntar al inicio)
     *   cabeza             = actual            (el encontrado es la nueva cabeza)
     *
     * Este patrón "dos punteros" es obligatorio en lista simple para eliminar
     * o mover nodos intermedios — en lista doble no lo necesitas porque cada
     * nodo ya trae su ".anterior".
     */
    public void reportarEmergencia(String numeroVuelo) {
        if (cabeza == null) { System.out.println("La cola está vacía."); return; }
        if (cabeza.numeroVuelo.equals(numeroVuelo)) {
            System.out.println("El vuelo " + numeroVuelo + " ya está al inicio.");
            return;
        }

        Vuelo anterior = cabeza;           // Puntero al nodo previo
        Vuelo actual   = cabeza.siguiente; // Puntero al nodo actual

        while (actual != null) {
            if (actual.numeroVuelo.equals(numeroVuelo)) {
                anterior.siguiente = actual.siguiente; // Desconectar
                actual.siguiente   = cabeza;           // Apuntar al inicio
                cabeza             = actual;           // Pasar a ser cabeza
                System.out.println("EMERGENCIA: Vuelo " + numeroVuelo
                        + " movido al INICIO.");
                return;
            }
            anterior = actual;           // Avanzar ambos punteros
            actual   = actual.siguiente;
        }
        System.out.println("Vuelo " + numeroVuelo + " no encontrado.");
    }

    // Listar — idéntico a todos los anteriores con while(actual != null)
    public void imprimirCola() {
        System.out.println("\n==============================================");
        System.out.println("   Cola de aterrizaje (orden de atención)");
        System.out.println("==============================================");
        if (cabeza == null) { System.out.println("  (cola vacía)"); return; }
        Vuelo actual = cabeza;
        int   pos    = 1;
        while (actual != null) {
            System.out.println("[" + pos + "] " + actual.numeroVuelo
                    + " | " + actual.aerolinea
                    + " | combustible: " + actual.combustibleRestante
                    + " | pasajeros: " + actual.pasajeros);
            actual = actual.siguiente;
            pos++;
        }
    }
}