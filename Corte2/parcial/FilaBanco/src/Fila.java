public class Fila {

    Cliente cabeza;
     public Fila() {
        this.cabeza = null;
    }

    public void agregarFinal(String nombre, int turno) {
        Cliente nuevo = new Cliente(nombre,turno);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Cliente actual = cabeza;
            while (actual.siguiente != null) actual = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }
    public void eliminarCliente(int turno) {
        if (cabeza == null) return;

        // Caso B: es la cabeza
        if (cabeza.turno==(turno)) {
            cabeza = cabeza.siguiente;
            return;
        }
        
    }
    
    public void mostrar() {
        if (cabeza == null) { System.out.println("No hay productos."); return; }
        Cliente actual = cabeza;
        while (actual != null) {
            System.out.println("Código: "   + actual.turno
                    + ", Nombre: "          + actual.nombre);
            actual = actual.siguiente;
        }
    }


}
