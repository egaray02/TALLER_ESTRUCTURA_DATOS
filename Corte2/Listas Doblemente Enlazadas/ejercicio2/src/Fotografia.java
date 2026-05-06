public class Fotografia {

    String nombreArchivo;
    double tamanoMB;
    String resolucion;

    
    Fotografia anterior;
    Fotografia siguiente;

    public Fotografia(String nombreArchivo, double tamanoMB, String resolucion) {
        this.nombreArchivo = nombreArchivo;
        this.tamanoMB      = tamanoMB;
        this.resolucion    = resolucion;
        this.anterior      = null;
        this.siguiente     = null;
    }

    @Override
    public String toString() {
        return String.format("Archivo: %-25s | Tamaño: %.1f MB | Resolución: %s",
                nombreArchivo, tamanoMB, resolucion);
    }
}