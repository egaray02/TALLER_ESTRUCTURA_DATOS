public class cancion {

    String titulo;
    String artista;
    int    duracion;

    cancion anterior;
    cancion siguiente;

    public cancion(String titulo, String artista, int duracion) {
        this.titulo    = titulo;
        this.artista   = artista;
        this.duracion  = duracion;
        this.anterior  = null;
        this.siguiente = null;
    }

    public String duracionFormato() {
        int minutos  = duracion / 60;
        int segundos = duracion % 60;
        if (segundos < 10) {
            return minutos + ":0" + segundos;
        }
        return minutos + ":" + segundos;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + " | Artista: " + artista +
               " | Duracion: " + duracionFormato();
    }
}