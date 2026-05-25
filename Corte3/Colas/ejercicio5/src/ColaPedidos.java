public class ColaPedidos {
    private Pedido entrada;
    private Pedido salida;
    private int tamaño;

    public ColaPedidos() {
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

    public Pedido peek() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        return salida;
    }

    public void enqueue(Pedido pedido) {
        if (isEmpty()) {
            salida = pedido;
            entrada = pedido;
        } else {
            entrada.siguiente = pedido;
            entrada = pedido;
        }
        tamaño++;
    }

    public Pedido dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        Pedido pedido = salida;
        salida = salida.siguiente;
        if (salida == null) {
            entrada = null;
        }
        tamaño--;
        return pedido;
    }

    public void imprimir() {
        if (isEmpty()) {
            System.out.println("[ Cola vacía ]");
            return;
        }
        System.out.print("Salida < ");
        Pedido actual = salida;
        while (actual != null) {
            System.out.print("[" + actual.numeroPedido + " - " + actual.cliente + " - $" + actual.totalPagar + " - " + (actual.cancelado ? "Cancelado" : "Vigente") + "]");
            if (actual.siguiente != null) System.out.print(" < ");
            actual = actual.siguiente;
        }
        System.out.println(" < Entrada");
    }

    public void procesarPedidos() {
        if (isEmpty()) {
            System.out.println("No hay pedidos en la cola.");
            return;
        }
        int despachados = 0;
        int cancelados = 0;
        System.out.println("\n--- PROCESAMIENTO DE PEDIDOS ---");
        while (!isEmpty()) {
            Pedido p = dequeue();
            if (p.cancelado) {
                cancelados++;
                System.out.println("Cancelado | Pedido: " + p.numeroPedido + " | Cliente: " + p.cliente);
            } else {
                despachados++;
                System.out.println("Despachado | Pedido: " + p.numeroPedido + " | Cliente: " + p.cliente + " | Total: $" + p.totalPagar);
            }
        }
        System.out.println("\nTotal despachados : " + despachados);
        System.out.println("Total cancelados  : " + cancelados);
    }
}