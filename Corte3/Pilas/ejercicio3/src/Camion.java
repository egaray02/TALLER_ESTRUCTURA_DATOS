public class Camion {
    String placa;
    String conductor;
    double cargaToneladas;
    Camion siguiente;

    public Camion(String placa, String conductor, double cargaToneladas) {
        this.placa = placa;
        this.conductor = conductor;
        this.cargaToneladas = cargaToneladas;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return "[" + placa + " | " + conductor + " | " + cargaToneladas + "t]";
    }
}