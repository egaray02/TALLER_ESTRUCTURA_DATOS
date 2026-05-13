public class Pieza {
    String nombrePieza;
    String numeroSerie;
    boolean esDefectuosa;
    Pieza siguiente;

    public Pieza(String nombrePieza, String numeroSerie, boolean esDefectuosa) {
        this.nombrePieza = nombrePieza;
        this.numeroSerie = numeroSerie;
        this.esDefectuosa = esDefectuosa;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return "[" + nombrePieza + " | Serie: " + numeroSerie + " | " + (esDefectuosa ? "DEFECTUOSA" : "OK") + "]";
    }
}