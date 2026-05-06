public class Anuncio {

    private String  titulo;
    private int     duracionSegundos;
    private int     vecesRepetido;
    private String  categoria;
    public  Anuncio siguiente;

    public Anuncio(String titulo, int duracion, String categoria) {
        this.titulo           = titulo;
        this.duracionSegundos = duracion;
        this.categoria        = categoria;
        this.vecesRepetido    = 0;
        this.siguiente        = null;
    }

    public String  getTitulo()           { return titulo; }
    public int     getDuracionSegundos() { return duracionSegundos; }
    public int     getVecesRepetido()    { return vecesRepetido; }
    public String  getCategoria()        { return categoria; }

    public void incrementarRepeticion() { vecesRepetido++; }

    @Override
    public String toString() {
        return "[" + categoria + "] " + titulo
             + " (" + duracionSegundos + "s)";
    }
}
