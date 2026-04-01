public class HistorialSensores {

    private Lectura cabeza;

    
    public void agregarLectura(Lectura nueva) {
        nueva.siguiente = cabeza;
        cabeza = nueva;
        System.out.println("Lectura registrada: Sensor " + nueva.idSensor
                + " | " + nueva.hora
                + " | Temp: " + nueva.temperatura + "°C"
                + " | Presión: " + nueva.presion + " bar");
    }

    public void reportarTemperaturaMaxima() {
        if (cabeza == null) {
            System.out.println("No hay lecturas registradas.");
            return;
        }

        Lectura actual = cabeza;
        Lectura maxima = cabeza;   

        while (actual != null) {
            if (actual.temperatura > maxima.temperatura) {
                maxima = actual;   
            }
            actual = actual.siguiente;
        }

        System.out.println("\n==============================================");
        System.out.println("   Lectura con temperatura más alta");
        System.out.println("==============================================");
        System.out.println("  Sensor    : " + maxima.idSensor);
        System.out.println("  Hora      : " + maxima.hora);
        System.out.println("  Temperatura: " + maxima.temperatura + " °C");
        System.out.println("  Presión   : " + maxima.presion + " bar");
    }

    public void imprimirHistorial() {
        System.out.println("\n==============================================");
        System.out.println("   Historial de lecturas (más reciente primero)");
        System.out.println("==============================================");
        if (cabeza == null) {
            System.out.println("  (sin registros)");
            return;
        }
        Lectura actual = cabeza;
        int pos = 1;
        while (actual != null) {
            System.out.println("[" + pos + "] Sensor " + actual.idSensor
                    + " | " + actual.hora
                    + " | Temp: " + actual.temperatura + " °C"
                    + " | Presión: " + actual.presion + " bar");
            actual = actual.siguiente;
            pos++;
        }
    }
}