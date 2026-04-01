
/* La Clase Vagon (Nodo): Debe contener contenido (String), pesoToneladas (double), origen (String) y destino (String).
 */
public class vagon {

    String contenido;
    double pesoToneladas;
    String origen;
    String destino;

    vagon siguiente;

    public vagon(String contenido, double pesoToneladas, String origen, String destino) {
        this.contenido = contenido;
        this.pesoToneladas = pesoToneladas;
        this.origen = origen;
        this.destino = destino;
        this.siguiente = null;
    }
}
