public class ArbolNomina {
    private Empleado raiz;

    public ArbolNomina() {
        this.raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    private Empleado insertar(Empleado nodo, long cedula, String nombreCompleto, String cargo, double salario) {
        if (nodo == null)
            return new Empleado(cedula, nombreCompleto, cargo, salario);
        if (cedula < nodo.cedula)
            nodo.izquierdo = insertar(nodo.izquierdo, cedula, nombreCompleto, cargo, salario);
        else if (cedula > nodo.cedula)
            nodo.derecho = insertar(nodo.derecho, cedula, nombreCompleto, cargo, salario);
        else
            System.out.println("Ya existe un empleado con cedula " + cedula + " — no se insertó.");
        return nodo;
    }

    public void insertar(long cedula, String nombreCompleto, String cargo, double salario) {
        raiz = insertar(raiz, cedula, nombreCompleto, cargo, salario);
    }

    private Empleado buscar(Empleado nodo, long cedula) {
        if (nodo == null) return null;
        if (cedula == nodo.cedula) return nodo;
        if (cedula < nodo.cedula)
            return buscar(nodo.izquierdo, cedula);
        else
            return buscar(nodo.derecho, cedula);
    }

    public Empleado buscar(long cedula) {
        return buscar(raiz, cedula);
    }

    private Empleado buscarMinimo(Empleado nodo) {
        if (nodo.izquierdo == null) return nodo;
        return buscarMinimo(nodo.izquierdo);
    }

    public void buscarMinimo() {
        if (isEmpty()) { System.out.println("  [ Arbol vacio ]"); return; }
        Empleado min = buscarMinimo(raiz);
        System.out.println("  Empleado con cedula mas baja: " + min);
    }

    private Empleado buscarMaximo(Empleado nodo) {
        if (nodo.derecho == null) return nodo;
        return buscarMaximo(nodo.derecho);
    }

    public void buscarMaximo() {
        if (isEmpty()) { System.out.println("  [ Arbol vacio ]"); return; }
        Empleado max = buscarMaximo(raiz);
        System.out.println("  Empleado con cedula mas alta: " + max);
    }

    private int altura(Empleado nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    public int altura() {
        return altura(raiz);
    }

    private int contarHojas(Empleado nodo) {
        if (nodo == null) return 0;
        if (nodo.izquierdo == null && nodo.derecho == null) return 1;
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }

    public void reporteEficiencia() {
        System.out.println("  Altura del arbol:  " + altura());
        System.out.println("  Nodos hoja:        " + contarHojas());
        if (altura() <= 2 * (Math.log(contarHojas() + 1) / Math.log(2)))
            System.out.println("  Estado: estructura eficiente.");
        else
            System.out.println("  Estado: estructura desbalanceada, se recomienda reindexar.");
    }

    private void inOrden(Empleado nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierdo);
            System.out.println("  " + nodo);
            inOrden(nodo.derecho);
        }
    }

    public void inOrden() {
        if (isEmpty()) { System.out.println("  [ Arbol vacio ]"); return; }
        System.out.println("  InOrden (cedula ascendente):");
        inOrden(raiz);
    }

    private void preOrden(Empleado nodo) {
        if (nodo != null) {
            System.out.println("  " + nodo);
            preOrden(nodo.izquierdo);
            preOrden(nodo.derecho);
        }
    }

    public void preOrden() {
        if (isEmpty()) { System.out.println("  [ Arbol vacio ]"); return; }
        System.out.println("  PreOrden (raiz primero):");
        preOrden(raiz);
    }
}