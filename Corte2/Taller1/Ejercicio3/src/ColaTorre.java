public class ColaTorre {

    private Vuelo cabeza;

    public void agregarVuelo(Vuelo nuevo) {
        if (nuevo.combustibleRestante < 10) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            System.out.println("ALERTA: Vuelo " + nuevo.numeroVuelo
                    + " con bajo combustible (" + nuevo.combustibleRestante
                    + "movido al INICIO de la cola.");
        } else {
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                Vuelo actual = cabeza;
                while (actual.siguiente != null) {
                    actual = actual.siguiente;
                }
                actual.siguiente = nuevo;
            }
            System.out.println(" Vuelo " + nuevo.numeroVuelo + " agregado al final de la cola.");
        }
    }

    public void reportarEmergencia(String numeroVuelo) {
        if (cabeza == null) {
            System.out.println("La cola está vacía.");
            return;
        }

        if (cabeza.numeroVuelo.equals(numeroVuelo)) {
            System.out.println("El vuelo " + numeroVuelo + " ya está al inicio de la cola.");
            return;
        }

        Vuelo anterior = cabeza;
        Vuelo actual = cabeza.siguiente;

        while (actual != null) {
            if (actual.numeroVuelo.equals(numeroVuelo)) {
            
                anterior.siguiente = actual.siguiente;
               
                actual.siguiente = cabeza;
                cabeza = actual;
                System.out.println("EMERGENCIA: Vuelo " + numeroVuelo
                        + " movido al INICIO de la cola.");
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }

        System.out.println("Vuelo " + numeroVuelo + " no encontrado en la cola.");
    }

    public void imprimirCola() {
        System.out.println("\n==============================================");
        System.out.println("   Cola de aterrizaje (orden de atención)");
        System.out.println("==============================================");
        if (cabeza == null) {
            System.out.println("  (cola vacía)");
            return;
        }
        Vuelo actual = cabeza;
        int pos = 1;
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