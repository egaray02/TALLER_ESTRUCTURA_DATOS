public class Grupo {
    String nombreReserva;
    int numeroDPersonas;
    boolean esVip;
    int minutosEsperando;
    Grupo siguiente;

    public Grupo(String nombreReserva, int numeroDPersonas, boolean esVip, int minutosEsperando) {
        this.nombreReserva = nombreReserva;
        this.numeroDPersonas = numeroDPersonas;
        this.esVip = esVip;
        this.minutosEsperando = minutosEsperando;
        this.siguiente = null;
    }

    @Override
    public String toString() {
        return "Reserva: " + nombreReserva +
               " | Personas: " + numeroDPersonas +
               " | VIP: " + (esVip ? "Si" : "No") +
               " | Esperando: " + minutosEsperando + " min";
    }
}