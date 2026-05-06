public class Cancion {
    String titulo;
    String artista;
    Cancion siguiente;
    Cancion anterior;
 
    public Cancion(String titulo, String artista) {
        this.titulo = titulo;
        this.artista = artista;
        this.siguiente = null;
        this.anterior = null;
    }
 
    @Override
    public String toString() {
        return "\"" + titulo + "\" - " + artista;
    }
}
