public class PilaCallejon {
    private Camion tope;
    private int tamaño;

    public PilaCallejon() {
        this.tope = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void push(Camion nuevo) {
        nuevo.siguiente = tope;
        tope = nuevo;
        tamaño++;
    }

    public Camion pop() {
        if (estaVacia()) {
            return null;
        }
        Camion aux = tope;
        tope = tope.siguiente;
        tamaño--;
        aux.siguiente = null;
        return aux;
    }

    public Camion peek() {
        return tope;
    }

    public void imprimir() {
        if (estaVacia()) {
            System.out.println("El callejón está vacío.");
            return;
        }
        Camion actual = tope;
        System.out.println("=== ESTADO DEL CALLEJÓN (Entrada -> Fondo) ===");
        while (actual != null) {
            if (actual == tope) {
                System.out.print("  ENTRADA -> ");
            } else {
                System.out.print("             ");
            }
            System.out.println(actual.toString());
            actual = actual.siguiente;
        }
        System.out.println("==============================================");
    }

    public double calcularCargaTotal() {
        double total = 0.0;
        Camion actual = tope;
        while (actual != null) {
            total += actual.cargaToneladas;
            actual = actual.siguiente;
        }
        return total;
    }
}