import java.util.LinkedList;
import java.util.Queue;

class NodoEmpleado {
    String cargo;
    NodoEmpleado izquierdo;
    NodoEmpleado derecho;

    public NodoEmpleado(String cargo) {
        this.cargo = cargo;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class    ejercicio13 {
    public static void main(String[] args) {

        NodoEmpleado gerente = new NodoEmpleado("Gerente");

        gerente.izquierdo = new NodoEmpleado("Supervisor1");
        gerente.derecho = new NodoEmpleado("Supervisor2");

        gerente.izquierdo.izquierdo = new NodoEmpleado("Analista1");
        gerente.izquierdo.derecho = new NodoEmpleado("Analista2");

        imprimirPorNiveles(gerente);
    }

    public static void imprimirPorNiveles(NodoEmpleado raiz) {
        if (raiz == null) return;

        Queue<NodoEmpleado> cola = new LinkedList<>();
        cola.add(raiz);

        int nivel = 0;

        while (!cola.isEmpty()) {
            int cantidad = cola.size();
            System.out.print("Nivel " + nivel + ": ");

            for (int i = 0; i < cantidad; i++) {
                NodoEmpleado actual = cola.poll();
                System.out.print(actual.cargo + " ");

                if (actual.izquierdo != null) cola.add(actual.izquierdo);
                if (actual.derecho != null) cola.add(actual.derecho);
            }

            System.out.println();
            nivel++;
        }
    }
}