public class Estacion {
    String nombrePunto;
    int profundidad;
    double nivelOxigeno;
    Estacion siguiente;

    public Estacion(String nombrePunto, int profundidad, double nivelOxigeno) {
        this.nombrePunto = nombrePunto;
        this.profundidad = profundidad;
        this.nivelOxigeno = nivelOxigeno;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return "[" + nombrePunto + " | Profundidad: " + profundidad + "m | O2: " + nivelOxigeno + "%]";
    }
}