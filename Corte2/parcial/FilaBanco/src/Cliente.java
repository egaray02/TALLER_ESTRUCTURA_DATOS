public class Cliente {
    String nombre;
    int turno;
    Cliente siguiente;
    public Cliente(String nombre, int turno) {
        this.nombre = nombre;
        this.turno = turno;
        this.siguiente = null;
    }
    
    
}
