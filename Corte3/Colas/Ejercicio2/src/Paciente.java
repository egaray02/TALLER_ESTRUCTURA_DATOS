public class Paciente {
    String cedula;
    String nombreCompleto;
    int edad;
    String sintomaPrincipal;
    Paciente siguiente;

    public Paciente(String cedula, String nombreCompleto, int edad, String sintomaPrincipal) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.sintomaPrincipal = sintomaPrincipal;
        this.siguiente = null;
    }
}