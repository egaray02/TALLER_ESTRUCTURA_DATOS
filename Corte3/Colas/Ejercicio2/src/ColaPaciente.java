public class ColaPaciente {
    private Paciente entrada;
    private Paciente salida;
    private int tamaño;

    public ColaPaciente() {
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

    public Paciente peek() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        return salida;
    }

    public void enqueue(Paciente paciente) {
        if (isEmpty()) {
            salida = paciente;
            entrada = paciente;
        } else {
            entrada.siguiente = paciente;
            entrada = paciente;
        }
        tamaño++;
    }

    public Paciente dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        Paciente paciente = salida;
        salida = salida.siguiente;
        if (salida == null) {
            entrada = null;
        }
        tamaño--;
        return paciente;
    }

    public void imprimir() {
        if (isEmpty()) {
            System.out.println("[ Cola vacía ]");
            return;
        }
        System.out.print("Salida < ");
        Paciente actual = salida;
        while (actual != null) {
            System.out.print("[" + actual.cedula + ": " + actual.nombreCompleto + " - " + actual.edad + " años - " + actual.sintomaPrincipal + "]");
            if (actual.siguiente != null) System.out.print(" < ");
            actual = actual.siguiente;
        }
        System.out.println(" < Entrada");
    }

    public void atenderTodos() {
        if (isEmpty()) {
            System.out.println("No hay pacientes en espera.");
            return;
        }
        int total = 0;
        System.out.println("\n--- INICIO DE ATENCIÓN ---");
        while (!isEmpty()) {
            Paciente p = dequeue();
            total++;
            System.out.println("\nPaciente #" + total + " atendido:");
            System.out.println("  Cedula          : " + p.cedula);
            System.out.println("  Nombre          : " + p.nombreCompleto);
            System.out.println("  Edad            : " + p.edad + " años");
            System.out.println("  Sintoma principal: " + p.sintomaPrincipal);
        }
        System.out.println("\n--- FIN DEL TURNO ---");
        System.out.println("Total de pacientes atendidos: " + total);
    }
}