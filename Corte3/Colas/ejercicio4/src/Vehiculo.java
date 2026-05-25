public class Vehiculo {
    String placa;
    String tipoVehiculo;
    double tarifa;
    boolean esExento;
    Vehiculo siguiente;

    public Vehiculo(String placa, String tipoVehiculo, double tarifa, boolean esExento) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.tarifa = tarifa;
        this.esExento = esExento;
        this.siguiente = null;
    }
}