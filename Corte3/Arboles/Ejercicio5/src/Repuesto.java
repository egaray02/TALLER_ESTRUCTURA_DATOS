public class Repuesto {
    int codigoRef;
    String descripcion;
    String marca;
    int stock;
    Repuesto izquierdo;
    Repuesto derecho;

    public Repuesto(int codigoRef, String descripcion, String marca, int stock) {
        this.codigoRef = codigoRef;
        this.descripcion = descripcion;
        this.marca = marca;
        this.stock = stock;
        this.izquierdo = null;
        this.derecho = null;
    }

    @Override
    public String toString() {
        return "[" + codigoRef + "] " + descripcion + " - " + marca + " (" + stock + " uds)";
    }
}