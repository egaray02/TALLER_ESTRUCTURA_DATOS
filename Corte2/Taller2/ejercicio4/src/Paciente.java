public class Paciente {

    String nombre;
    int    edad;
    int    nivelUrgencia;

   
    Paciente anterior;
    Paciente siguiente;

    public Paciente(String nombre, int edad, int nivelUrgencia) {
        this.nombre       = nombre;
        this.edad         = edad;
        this.nivelUrgencia = nivelUrgencia;
        this.anterior     = null;
        this.siguiente    = null;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " | Edad: " + edad +
               " | Urgencia: " + nivelUrgencia;
    }
}