public class PilaRuta {
    private Estacion tope;
    private int tamaño;

    public PilaRuta() {
        this.tope = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void push(Estacion nueva) {
        nueva.siguiente = tope;
        tope = nueva;
        tamaño++;
    }

    public Estacion pop() {
        if (estaVacia()) {
            return null;
        }
        Estacion aux = tope;
        tope = tope.siguiente;
        tamaño--;
        aux.siguiente = null;
        return aux;
    }

    public Estacion peek() {
        return tope;
    }

    public void imprimir() {
        if (estaVacia()) {
            System.out.println("No hay estaciones registradas.");
            return;
        }
        Estacion actual = tope;
        System.out.println("=== RUTA ACTUAL (Más Profunda : Entrada) ===");
        while (actual != null) {
            if (actual == tope) {
                System.out.print("  TOPE  ");
            } else {
                System.out.print("          ");
            }
            System.out.println(actual.toString());
            actual = actual.siguiente;
        }
        System.out.println("=============================================");
    }

    public void retrocederASuperficie() {
        if (estaVacia()) {
            System.out.println("No hay estaciones en la ruta. El equipo ya está en la superficie.");
            return;
        }

        System.out.println("\n>>> INICIANDO RETROCESO A LA SUPERFICIE...");
        int paso = 1;

        while (!estaVacia()) {
            Estacion actual = pop();
            System.out.println("\n  Paso " + paso + " - Estación: " + actual.nombrePunto);
            System.out.println("           Profundidad: " + actual.profundidad + "m | O2: " + actual.nivelOxigeno + "%");
            if (actual.nivelOxigeno < 18.0) {
                System.out.println("  Uso de Tanque de Emergencia Requerido en este punto.");
            }
            paso++;
        }

        System.out.println("\n  ✓ Equipo en superficie. Ruta completada.");
    }
}