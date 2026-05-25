public class Peaje {
    private Vehiculo entrada;
    private Vehiculo salida;
    private int tamaño;

    public Peaje() {
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

    public Vehiculo peek() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        return salida;
    }

    public void enqueue(Vehiculo vehiculo) {
        if (isEmpty()) {
            salida = vehiculo;
            entrada = vehiculo;
        } else {
            entrada.siguiente = vehiculo;
            entrada = vehiculo;
        }
        tamaño++;
    }

    public Vehiculo dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        Vehiculo vehiculo = salida;
        salida = salida.siguiente;
        if (salida == null) {
            entrada = null;
        }
        tamaño--;
        return vehiculo;
    }

    public void imprimir() {
        if (isEmpty()) {
            System.out.println("[ Cola vacía ]");
            return;
        }
        System.out.print("Salida < ");
        Vehiculo actual = salida;
        while (actual != null) {
            System.out.print("[" + actual.placa + " - " + actual.tipoVehiculo + " - $" + actual.tarifa + " - " + (actual.esExento ? "Exento" : "Paga") + "]");
            if (actual.siguiente != null) System.out.print(" < ");
            actual = actual.siguiente;
        }
        System.out.println(" < Entrada");
    }

    public void cerrarTurno() {
        if (isEmpty()) {
            System.out.println("No hay vehículos en el carril.");
            return;
        }
        double totalRecaudado = 0;
        int procesados = 0;
        int exentos = 0;
        System.out.println("\n--- CIERRE DE TURNO ---");
        while (!isEmpty()) {
            Vehiculo v = dequeue();
            procesados++;
            if (!v.esExento) {
                totalRecaudado += v.tarifa;
                System.out.println("Cobrado  | Placa: " + v.placa + " | Tipo: " + v.tipoVehiculo + " | Tarifa: $" + v.tarifa);
            } else {
                exentos++;
                System.out.println("Exento   | Placa: " + v.placa + " | Tipo: " + v.tipoVehiculo);
            }
        }
        System.out.println("\nVehículos procesados : " + procesados);
        System.out.println("Vehículos exentos    : " + exentos);
        System.out.println("Total recaudado      : $" + totalRecaudado);
    }
}