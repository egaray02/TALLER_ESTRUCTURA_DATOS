public class Documento {
    String nombreArchivo;
    String usuario;
    int numeroPaginas;
    boolean esColor;
    Documento siguiente;

    public Documento(String nombreArchivo, String usuario, int numeroPaginas, boolean esColor) {
        this.nombreArchivo = nombreArchivo;
        this.usuario = usuario;
        this.numeroPaginas = numeroPaginas;
        this.esColor = esColor;
        this.siguiente = null;
    }
}