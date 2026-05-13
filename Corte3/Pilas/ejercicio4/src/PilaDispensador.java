public class PilaDispensador {
    private Medicamento tope;
    private int tamaño;

    public PilaDispensador() {
        this.tope = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void push(Medicamento nuevo) {
        nuevo.siguiente = tope;
        tope = nuevo;
        tamaño++;
    }

    public Medicamento pop() {
        if (estaVacia()) {
            return null;
        }
        Medicamento aux = tope;
        tope = tope.siguiente;
        tamaño--;
        aux.siguiente = null;
        return aux;
    }

    public Medicamento peek() {
        return tope;
    }

    public void imprimir() {
        if (estaVacia()) {
            System.out.println("El dispensador está vacío.");
            return;
        }
        Medicamento actual = tope;
        System.out.println("=== ESTADO DEL DISPENSADOR (De Tope a Base) ===");
        while (actual != null) {
            if (actual == tope) {
                System.out.print("  TOPE  ");
            } else {
                System.out.print("          ");
            }
            System.out.println(actual.toString());
            actual = actual.siguiente;
        }
        System.out.println("===============================================");
    }


    public void validarDespacho() {
        if (estaVacia()) {
            System.out.println("El dispensador está vacío. No hay medicamentos para despachar.");
            return;
        }

        System.out.println("\n VALIDANDO DESPACHO...");
        boolean encontrado = false;

        while (!estaVacia()) {
            Medicamento actual = peek();
            if (actual.diasParaVencer < 10) {
                pop();
                System.out.println("  RETIRADO por vencimiento próximo: " + actual.nombre + " | Lote: " + actual.lote + " (" + actual.diasParaVencer + " días)");
            } else {
                System.out.println("  Medicamento SEGURO para despachar: " + actual.toString());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("  No quedan medicamentos aptos en el dispensador.");
        }
    }
}