/**
 * Lista circular simplemente enlazada para gestión de torneo Round Robin.
 * REUTILIZABLE: Introduce tres técnicas nuevas:
 *   1. Inserción recorriendo hasta el último nodo (sin puntero "cola")
 *   2. Convertir lista circular a arreglo para operaciones que necesitan índices
 *   3. Algoritmo de rotación de arreglo para generar fixture
 */
public class TorneoRound {

    // ── Atributos ────────────────────────────────────────────────────────────
    // OJO: Este ejercicio NO tiene puntero "cola" — lo reemplaza recorriendo
    // hasta el último nodo. Más simple de mantener, pero más lento O(n) vs O(1)
    private Equipo cabeza;
    private int    tamanio;

    // ── Constructor ──────────────────────────────────────────────────────────
    public TorneoRound() {
        cabeza  = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    // ── Inserción al final SIN puntero cola ──────────────────────────────────
    /**
     * REUTILIZABLE: Alternativa a mantener un puntero "cola".
     * Se recorre la lista hasta encontrar el nodo cuyo "siguiente" es cabeza
     * — ese es el último nodo.
     *
     * Con puntero cola  → insertar es O(1)  (acceso directo)
     * Sin puntero cola  → insertar es O(n)  (hay que recorrer hasta el final)
     * Usa "cola" cuando el rendimiento importe; este enfoque cuando quieras
     * simplicidad en los atributos de la clase.
     */
    public void agregarEquipo(String nombre, String ciudad, int puntos, int golesFavor) {
        Equipo nuevo = new Equipo(nombre, ciudad, puntos, golesFavor);
        if (estaVacia()) {
            cabeza          = nuevo;
            cabeza.siguiente = cabeza; // Único nodo: se apunta a sí mismo
        } else {
            // Recorrer hasta el último nodo (aquel cuyo siguiente es cabeza)
            Equipo actual = cabeza;
            while (actual.siguiente != cabeza) {
                actual = actual.siguiente;
            }
            // "actual" ahora es el último — enganchar el nuevo y cerrar el círculo
            actual.siguiente = nuevo;
            nuevo.siguiente  = cabeza;
        }
        tamanio++;
    }

    // ── Convertir lista circular a arreglo ───────────────────────────────────
    /**
     * REUTILIZABLE: Cuando necesitas índices (posición [i], [i-1], invertir)
     * es mucho más cómodo trabajar con un arreglo que con la lista circular.
     * Patrón: recorrer con "for + tamanio" y copiar cada nodo al arreglo.
     *
     * Úsalo cuando necesites: ordenar, generar emparejamientos, rotar, etc.
     * IMPORTANTE: El arreglo contiene referencias a los mismos nodos —
     * modificar arr[i].puntos también modifica el nodo en la lista.
     */
    private Equipo[] obtenerArreglo() {
        Equipo[] arr   = new Equipo[tamanio];
        Equipo actual  = cabeza;
        for (int i = 0; i < tamanio; i++) {
            arr[i] = actual;
            actual = actual.siguiente;
        }
        return arr;
    }

    // ── Rotación de arreglo para algoritmo Round Robin ───────────────────────
    /**
     * Rota una posición todos los elementos EXCEPTO el primero (arr[0]).
     * arr[0] es el equipo "fijo" — todos los demás rotan a su alrededor.
     *
     * Antes: [A, B, C, D]  →  Después: [A, D, B, C]
     *         fijo  rotan              fijo  rotan
     *
     * REUTILIZABLE: Este algoritmo de rotación con índice fijo es el estándar
     * para generar fixtures de torneos todos-contra-todos (Round Robin).
     * Con N equipos genera N-1 jornadas donde cada equipo juega una vez por jornada.
     */
    private void rotarUnaPosicion(Equipo[] arr) {
        Equipo ultimo = arr[tamanio - 1]; // Guardar el último antes de sobreescribir
        // Desplazar hacia la derecha desde la posición 2 hasta el final
        for (int i = tamanio - 1; i > 1; i--) {
            arr[i] = arr[i - 1]; // Cada elemento ocupa el lugar del anterior
        }
        arr[1] = ultimo; // El último pasa a ser el segundo (posición 1)
    }

    // ── Generar fixture completo ──────────────────────────────────────────────
    /**
     * Genera todas las jornadas del torneo Round Robin.
     * Con N equipos → N-1 jornadas, N/2 partidos por jornada.
     *
     * Lógica de emparejamiento:
     *   - arr[0]       juega contra arr[N-1]
     *   - arr[1]       juega contra arr[N-2]
     *   - arr[i]       juega contra arr[N-1-i]
     * Después de cada jornada se rota el arreglo (excepto arr[0]).
     */
    public void generarFixture() {
        if (tamanio % 2 != 0) {
            System.out.println("Se necesita un numero par de equipos.");
            return;
        }

        Equipo[] arr   = obtenerArreglo();
        int      jornadas = tamanio - 1; // N equipos → N-1 jornadas

        for (int j = 1; j <= jornadas; j++) {
            System.out.println("\nJornada " + j + ":");
            // Emparejar: el primero con el último, el segundo con el penúltimo...
            for (int i = 0; i < tamanio / 2; i++) {
                Equipo local     = arr[i];
                Equipo visitante = arr[tamanio - 1 - i];
                System.out.println("  " + local.nombre + " vs " + visitante.nombre);
            }
            rotarUnaPosicion(arr); // Preparar emparejamientos para la siguiente jornada
        }
    }

    // ── Tabla de posiciones (Ordenamiento burbuja) ───────────────────────────
    /**
     * Ordena los equipos por puntos (desc) y por goles a favor como desempate (desc).
     * REUTILIZABLE: Patrón de ordenamiento burbuja con dos criterios:
     *   Criterio 1 (principal): puntos mayor primero
     *   Criterio 2 (desempate): golesFavor mayor primero si puntos son iguales
     *
     * Para agregar un tercer criterio de desempate, extiende la condición del if:
     *   || (puntos iguales && goles iguales && otroDesempate)
     */
    public void tablaPosiciones() {
        Equipo[] arr = obtenerArreglo(); // Trabajar sobre arreglo, no sobre la lista

        // Ordenamiento burbuja con doble criterio
        for (int i = 0; i < tamanio - 1; i++) {
            for (int j = i + 1; j < tamanio; j++) {
                boolean mayorPuntos = arr[j].puntos > arr[i].puntos;
                boolean igualPuntosYMasGoles = arr[j].puntos == arr[i].puntos
                                            && arr[j].golesFavor > arr[i].golesFavor;
                if (mayorPuntos || igualPuntosYMasGoles) {
                    // Intercambiar posiciones
                    Equipo temp = arr[i];
                    arr[i]      = arr[j];
                    arr[j]      = temp;
                }
            }
        }

        // Imprimir tabla formateada
        System.out.println("\nTABLA DE POSICIONES");
        System.out.println("Pos | Equipo          | Ciudad          | Pts | Goles");
        System.out.println("----+-----------------+-----------------+-----+------");
        for (int i = 0; i < tamanio; i++) {
            System.out.printf("%-4d| %-16s| %-16s| %-4d| %d%n",
                i + 1,
                arr[i].nombre,
                arr[i].ciudad,
                arr[i].puntos,
                arr[i].golesFavor);
        }
    }
}