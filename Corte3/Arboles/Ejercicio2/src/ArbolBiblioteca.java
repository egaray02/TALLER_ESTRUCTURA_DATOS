public class ArbolBiblioteca {
    private Libro raiz;

    public ArbolBiblioteca() {
        this.raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    private Libro insertar(Libro nodo, long isbn, String titulo, String autor, int anioPublicacion) {
        if (nodo == null)
            return new Libro(isbn, titulo, autor, anioPublicacion);
        if (isbn < nodo.isbn)
            nodo.izquierdo = insertar(nodo.izquierdo, isbn, titulo, autor, anioPublicacion);
        else if (isbn > nodo.isbn)
            nodo.derecho = insertar(nodo.derecho, isbn, titulo, autor, anioPublicacion);
        else
            System.out.println("Ya existe un libro con ISBN " + isbn + " — no se insertó.");
        return nodo;
    }

    public void insertar(long isbn, String titulo, String autor, int anioPublicacion) {
        raiz = insertar(raiz, isbn, titulo, autor, anioPublicacion);
    }

    private boolean existeISBN(Libro nodo, long isbn) {
        if (nodo == null) return false;
        if (isbn == nodo.isbn) return true;
        if (isbn < nodo.isbn)
            return existeISBN(nodo.izquierdo, isbn);
        else
            return existeISBN(nodo.derecho, isbn);
    }

    public boolean existeISBN(long isbn) {
        return existeISBN(raiz, isbn);
    }

    private void imprimirCatalogo(Libro nodo) {
        if (nodo != null) {
            imprimirCatalogo(nodo.izquierdo);
            System.out.println("  " + nodo.isbn + " - " + nodo.titulo + " - " + nodo.autor);
            imprimirCatalogo(nodo.derecho);
        }
    }

    public void imprimirCatalogo() {
        if (isEmpty()) { System.out.println("  [ Catalogo vacio ]"); return; }
        System.out.println("  Catalogo ordenado por ISBN:");
        imprimirCatalogo(raiz);
    }

    private void inOrden(Libro nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierdo);
            System.out.println("  " + nodo);
            inOrden(nodo.derecho);
        }
    }

    public void inOrden() {
        if (isEmpty()) { System.out.println("  [ Arbol vacio ]"); return; }
        System.out.println("  InOrden (ISBN ascendente):");
        inOrden(raiz);
    }

    private void preOrden(Libro nodo) {
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

    private int altura(Libro nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
    }

    public int altura() {
        return altura(raiz);
    }

    private int contarHojas(Libro nodo) {
        if (nodo == null) return 0;
        if (nodo.izquierdo == null && nodo.derecho == null) return 1;
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }
}