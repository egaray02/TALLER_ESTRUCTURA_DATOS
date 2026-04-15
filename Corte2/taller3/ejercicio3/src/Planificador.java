import java.util.ArrayList;

public class Planificador {

    private Proceso cabeza;
    private Proceso cola;
    private int tamaño;
    private ArrayList<String> ordenFinalizacion;

    public Planificador() {
        cabeza = null;
        cola   = null;
        tamaño = 0;
        ordenFinalizacion = new ArrayList<>();
    }

    public void agregar(Proceso nuevo) {
        if (cabeza == null) {
            cabeza = nuevo;
            cola  = nuevo;
            cola.siguiente = cabeza;
        } else {
            cola.siguiente = nuevo;
            cola = nuevo;
            cola.siguiente = cabeza;
        }
        tamaño++;
    }

    private void eliminar(Proceso anterior, Proceso aEliminar) {
        if (tamaño == 1) {
            cabeza = null;
            cola   = null;
        } else if (aEliminar == cabeza) {
            cabeza = cabeza.siguiente;
            cola.siguiente = cabeza;
        } else if (aEliminar == cola) {
            cola  = anterior;
            cola.siguiente = cabeza;
        } else {
            anterior.siguiente = aEliminar.siguiente;
        }
        tamaño--;
    }

    public void ejecutar(int cantidad) {
        if (cabeza == null) {
            System.out.println("No hay procesos en la cola.");
            return;
        }

        ordenFinalizacion.clear();

        System.out.println("\nIniciando Round Robin  quantum=" + cantidad);
        System.out.println("─".repeat(45));

        int turno = 1;
        Proceso anterior = cola;
        Proceso actual   = cabeza;

        while (cabeza != null) {
            System.out.printf("Turno %2d | %s | restante antes: %ds%n",
                    turno++, actual, actual.getTiempoRestante());

            actual.descontarTiempo(cantidad);

            if (actual.getTiempoRestante() <= 0) {
                System.out.printf("         | TERMINO %s%n", actual.getNombre());
                ordenFinalizacion.add(actual.getNombre());

                Proceso siguiente = actual.siguiente;
                eliminar(anterior, actual);
                actual = (cabeza == null) ? null : siguiente;
            } else {
                System.out.printf("         | restante ahora: %ds%n",
                        actual.getTiempoRestante());
                anterior = actual;
                actual   = actual.siguiente;
            }

            System.out.println("─".repeat(45));
        }

        System.out.println("\nOrden de finalizacion:");
        for (int i = 0; i < ordenFinalizacion.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + ordenFinalizacion.get(i));
        }
    }

    public void listar() {
        if (cabeza == null) {
            System.out.println("  (sin procesos)");
            return;
        }
        Proceso actual = cabeza;
        int i = 1;
        do {
            System.out.printf("  %d. %s  tiempo=%ds%n",
                    i++, actual, actual.getTiempoRestante());
            actual = actual.siguiente;
        } while (actual != cabeza);
    }

    public int getTamaño() { return tamaño; }
}
