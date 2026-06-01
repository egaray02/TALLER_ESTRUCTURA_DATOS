public class ArbolTorneo {
    private Jugador raiz;

    public ArbolTorneo() {
        this.raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    private Jugador insertar(Jugador nodo, int elo, String nombreUsuario, String pais, int partidasJugadas) {
        if (nodo == null)
            return new Jugador(elo, nombreUsuario, pais, partidasJugadas);
        if (elo < nodo.elo)
            nodo.izquierdo = insertar(nodo.izquierdo, elo, nombreUsuario, pais, partidasJugadas);
        else if (elo > nodo.elo)
            nodo.derecho = insertar(nodo.derecho, elo, nombreUsuario, pais, partidasJugadas);
        else
            System.out.println("Ya existe un jugador con ELO " + elo + " — no se insertó.");
        return nodo;
    }

    public void insertar(int elo, String nombreUsuario, String pais, int partidasJugadas) {
        raiz = insertar(raiz, elo, nombreUsuario, pais, partidasJugadas);
    }

    private Jugador buscar(Jugador nodo, int elo) {
        if (nodo == null) return null;
        if (elo == nodo.elo) return nodo;
        if (elo < nodo.elo)
            return buscar(nodo.izquierdo, elo);
        else
            return buscar(nodo.derecho, elo);
    }

    public Jugador buscar(int elo) {
        return buscar(raiz, elo);
    }

    private void listarEnRango(Jugador nodo, int eloMin, int eloMax) {
        if (nodo == null) return;
        if (nodo.elo < eloMin) {
            listarEnRango(nodo.derecho, eloMin, eloMax);
        } else if (nodo.elo > eloMax) {
            listarEnRango(nodo.izquierdo, eloMin, eloMax);
        } else {
            listarEnRango(nodo.izquierdo, eloMin, eloMax);
            System.out.println("  " + nodo);
            listarEnRango(nodo.derecho, eloMin, eloMax);
        }
    }

    public void listarEnRango(int eloMin, int eloMax) {
        if (isEmpty()) { System.out.println("  [ Arbol vacio ]"); return; }
        System.out.println("  Jugadores con ELO entre " + eloMin + " y " + eloMax + ":");
        listarEnRango(raiz, eloMin, eloMax);
    }

    private void inOrden(Jugador nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierdo);
            System.out.println("  " + nodo);
            inOrden(nodo.derecho);
        }
    }

    public void inOrden() {
        if (isEmpty()) { System.out.println("  [ Arbol vacio ]"); return; }
        System.out.println("  InOrden (ELO ascendente):");
        inOrden(raiz);
    }

    private void preOrden(Jugador nodo) {
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

    private int altura(Jugador nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    public int altura() {
        return altura(raiz);
    }

    private int contarHojas(Jugador nodo) {
        if (nodo == null) return 0;
        if (nodo.izquierdo == null && nodo.derecho == null) return 1;
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }
}