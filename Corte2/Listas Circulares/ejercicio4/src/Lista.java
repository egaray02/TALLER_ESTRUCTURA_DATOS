/**
 * Lista circular simplemente enlazada para gestión de turnos en restaurante.
 * REUTILIZABLE: Introduce dos variantes nuevas respecto a los ejercicios anteriores:
 *   1. "estaVacia()" como método auxiliar en lugar de verificar cabeza==null directo
 *   2. Reinserción al final: eliminar de cabeza y volver a poner al final (grupos VIP)
 *   3. Recorrido con "for (int i = 0; i < tamanio; i++)" como alternativa al do-while
 */
public class Lista {

    // ── Atributos ────────────────────────────────────────────────────────────
    private Grupo cabeza;
    private Grupo cola;
    private int   tamanio;

    // ── Constructor ──────────────────────────────────────────────────────────
    public Lista() {
        cabeza   = null;
        cola     = null;
        tamanio  = 0;
    }

    // ── Verificación de lista vacía ──────────────────────────────────────────
    /**
     * REUTILIZABLE: Extraer esta verificación como método propio
     * hace el código más legible — úsalo en todos tus ejercicios.
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    // ── Inserción al final ───────────────────────────────────────────────────
    /**
     * Crea el nodo internamente en lugar de recibirlo ya construido.
     * REUTILIZABLE: Ambas formas son válidas — aquí el método recibe
     * los datos sueltos y construye el nodo él mismo.
     * Mismo patrón circular de siempre: caso vacío / caso con nodos.
     */
    public void agregarGrupo(String nombre, int personas, boolean vip, int minutos) {
        Grupo nuevo = new Grupo(nombre, personas, vip, minutos);
        if (estaVacia()) {
            cabeza         = nuevo;
            cola           = nuevo;
            nuevo.siguiente = cabeza; // Un solo nodo se apunta a sí mismo
        } else {
            cola.siguiente = nuevo;  // Ex-cola apunta al nuevo
            cola           = nuevo;  // El nuevo es la nueva cola
            cola.siguiente = cabeza; // Cerrar el círculo
        }
        tamanio++;
    }

    // ── Atender siguiente grupo (cabeza) ─────────────────────────────────────
    /**
     * Elimina la cabeza de la lista, con un caso especial:
     * si el grupo es VIP, en lugar de eliminarlo se reinserta al final.
     *
     * REUTILIZABLE: El patrón "sacar de cabeza + reinsertar al final" aparece
     * en colas de prioridad, sistemas de turnos y Round Robin simplificado.
     *
     * Casos cubiertos:
     *   A. Lista vacía         → avisar y salir
     *   B. Único nodo no-VIP   → lista queda vacía
     *   C. Único nodo VIP      → se queda solo (no se elimina, no se mueve)
     *   D. Varios nodos no-VIP → eliminar cabeza, avanzar al siguiente
     *   E. Varios nodos VIP    → sacar de cabeza, reinsertar al final
     */
    public void atenderSiguiente() {
        if (estaVacia()) {
            System.out.println("No hay grupos en espera.");
            return;
        }

        Grupo atendido = cabeza; // Guardar referencia al grupo que se va a atender
        System.out.println("\nAtendiendo:");
        System.out.println(atendido.toString());

        // ── Caso especial: único nodo en la lista ────────────────────────────
        if (tamanio == 1) {
            if (atendido.esVip) {
                // VIP solo: se queda en su lugar, no hace falta mover nada
                System.out.println(">> VIP reinsertado al final.");
            } else {
                // No-VIP solo: vaciar la lista completamente
                cabeza   = null;
                cola     = null;
                tamanio  = 0;
                System.out.println(">> Grupo eliminado. Lista vacia.");
            }
            mostrarLista();
            return;
        }

        // ── Caso general: más de un nodo ─────────────────────────────────────
        // Paso 1: Avanzar cabeza al siguiente (sacar el grupo atendido del frente)
        cabeza         = cabeza.siguiente;
        cola.siguiente = cabeza; // Mantener circularidad

        if (atendido.esVip) {
            // Caso E: VIP → reinsertar al final sin decrementar tamanio
            atendido.siguiente = cabeza;  // El VIP apunta a la nueva cabeza
            cola.siguiente     = atendido; // La ex-cola apunta al VIP
            cola               = atendido; // El VIP es la nueva cola
            System.out.println(">> VIP reinsertado al final.");
            // tamanio NO cambia: salió del frente y entró al final
        } else {
            // Caso D: No-VIP → eliminado definitivamente
            tamanio--;
            System.out.println(">> Grupo eliminado de la lista.");
        }

        mostrarLista();
    }

    // ── Mostrar estado actual de la lista ────────────────────────────────────
    /**
     * Recorre la lista usando "for + tamanio" en lugar del do-while habitual.
     * REUTILIZABLE: Esta alternativa es útil cuando ya tienes "tamanio" confiable
     * y quieres controlar exactamente cuántas iteraciones hacer.
     *
     * do-while (actual != cabeza) → cuando no tienes tamanio o no es confiable
     * for (int i = 0; i < tamanio; i++) → cuando tamanio está actualizado
     * Ambos producen el mismo resultado en una lista circular bien mantenida.
     */
    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("Lista vacia.");
            return;
        }
        System.out.print("Estado lista: ");
        Grupo actual = cabeza;
        for (int i = 0; i < tamanio; i++) {
            // Imprimir nombre + asterisco si es VIP
            System.out.print("[" + actual.nombreReserva + (actual.esVip ? "*" : "") + "]");
            if (i < tamanio - 1) System.out.print(" -> "); // Separador entre nodos
            actual = actual.siguiente;
        }
        System.out.println(" -> (cabeza)"); // Recordatorio visual de la circularidad
    }
}