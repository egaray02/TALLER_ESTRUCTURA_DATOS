public class Lista {
    private Grupo cabeza;
    private Grupo cola;
    private int tamanio;

    public Lista() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregarGrupo(String nombre, int personas, boolean vip, int minutos) {
        Grupo nuevo = new Grupo(nombre, personas, vip, minutos);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = cabeza;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }
        tamanio++;
    }

    public void atenderSiguiente() {
        if (estaVacia()) {
            System.out.println("No hay grupos en espera.");
            return;
        }

        Grupo atendido = cabeza;
        System.out.println("\nAtendiendo:");
        System.out.println(atendido.toString());

        if (tamanio == 1) {
            if (atendido.esVip) {
                System.out.println(">> VIP reinsertado al final.");
            } else {
                cabeza = null;
                cola = null;
                tamanio = 0;
                System.out.println(">> Grupo eliminado. Lista vacia.");
            }
            mostrarLista();
            return;
        }

        cabeza = cabeza.siguiente;
        cola.siguiente = cabeza;

        if (atendido.esVip) {
            atendido.siguiente = cabeza;
            cola.siguiente = atendido;
            cola = atendido;
            System.out.println(">> VIP reinsertado al final.");
        } else {
            tamanio--;
            System.out.println(">> Grupo eliminado de la lista.");
        }

        mostrarLista();
    }

    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("Lista vacia.");
            return;
        }
        System.out.print("Estado lista: ");
        Grupo actual = cabeza;
        for (int i = 0; i < tamanio; i++) {
            System.out.print("[" + actual.nombreReserva + (actual.esVip ? "*" : "") + "]");
            if (i < tamanio - 1) System.out.print(" -> ");
            actual = actual.siguiente;
        }
        System.out.println(" -> (cabeza)");
    }
}
