//La Clase Lectura (Nodo): Debe contener idSensor (int), temperatura (double), presion (double) y hora (String).
public class Lectura {
    int idSensor;
    double temperatura;
    double presion;
    String hora;
    Lectura siguiente;

    public Lectura(int idSensor, double temperatura, double presion, String hora) {
        this.idSensor = idSensor;
        this.temperatura = temperatura;
        this.presion = presion;
        this.hora = hora;
        this.siguiente = null;
    }
    
}
