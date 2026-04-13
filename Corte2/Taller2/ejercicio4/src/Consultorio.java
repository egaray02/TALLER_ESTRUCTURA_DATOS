public class Consultorio {

    private Paciente cabeza;   
    private Paciente cola;    
    private int tamano;

    public Consultorio() {
        cabeza = null;
        cola   = null;
        tamano = 0;
    }

    
    public void agregarPaciente(String nombre, int edad, int urgencia) {
        Paciente nuevo = new Paciente(nombre, edad, urgencia);

        if (cabeza == null) {

            cabeza = nuevo;
            cola   = nuevo;
            tamano++;
            System.out.println("  Paciente agregado: " + nuevo.nombre);
            return;
        }

        
        nuevo.anterior = cola;
        cola.siguiente = nuevo;
        cola           = nuevo;
        tamano++;
        System.out.println("  Paciente agregado: " + nuevo.nombre);

        
        if (urgencia == 5) {
            moverDespuesDeCabeza(nuevo);
        }
    }

   
    private void moverDespuesDeCabeza(Paciente p) {

       
        if (p == cabeza || p == cabeza.siguiente) {
            return;
        }

       
        if (p == cola) {
            
            cola           = p.anterior;
            cola.siguiente = null;
        } else {
            
            p.anterior.siguiente = p.siguiente;
            p.siguiente.anterior = p.anterior;
        }

       
        p.siguiente            = cabeza.siguiente;
        p.anterior             = cabeza;

        if (cabeza.siguiente != null) {
            cabeza.siguiente.anterior = p;
        }
        cabeza.siguiente = p;

        System.out.println("  EMERGENCIA: " + p.nombre +
                           " movido al frente de la fila (urgencia 5).");
    }

  
    public void atenderSiguiente() {
        if (cabeza == null) {
            System.out.println("  No hay pacientes en espera.");
            return;
        }

        System.out.println("  Atendiendo a: " + cabeza);

        if (tamano == 1) {
            cabeza = null;
            cola   = null;
        } else {
            cabeza          = cabeza.siguiente;
            cabeza.anterior = null;
        }
        tamano--;
    }

   
    public void mostrarFila() {
        if (cabeza == null) {
            System.out.println("  La fila esta vacia.");
            return;
        }
        System.out.println("  === Fila de espera (" + tamano + " pacientes) ===");
        Paciente cursor = cabeza;
        int pos = 1;
        while (cursor != null) {
            System.out.println("  " + pos + ". " + cursor);
            cursor = cursor.siguiente;
            pos++;
        }
    }

   
    public void pacienteMayorEdad() {
        if (cabeza == null) {
            System.out.println("  La fila esta vacia.");
            return;
        }

        Paciente cursor = cola;
        Paciente mayor  = cola;

        System.out.println("  === Recorrido de COLA a CABEZA ===");
        while (cursor != null) {
            System.out.println("  Revisando: " + cursor);
            if (cursor.edad > mayor.edad) {
                mayor = cursor;
            }
            cursor = cursor.anterior;
        }

        System.out.println("\n  Paciente de mayor edad encontrado:");
        System.out.println("  " + mayor);
    }

    public boolean estaVacia() {
        return tamano == 0;
    }

    public int getTamano() {
        return tamano;
    }
}