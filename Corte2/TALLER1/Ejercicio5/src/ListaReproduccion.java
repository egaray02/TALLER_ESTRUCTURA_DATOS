public class ListaReproduccion {

    private Cancion cabeza;
    private Cancion actual;  

    public void agregarAlFinal(Cancion nueva) {
        if (cabeza == null) {
            cabeza = nueva;
            actual = cabeza;
        } else {
            Cancion temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nueva;
        }
        System.out.println( nueva.titulo + "\" agregada al final de la cola.");
    }

    
    public void agregarAContinuacion(Cancion nueva) {
        if (actual == null) {
           
            cabeza = nueva;
            actual = cabeza;
            System.out.println( nueva.titulo + "\" agregada (lista estaba vacía, ahora es la actual).");
        } else {
            nueva.siguiente = actual.siguiente;
            actual.siguiente = nueva;
            System.out.println( nueva.titulo + "\" insertada después de \"" + actual.titulo + "\".");
        }
    }

    
    public void siguiente() {
        if (actual == null || actual.siguiente == null) {
            System.out.println("  No hay más canciones en la lista.");
        } else {
            actual = actual.siguiente;
            System.out.println("Reproduciendo ahora:" + actual.titulo + " — " + actual.artista);
        }
    }

    public void mostrarDuracionTotal() {
        if (cabeza == null) {
            System.out.println("  La lista está vacía.");
            return;
        }

        int totalSegundos = 0;
        Cancion temp = cabeza;
        while (temp != null) {
            totalSegundos += temp.duracionSegundos;
            temp = temp.siguiente;
        }

        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;

        System.out.println("\n==============================================");
        System.out.println("   Duración total de la lista");
        System.out.println("==============================================");
        System.out.println("  Tiempo total : " + String.format("%02d", minutos) + ":" + String.format("%02d", segundos));
        System.out.println("  Canciones    : " + contarCanciones());
    }

    public void imprimirLista() {
        System.out.println("\n==============================================");
        System.out.println("   Lista de reproducción");
        System.out.println("==============================================");
        if (cabeza == null) {
            System.out.println("  (lista vacía)");
            return;
        }
        Cancion temp = cabeza;
        int pos = 1;
       while (temp != null) {
            String marcador = "  ";
            if (temp == actual) {
                marcador = "";
            }
            int min = temp.duracionSegundos / 60;
            int seg = temp.duracionSegundos % 60;
            String minStr = (min < 10) ? "0" + min : "" + min;
            String segStr = (seg < 10) ? "0" + seg : "" + seg;
            System.out.println(marcador + "[" + pos + "] " + temp.titulo + " - " + temp.artista + " | " + minStr + ":" + segStr + " | " + temp.genero);
            temp = temp.siguiente;
            pos++;
}
    }

    private int contarCanciones() {
        int count = 0;
        Cancion temp = cabeza;
        while (temp != null) {
            count++;
            temp = temp.siguiente;
        }
        return count;
    }
}
