public class TorneoRound {
    private Equipo cabeza;
    private int tamanio;

    public TorneoRound() {
        cabeza = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregarEquipo(String nombre, String ciudad, int puntos, int golesFavor) {
        Equipo nuevo = new Equipo(nombre, ciudad, puntos, golesFavor);
        if (estaVacia()) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
        } else {
            Equipo actual = cabeza;
            while (actual.siguiente != cabeza) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
            nuevo.siguiente = cabeza;
        }
        tamanio++;
    }

    private Equipo[] obtenerArreglo() {
        Equipo[] arr = new Equipo[tamanio];
        Equipo actual = cabeza;
        for (int i = 0; i < tamanio; i++) {
            arr[i] = actual;
            actual = actual.siguiente;
        }
        return arr;
    }

    private void rotarUnaposicion(Equipo[] arr) {
        Equipo ultimo = arr[tamanio - 1];
        for (int i = tamanio - 1; i > 1; i--) {
            arr[i] = arr[i - 1];
        }
        arr[1] = ultimo;
    }

    public void generarFixture() {
        if (tamanio % 2 != 0) {
            System.out.println("Se necesita un numero par de equipos.");
            return;
        }

        Equipo[] arr = obtenerArreglo();
        int jornadas = tamanio - 1;

        for (int j = 1; j <= jornadas; j++) {
            System.out.println("\nJornada " + j + ":");
            for (int i = 0; i < tamanio / 2; i++) {
                Equipo local = arr[i];
                Equipo visitante = arr[tamanio - 1 - i];
                System.out.println("  " + local.nombre + " vs " + visitante.nombre);
            }
            rotarUnaposicion(arr);
        }
    }

    public void tablaPosiciones() {
        Equipo[] arr = obtenerArreglo();

        for (int i = 0; i < tamanio - 1; i++) {
            for (int j = i + 1; j < tamanio; j++) {
                if (arr[j].puntos > arr[i].puntos ||
                   (arr[j].puntos == arr[i].puntos && arr[j].golesFavor > arr[i].golesFavor)) {
                    Equipo temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("\nTABLA DE POSICIONES");
        System.out.println("Pos | Equipo          | Ciudad          | Pts | Goles");
        System.out.println("----+-----------------+-----------------+-----+------");
        for (int i = 0; i < tamanio; i++) {
            System.out.printf("%-4d| %-16s| %-16s| %-4d| %d%n",
                i + 1,
                arr[i].nombre,
                arr[i].ciudad,
                arr[i].puntos,
                arr[i].golesFavor);
        }
    }
}