//La Clase Cancion (Nodo): Debe contener titulo (String), artista (String), duracionSegundos (int) y genero (String).
public class Cancion {
    String titulo;
    String artista;
    int duracionSegundos;
    String genero;
    Cancion siguiente;

    public Cancion(String titulo, String artista, int duracionSegundos, String genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSegundos = duracionSegundos;
        this.genero = genero;
        this.siguiente = null;
    }
}
