public class PilaEnsamblaje {
    private Pieza tope;
    private int tamaño;

    public PilaEnsamblaje() {
        this.tope = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void push(Pieza nueva) {
        nueva.siguiente = tope;
        tope = nueva;
        tamaño++;
    }

    public Pieza pop() {
        if (estaVacia()) {
            return null;
        }
        Pieza aux = tope;
        tope = tope.siguiente;
        tamaño--;
        aux.siguiente = null;
        return aux;
    }

    public Pieza peek() {
        return tope;
    }

    public void imprimir() {
        if (estaVacia()) {
            System.out.println("Pila vacía.");
            return;
        }
        Pieza actual = tope;
        System.out.println("=== ESTADO DE LA LÍNEA DE ENSAMBLAJE (De Tope a Base) ===");
        while (actual != null) {
            if (actual == tope) {
                System.out.print("  TOPE  ");
            } else {
                System.out.print("          ");
            }
            System.out.println(actual.toString());
            actual = actual.siguiente;
        }
        System.out.println("==========================================================");
    }

    public void limpiarHastaDefecto() {
        if (estaVacia()) {
            System.out.println("La pila está vacía. No hay piezas que revisar.");
            return;
        }

        boolean encontrado = false;
        System.out.println("\n>>> SENSOR ACTIVADO: Iniciando limpieza hasta pieza defectuosa...");

        while (!estaVacia()) {
            Pieza actual = pop();
            if (!actual.esDefectuosa) {
                System.out.println("  Pieza buena descartada: " + actual.nombrePieza + " [" + actual.numeroSerie + "]");
            } else {
                System.out.println("  ¡PIEZA DEFECTUOSA ENCONTRADA!: " + actual.nombrePieza + " [" + actual.numeroSerie + "] -> LÍNEA DETENIDA.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("  No se encontró ninguna pieza defectuosa en la pila.");
        }
    }
}