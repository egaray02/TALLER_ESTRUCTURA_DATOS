class NodoResiduo {
    String nombre;
    NodoResiduo izquierdo;
    NodoResiduo derecho;

    public NodoResiduo(String nombre) {
        this.nombre = nombre;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class ArbolResiduos {
    public static void main(String[] args) {

        NodoResiduo raiz = new NodoResiduo("Residuos");

        raiz.izquierdo = new NodoResiduo("Orgánicos");
        raiz.derecho = new NodoResiduo("Inorgánicos");

        raiz.izquierdo.izquierdo = new NodoResiduo("Comida");
        raiz.izquierdo.derecho = new NodoResiduo("Jardín");

        raiz.derecho.izquierdo = new NodoResiduo("Plástico");
        raiz.derecho.derecho = new NodoResiduo("Papel");

        imprimirConNivel(raiz, 0);
    }

    public static void imprimirConNivel(NodoResiduo nodo, int nivel) {
        if (nodo == null) return;

        System.out.println("Nivel " + nivel + ": " + nodo.nombre);
        imprimirConNivel(nodo.izquierdo, nivel + 1);
        imprimirConNivel(nodo.derecho, nivel + 1);
    }
}
