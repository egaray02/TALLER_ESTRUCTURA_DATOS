public class Jugador {
    int elo;
    String nombreUsuario;
    String pais;
    int partidasJugadas;
    Jugador izquierdo;
    Jugador derecho;

    public Jugador(int elo, String nombreUsuario, String pais, int partidasJugadas) {
        this.elo = elo;
        this.nombreUsuario = nombreUsuario;
        this.pais = pais;
        this.partidasJugadas = partidasJugadas;
        this.izquierdo = null;
        this.derecho = null;
    }

    @Override
    public String toString() {
        return "[ELO: " + elo + " | " + nombreUsuario + " | " + pais + " | Partidas: " + partidasJugadas + "]";
    }
}