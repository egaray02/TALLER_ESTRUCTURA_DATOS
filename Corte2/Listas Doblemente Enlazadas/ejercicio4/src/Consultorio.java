/**
 * Lista DOBLEMENTE enlazada NO circular — Consultorio médico con prioridad.
 * ESTRUCTURA: Lista doble NO circular, igual que los anteriores.
 * APORTES NUEVOS:
 *   1. moverDespuesDeCabeza() — reubicar un nodo ya insertado a una posición específica
 *   2. pacienteMayorEdad()    — recorrido inverso (cola → cabeza) buscando el máximo
 */
public class Consultorio {

    private Paciente cabeza;
    private Paciente cola;
    private int      tamano;

    public Consultorio() {
        cabeza = null;
        cola   = null;
        tamano = 0;
    }

    // ── Inserción con prioridad de emergencia ─────────────────────────────────
    /**
     * Inserta al final como siempre, pero si urgencia == 5 (emergencia),
     * mueve el nodo recién insertado justo después de cabeza.
     * REUTILIZABLE: Patrón "insertar + reubicar según condición".
     * Separa la lógica de inserción de la de reubicación — más limpio.
     */
    public void agregarPaciente(String nombre, int edad, int urgencia) {
        Paciente nuevo = new Paciente(nombre, edad, urgencia);
        if (cabeza == null) {
            cabeza = nuevo;
            cola   = nuevo;
        } else {
            nuevo.anterior = cola;
            cola.siguiente = nuevo;
            cola           = nuevo;
        }
        tamano++;
        System.out.println("  Paciente agregado: " + nuevo.nombre);

        // Post-inserción: si es emergencia, reubicarlo al frente
        if (urgencia == 5) {
            moverDespuesDeCabeza(nuevo);
        }
    }

    // ── Reubicación de nodo en lista doble ────────────────────────────────────
    /**
     * REUTILIZABLE: Patrón "mover nodo a posición específica" en lista doble.
     * Pasos:
     *   1. Verificar si ya está en la posición deseada (evitar trabajo innecesario)
     *   2. Desconectarlo de su posición actual (3 subcasos: cola, cabeza, intermedio)
     *   3. Reconectarlo en la nueva posición
     *
     * Aquí se mueve justo después de cabeza (segunda posición),
     * pero el patrón se adapta a cualquier posición destino.
     */
    private void moverDespuesDeCabeza(Paciente p) {
        // Caso trivial: ya es cabeza o ya está en segunda posición
        if (p == cabeza || p == cabeza.siguiente) return;

        // Paso 1: Desconectar de su posición actual
        if (p == cola) {
            // Era la cola — el anterior pasa a ser la nueva cola
            cola           = p.anterior;
            cola.siguiente = null;
        } else {
            // Era un nodo intermedio — saltar sobre él
            p.anterior.siguiente = p.siguiente;
            p.siguiente.anterior = p.anterior;
        }

        // Paso 2: Insertar justo después de cabeza
        p.siguiente = cabeza.siguiente;  // p apunta al ex-segundo nodo
        p.anterior  = cabeza;            // p apunta hacia atrás a cabeza

        if (cabeza.siguiente != null) {
            cabeza.siguiente.anterior = p; // El ex-segundo apunta hacia atrás a p
        }
        cabeza.siguiente = p;            // Cabeza apunta hacia adelante a p

        System.out.println("  EMERGENCIA: " + p.nombre +
                           " movido al frente de la fila (urgencia 5).");
    }

    // ── Atender (eliminar cabeza) ─────────────────────────────────────────────
    /**
     * REUTILIZABLE: Eliminar siempre desde cabeza — patrón de cola FIFO.
     * Es el caso B de eliminación: cabeza = cabeza.siguiente, su anterior = null.
     */
    public void atenderSiguiente() {
        if (cabeza == null) { System.out.println("  No hay pacientes en espera."); return; }
        System.out.println("  Atendiendo a: " + cabeza);
        if (tamano == 1) {
            cabeza = null; cola = null;
        } else {
            cabeza          = cabeza.siguiente;
            cabeza.anterior = null; // La nueva cabeza no tiene anterior
        }
        tamano--;
    }

    // Listar — idéntico a Navegador/Reproductor
    public void mostrarFila() {
        if (cabeza == null) { System.out.println("  La fila esta vacia."); return; }
        System.out.println("  === Fila de espera (" + tamano + " pacientes) ===");
        Paciente cursor = cabeza;
        int pos = 1;
        while (cursor != null) {
            System.out.println("  " + pos + ". " + cursor);
            cursor = cursor.siguiente;
            pos++;
        }
    }

    // ── Recorrido inverso buscando el máximo ──────────────────────────────────
    /**
     * REUTILIZABLE: Recorrer la lista de cola a cabeza usando ".anterior".
     * Útil cuando los datos más relevantes están al final, o para demostrar
     * el recorrido bidireccional de la lista doble.
     *
     * Patrón búsqueda del máximo en recorrido inverso:
     *   mayor  = cola   (candidato inicial = último nodo)
     *   cursor = cola   (empezar desde el final)
     *   while (cursor != null) { comparar, avanzar con cursor.anterior }
     */
    public void pacienteMayorEdad() {
        if (cabeza == null) { System.out.println("  La fila esta vacia."); return; }

        Paciente cursor = cola;   // Empezar desde el final
        Paciente mayor  = cola;   // Asumir que el último es el mayor
        System.out.println("  === Recorrido de COLA a CABEZA ===");
        while (cursor != null) {
            System.out.println("  Revisando: " + cursor);
            if (cursor.edad > mayor.edad) mayor = cursor;
            cursor = cursor.anterior; // Retroceder
        }
        System.out.println("\n  Paciente de mayor edad encontrado:\n  " + mayor);
    }

    public boolean estaVacia() { return tamano == 0; }
    public int getTamano()     { return tamano; }
}