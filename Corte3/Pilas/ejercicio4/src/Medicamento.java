public class Medicamento {
    String nombre;
    String lote;
    int diasParaVencer;
    Medicamento siguiente;

    public Medicamento(String nombre, String lote, int diasParaVencer) {
        this.nombre = nombre;
        this.lote = lote;
        this.diasParaVencer = diasParaVencer;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return "[" + nombre + " | Lote: " + lote + " | Vence en: " + diasParaVencer + " días]";
    }
}