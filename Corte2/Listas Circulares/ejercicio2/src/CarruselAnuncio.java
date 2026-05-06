/**
 * Lista circular simplemente enlazada que actúa como carrusel de anuncios.
 * REUTILIZABLE: Cambia "Anuncio" por cualquier nodo que tenga campo "siguiente"
 * y los métodos que uses (getDuracion, getVecesRepetido, etc.).
 */
public class CarruselAnuncio {

    // ── Atributos de la lista circular ──────────────────────────────────────
    private Anuncio cabeza;   // Primer nodo (punto de entrada al ciclo)
    private Anuncio cola;     // Último nodo; su "siguiente" apunta siempre a cabeza
    private int     tamanio;  // Cantidad de nodos en la lista

    // ── Constructor ─────────────────────────────────────────────────────────
    /**
     * Crea un carrusel vacío.
     * PATRÓN: Siempre inicializa cabeza y cola en null, tamanio en 0.
     */
    public CarruselAnuncio() {
        cabeza  = null;
        cola    = null;
        tamanio = 0;
    }

    // ── Inserción al final (cola) ────────────────────────────────────────────
    /**
     * Agrega un nodo al final del carrusel manteniendo la circularidad.
     * REUTILIZABLE: La lógica de los dos casos (lista vacía / no vacía)
     * es estándar para cualquier lista circular.
     *
     *  Caso 1 — lista vacía:
     *    cabeza → nuevo → (vuelve a cabeza)   [un solo nodo se apunta a sí mismo]
     *
     *  Caso 2 — lista con nodos:
     *    ... → cola → nuevo → cabeza
     */
    public void agregar(Anuncio nuevo) {
        if (cabeza == null) {
            // Único nodo: él mismo cierra el ciclo
            cabeza         = nuevo;
            cola           = nuevo;
            cola.siguiente = cabeza;
        } else {
            // Enganchar el nuevo al final y cerrar el ciclo
            cola.siguiente = nuevo;   // El ex-último apunta al nuevo
            cola           = nuevo;   // El nuevo pasa a ser la cola
            cola.siguiente = cabeza;  // Cerrar el círculo
        }
        tamanio++;
    }

    // ── Recorrido circular por ciclos ────────────────────────────────────────
    /**
     * Recorre la lista circular "ciclos" veces completas.
     * REUTILIZABLE: El patrón do-while con "actual != cabeza" es el estándar
     * para recorrer UNA vuelta completa en cualquier lista circular.
     * Para N vueltas, simplemente envuélvelo en un for.
     */
    public void reproducir(int ciclos) {
        if (cabeza == null) {
            System.out.println("El carrusel está vacío.");
            return;
        }

        int tiempoTotal = 0;

        System.out.println("\nIniciando carrusel — " + ciclos + " ciclo(s)\n"
                         + "─".repeat(45));

        for (int c = 1; c <= ciclos; c++) {
            System.out.println("\n  Ciclo " + c + ":");

            Anuncio actual = cabeza;          // Siempre arrancar desde cabeza
            do {
                actual.incrementarRepeticion();
                tiempoTotal += actual.getDuracionSegundos();
                System.out.printf("    %-35s ×%d%n",
                        actual, actual.getVecesRepetido());
                actual = actual.siguiente;    // Avanzar al siguiente nodo
            } while (actual != cabeza);       // Parar al volver a cabeza
        }

        // ── Resumen final ────────────────────────────────────────────────────
        System.out.println("\n" + "─".repeat(45));
        System.out.println("  Tiempo total: " + tiempoTotal + " s"
                         + " (" + (tiempoTotal / 60) + " min "   // División entera = minutos
                         + (tiempoTotal % 60) + " s)");           // Módulo = segundos restantes
        System.out.println(" Más repetido: " + anuncioMasRepetido());
    }

    // ── Búsqueda del máximo en lista circular ────────────────────────────────
    /**
     * Recorre la lista buscando el nodo con mayor valor en un campo.
     * REUTILIZABLE: Patrón "máximo en lista circular":
     *   1. max = cabeza (asumir que el primero es el mayor)
     *   2. Recorrer desde cabeza.siguiente hasta volver a cabeza
     *   3. Actualizar max si encuentras uno mayor
     */
    private Anuncio anuncioMasRepetido() {
        Anuncio max    = cabeza;           // Candidato inicial
        Anuncio actual = cabeza.siguiente; // Empezar desde el segundo nodo
        while (actual != cabeza) {
            if (actual.getVecesRepetido() > max.getVecesRepetido()) {
                max = actual;             // Encontramos un nuevo máximo
            }
            actual = actual.siguiente;
        }
        return max;
    }

    // ── Listar todos los nodos ───────────────────────────────────────────────
    /**
     * Imprime todos los nodos de la lista circular numerados.
     * REUTILIZABLE: Mismo patrón do-while para recorrer una vuelta completa.
     */
    public void listar() {
        if (cabeza == null) {
            System.out.println("  (sin anuncios)");
            return;
        }
        Anuncio actual = cabeza;
        int i = 1;
        do {
            System.out.printf("  %d. %s  — repetido: %d vez(ces)%n",
                    i++, actual, actual.getVecesRepetido());
            actual = actual.siguiente;
        } while (actual != cabeza);  // Una vuelta completa
    }

    public int getTamanio() { return tamanio; }
}