public class CarruselAnuncio {

    private Anuncio cabeza;
    private Anuncio cola;
    private int     tamanio;

    public CarruselAnuncio() {
        cabeza  = null;
        cola    = null;
        tamanio = 0;
    }

    public void agregar(Anuncio nuevo) {
        if (cabeza == null) {
            cabeza         = nuevo;
            cola           = nuevo;
            cola.siguiente = cabeza;
        } else {
            cola.siguiente = nuevo;
            cola           = nuevo;
            cola.siguiente = cabeza;
        }
        tamanio++;
    }

    public void reproducir(int ciclos) {
        if (cabeza == null) {
            System.out.println("El carrusel está vacío.");
            return;
        }

        int tiempoTotal = 0;

        System.out.println("\nIniciando carrusel — " + ciclos + " ciclo(s)\n"
                         + "─".repeat(45));

        for (int c = 1; c <= ciclos; c++) {
            System.out.println("\n  Ciclo " + c + ":");
            Anuncio actual = cabeza;
            do {
                actual.incrementarRepeticion();
                tiempoTotal += actual.getDuracionSegundos();
                System.out.printf("    %-35s ×%d%n",
                        actual, actual.getVecesRepetido());
                actual = actual.siguiente;
            } while (actual != cabeza);
        }

        System.out.println("\n" + "─".repeat(45));
        System.out.println("  Tiempo total: " + tiempoTotal + " s"
                         + " (" + (tiempoTotal / 60) + " min "
                         + (tiempoTotal % 60) + " s)");
        System.out.println(" Más repetido: " + anuncioMasRepetido());
    }

    private Anuncio anuncioMasRepetido() {
        Anuncio max    = cabeza;
        Anuncio actual = cabeza.siguiente;
        while (actual != cabeza) {
            if (actual.getVecesRepetido() > max.getVecesRepetido()) {
                max = actual;
            }
            actual = actual.siguiente;
        }
        return max;
    }

    public void listar() {
        if (cabeza == null) {
            System.out.println("  (sin anuncios)");
            return;
        }
        Anuncio actual = cabeza;
        int i = 1;
        do {
            System.out.printf("  %d. %s  — repetido: %d vez(ces)%n",
                    i++, actual, actual.getVecesRepetido());
            actual = actual.siguiente;
        } while (actual != cabeza);
    }

    public int getTamanio() { return tamanio; }
}
