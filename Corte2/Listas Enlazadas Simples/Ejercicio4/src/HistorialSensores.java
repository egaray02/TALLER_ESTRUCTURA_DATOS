/**
 * Lista SIMPLEMENTE enlazada NO circular — Historial de lecturas de sensores.
 * ESTRUCTURA: Lista simple, igual que los anteriores.
 * APORTE NUEVO: agregarLectura() inserta SIEMPRE al inicio — pila (LIFO).
 * El más reciente queda primero, el más antiguo al final.
 *
 * Cola  (FIFO) → insertar al final,  sacar de cabeza
 * Pila  (LIFO) → insertar al inicio, sacar de cabeza   ← este ejercicio
 */
public class HistorialSensores {

    private Lectura cabeza;

    // ── Inserción al inicio siempre (comportamiento de pila/stack) ───────────
    /**
     * REUTILIZABLE: Cuando SIEMPRE insertas al inicio, la lista actúa como pila:
     * el último en entrar es el primero en aparecer al recorrer.
     * Útil para historiales, logs, registros donde quieres ver lo más reciente primero.
     *
     * Mismo mecanismo que insertarAlInicio() — ahora es el único modo de inserción.
     */
    public void agregarLectura(Lectura nueva) {
        nueva.siguiente = cabeza; // La nueva lectura apunta a la anterior cabeza
        cabeza          = nueva;  // La nueva lectura pasa a ser la cabeza
        System.out.println("Lectura registrada: Sensor " + nueva.idSensor
                + " | " + nueva.hora
                + " | Temp: " + nueva.temperatura + "°C"
                + " | Presión: " + nueva.presion + " bar");
    }

    // ── Búsqueda del máximo — idéntico a pacienteMayorEdad() ────────────────
    /**
     * REUTILIZABLE: Mismo patrón de siempre:
     *   maxima = cabeza  (candidato inicial)
     *   recorrer con while, actualizar maxima si encuentras uno mayor
     * Aquí sobre temperatura en lugar de edad — solo cambia el campo.
     */
    public void reportarTemperaturaMaxima() {
        if (cabeza == null) { System.out.println("No hay lecturas registradas."); return; }
        Lectura actual = cabeza;
        Lectura maxima = cabeza;
        while (actual != null) {
            if (actual.temperatura > maxima.temperatura) maxima = actual;
            actual = actual.siguiente;
        }
        System.out.println("\n  Sensor: "     + maxima.idSensor
                         + "\n  Hora: "       + maxima.hora
                         + "\n  Temperatura: "+ maxima.temperatura + " °C"
                         + "\n  Presión: "    + maxima.presion + " bar");
    }

    // Listar — idéntico a todos los anteriores
    public void imprimirHistorial() {
        System.out.println("\n==============================================");
        System.out.println("   Historial de lecturas (más reciente primero)");
        System.out.println("==============================================");
        if (cabeza == null) { System.out.println("  (sin registros)"); return; }
        Lectura actual = cabeza;
        int     pos    = 1;
        while (actual != null) {
            System.out.println("[" + pos + "] Sensor " + actual.idSensor
                    + " | " + actual.hora
                    + " | Temp: " + actual.temperatura + " °C"
                    + " | Presión: " + actual.presion + " bar");
            actual = actual.siguiente;
            pos++;
        }
    }
}