public class Equipo {
    String nombre;
    String ciudad;
    int puntos;
    int golesFavor;
    Equipo siguiente;

    public Equipo(String nombre, String ciudad, int puntos, int golesFavor) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.puntos = puntos;
        this.golesFavor = golesFavor;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return "Equipo: " + nombre +
               " | Ciudad: " + ciudad +
               " | Puntos: " + puntos +
               " | Goles: " + golesFavor;
    }
}
