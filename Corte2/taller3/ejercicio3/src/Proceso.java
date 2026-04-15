public class Proceso {

    private String  nombre;
    private int pid;
    private int tiempoRestante;
    private int prioridad;
    public  Proceso siguiente;

    public Proceso(String nombre, int pid, int tiempoRestante, int prioridad) {
        this.nombre = nombre;
        this.pid = pid;
        this.tiempoRestante = tiempoRestante;
        this.prioridad = prioridad;
        this.siguiente = null;
    }

    public String getNombre() { return nombre; }
    public int getPid()            { return pid; }
    public int getTiempoRestante() { return tiempoRestante; }
    public int getPrioridad()      { return prioridad; }

    public void descontarTiempo(int quantum) {
        tiempoRestante -= quantum;
    }

    @Override
    public String toString() {
        return "PID " + pid + " [" + nombre + "] prioridad=" + prioridad;
    }
}
