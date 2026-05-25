public class Impresion {
    private Documento entrada;
    private Documento salida;
    private int tamaño;

    public Impresion() {
        this.entrada = null;
        this.salida = null;
        this.tamaño = 0;
    }

    public boolean isEmpty() {
        return salida == null;
    }

    public int size() {
        return tamaño;
    }

    public Documento peek() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        return salida;
    }

    public void enqueue(Documento documento) {
        if (isEmpty()) {
            salida = documento;
            entrada = documento;
        } else {
            entrada.siguiente = documento;
            entrada = documento;
        }
        tamaño++;
    }

    public Documento dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        Documento documento = salida;
        salida = salida.siguiente;
        if (salida == null) {
            entrada = null;
        }
        tamaño--;
        return documento;
    }

    public void imprimir() {
        if (isEmpty()) {
            System.out.println("[ Cola vacía ]");
            return;
        }
        System.out.print("Salida < ");
        Documento actual = salida;
        while (actual != null) {
            System.out.print("[" + actual.nombreArchivo + " - " + actual.usuario + " - " + actual.numeroPaginas + " pags - " + (actual.esColor ? "Color" : "B/N") + "]");
            if (actual.siguiente != null) System.out.print(" < ");
            actual = actual.siguiente;
        }
        System.out.println(" < Entrada");
    }

    public int calcularPaginasTotales() {
        int total = 0;
        Documento actual = salida;
        while (actual != null) {
            total += actual.numeroPaginas;
            actual = actual.siguiente;
        }
        return total;
    }
}