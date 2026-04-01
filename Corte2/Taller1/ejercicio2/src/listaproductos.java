/*El Problema: Si llega un producto que vence en menos de 3 días, 
debe insertarse al inicio de la lista (prioridad de venta).
 Si vence en más tiempo, se pone al final.*/
public class listaproductos {
    producto cabeza;
    public listaproductos() {
        this.cabeza = null;
    }

    public void agregar(producto nuevo) {
        if (nuevo.diasParaVencer < 3) {
            insertarAlInicio(nuevo);
        } else {
            insertarAlFinal(nuevo);
        }
    }
 
    private void insertarAlInicio(producto nuevo) {
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        System.out.println(nuevo.nombre + "\" insertado al INICIO (vence en "
                + nuevo.diasParaVencer + " días");
    }
 
    private void insertarAlFinal(producto nuevo) {
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            producto actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        System.out.println(nuevo.nombre + "\" insertado al FINAL   (vence en "
                + nuevo.diasParaVencer + " días");
    }
    public void imprimirEstante() {
        System.out.println("\n==============================================");
        System.out.println("   Estante de lácteos (orden de venta)");
        System.out.println("==============================================");
        if (cabeza == null) {
            System.out.println("  (estante vacío)");
            return;
        }
        producto actual = cabeza;
        int pos = 1;
        while (actual != null) {
            System.out.println("[" + pos + "] " + actual.nombre
                    + " | cantidad: " + actual.cantidad
                    + " | vence en: " + actual.diasParaVencer + " día(s)");
            actual = actual.siguiente;
            pos++;
        }
    }
    public void imprimirProximosAVencer() {
        System.out.println("\n==============================================");
        System.out.println("   Productos con menos de 5 días para vencer");
        System.out.println("==============================================");
        producto actual = cabeza;
        boolean hayProductos = false;
        while (actual != null) {
            if (actual.diasParaVencer < 5) {
                System.out.println("→ " + actual.nombre
                        + " | cantidad: " + actual.cantidad
                        + " | vence en: " + actual.diasParaVencer + " día(s)");
                hayProductos = true;
            }
            actual = actual.siguiente;
        }
        if (!hayProductos) {
            System.out.println("  (ningún producto próximo a vencer)");
        }
    }
 
}
