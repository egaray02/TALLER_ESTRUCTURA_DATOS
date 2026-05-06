
public class Playlist {
    private Cancion cabeza;  
    private Cancion actual;   
    private int tamanio;
 
    public Playlist() {
        this.cabeza = null;
        this.actual = null;
        this.tamanio = 0;
    }
 
    
    public void agregarCancion(String titulo, String artista) {
        Cancion nueva = new Cancion(titulo, artista);
 
        if (cabeza == null) {
           
            cabeza = nueva;
            cabeza.siguiente = cabeza;
            cabeza.anterior  = cabeza;
            actual = cabeza;
        } else {
            
            Cancion cola = cabeza.anterior;
 
            cola.siguiente  = nueva;
            nueva.anterior  = cola;
            nueva.siguiente = cabeza;
            cabeza.anterior = nueva;
        }
 
        tamanio++;
        System.out.println("✔ Canción agregada: " + nueva);
    }
 
   
    public void siguienteCancion() {
        if (actual == null) {
            System.out.println("⚠ La playlist está vacía.");
            return;
        }
        actual = actual.siguiente;
        System.out.println("▶ Reproduciendo: " + actual);
        indicarCiclo();
    }
 
    
    public void cancionAnterior() {
        if (actual == null) {
            System.out.println("⚠ La playlist está vacía.");
            return;
        }
        actual = actual.anterior;
        System.out.println("◀ Reproduciendo: " + actual);
        indicarCiclo();
    }
 
   
    public void verPlaylist() {
        if (cabeza == null) {
            System.out.println("⚠ La playlist está vacía.");
            return;
        }
 
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║         LISTA CIRCULAR DOBLE         ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  primer nodo de la lista");
        System.out.println();
 
        Cancion temp = cabeza;
        int indice = 1;
        do {
            String marcador = (temp == actual) ? " ACTUAL" : "";
            String esCabeza = (temp == cabeza)  ? " cabeza" : "         ";
 
            System.out.printf("  %s [%d] %s%s%n", esCabeza, indice, temp, marcador);
 
            
            System.out.printf("         ant=%-25s sig=%s%n",
                    temp.anterior.titulo, temp.siguiente.titulo);
 
            temp = temp.siguiente;
            indice++;
        } while (temp != cabeza);
 
       
        System.out.println();
        System.out.println("  ↺  El último nodo apunta de vuelta a [cabeza]: "
                + cabeza.anterior.titulo + " → " + cabeza.titulo);
        System.out.println("  ↻  [cabeza] anterior apunta al último nodo: "
                + cabeza.titulo + " ← " + cabeza.anterior.titulo);
        System.out.println("══════════════════════════════════════════");
        System.out.println("  Total de canciones: " + tamanio);
        System.out.println("══════════════════════════════════════════\n");
    }
 
   
    public void mostrarActual() {
        if (actual == null) {
            System.out.println("⚠ La playlist está vacía.");
        } else {
            System.out.println("♪ Actual: " + actual);
        }
    }
 
   
    private void indicarCiclo() {
       
        System.out.println("  (lista circular: " + tamanio + " canciones en ciclo)");
    }
 
    public boolean estaVacia() {
        return cabeza == null;
    }
}