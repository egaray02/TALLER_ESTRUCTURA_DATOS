public class Galeria {

    private Fotografia cabeza;
    private Fotografia cola;
    private Fotografia actual;
    private int tamano;

    public Galeria() {
        cabeza = null;
        cola   = null;
        actual = null;
        tamano = 0;
    }

    
    public void agregarFoto(String nombre, double mb, String res) {
        Fotografia nueva = new Fotografia(nombre, mb, res);

        if (cabeza == null) {
            cabeza = nueva;
            cola   = nueva;
            actual = nueva;
        } else {
            nueva.anterior = cola;
            cola.siguiente = nueva;
            cola           = nueva;
        }
        tamano++;
        System.out.println("  Foto agregada: " + nueva.nombreArchivo);
    }

    
    public void siguienteFoto() {
        if (actual == null) {
            System.out.println("  La galeria esta vacia.");
            return;
        }
        if (actual.siguiente == null) {
            System.out.println("  Ya estas en la ULTIMA foto. No puedes avanzar mas.");
        } else {
            actual = actual.siguiente;
            System.out.println("  Siguiente foto:");
            System.out.println("  " + actual);
        }
    }

   
    public void fotoAnterior() {
        if (actual == null) {
            System.out.println("  La galeria esta vacia.");
            return;
        }
        if (actual.anterior == null) {
            System.out.println("  Ya estas en la PRIMERA foto. No puedes retroceder.");
        } else {
            actual = actual.anterior;
            System.out.println("  Foto anterior:");
            System.out.println("  " + actual);
        }
    }

    
    public void verFotoActual() {
        if (actual == null) {
            System.out.println("  La galeria esta vacia.");
        } else {
            int pos = posicionActual();
            System.out.println("  Foto " + pos + " de " + tamano + ":");
            System.out.println("  " + actual);
        }
    }

    
    public void reproducirGaleria() {
        if (cabeza == null) {
            System.out.println("  La galeria esta vacia.");
            return;
        }

        System.out.println("\n  ==== Reproduciendo hacia ADELANTE ====");
        Fotografia cursor = cabeza;
        int pos = 1;
        while (cursor != null) {
            System.out.println("  " + pos + " de " + tamano + " | " + cursor);
            cursor = cursor.siguiente;
            pos++;
        }

        System.out.println("\n  ==== Reproduciendo hacia ATRAS ====");
        cursor = cola;
        pos = tamano;
        while (cursor != null) {
            System.out.println("  " + pos + " de " + tamano + " | " + cursor);
            cursor = cursor.anterior;
            pos--;
        }

        System.out.println("\n  Reproduccion completa.");
    }

    
    private int posicionActual() {
        Fotografia cursor = cabeza;
        int pos = 1;
        while (cursor != null && cursor != actual) {
            cursor = cursor.siguiente;
            pos++;
        }
        return pos;
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public int getTamano() {
        return tamano;
    }
}