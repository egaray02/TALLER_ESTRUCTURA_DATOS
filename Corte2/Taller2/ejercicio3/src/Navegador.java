public class Navegador {

    private pestaña cabeza;    
    private pestaña cola;      
    private pestaña actual;    
    private int tamano;

    public Navegador() {
        cabeza = null;
        cola   = null;
        actual = null;
        tamano = 0;
    }

   
    public void abrirPestana(String titulo, String url, String hora) {
        pestaña nueva = new pestaña(titulo, url, hora);

        if (cabeza == null) {
            cabeza = nueva;
            cola   = nueva;
            actual = nueva;
        } else {
            nueva.anterior = cola;
            cola.siguiente = nueva;
            cola           = nueva;
            actual         = nueva;   // el foco va a la nueva pestaña
        }
        tamano++;
        System.out.println("  Pestana abierta: " + nueva.tituloPagina);
    }

   
    public void verPestanaActual() {
        if (actual == null) {
            System.out.println("  No hay pestanas abiertas.");
            return;
        }
        int pos = posicionActual();
        System.out.println("  Pestana activa " + pos + " de " + tamano + ":");
        System.out.println("  " + actual);
    }

    
    public void siguientePestana() {
        if (actual == null) {
            System.out.println("  No hay pestanas abiertas.");
            return;
        }
        if (actual.siguiente == null) {
            System.out.println("  Ya estas en la ULTIMA pestana.");
        } else {
            actual = actual.siguiente;
            System.out.println("  Avanzaste a: " + actual.tituloPagina);
        }
    }

    
    public void pestanaAnterior() {
        if (actual == null) {
            System.out.println("  No hay pestanas abiertas.");
            return;
        }
        if (actual.anterior == null) {
            System.out.println("  Ya estas en la PRIMERA pestana.");
        } else {
            actual = actual.anterior;
            System.out.println("  Retrocediste a: " + actual.tituloPagina);
        }
    }

    
    public void mostrarTodas() {
        if (cabeza == null) {
            System.out.println("  No hay pestanas abiertas.");
            return;
        }
        System.out.println("  === Pestanas abiertas (" + tamano + ") ===");
        pestaña cursor = cabeza;
        int pos = 1;
        while (cursor != null) {
            String marca = (cursor == actual) ? " <-- ACTIVA" : "";
            System.out.println("  " + pos + ". " + cursor + marca);
            cursor = cursor.siguiente;
            pos++;
        }
    }

    
    public void cerrarPestanaActual(String url) {
        if (cabeza == null) {
            System.out.println("  No hay pestanas abiertas.");
            return;
        }

       
        pestaña cursor = cabeza;
        while (cursor != null) {
            if (cursor.url.equalsIgnoreCase(url)) {
                break;
            }
            cursor = cursor.siguiente;
        }

        
        if (cursor == null) {
            System.out.println("  No se encontro ninguna pestana con esa URL.");
            return;
        }

        String tituloEliminado = cursor.tituloPagina;

        
        if (tamano == 1) {
            cabeza = null;
            cola   = null;
            actual = null;

        
        } else if (cursor == cabeza) {
            cabeza          = cabeza.siguiente;
            cabeza.anterior = null;
            if (actual == cursor) {
                actual = cabeza;   
            }

       
        } else if (cursor == cola) {
            cola          = cola.anterior;
            cola.siguiente = null;
            if (actual == cursor) {
                actual = cola;    
            }

        } else {
            cursor.anterior.siguiente = cursor.siguiente;
            cursor.siguiente.anterior = cursor.anterior;
            if (actual == cursor) {
                actual = cursor.anterior; 
            }
        }

       
        cursor.anterior = null;
        cursor.siguiente = null;

        tamano--;
        System.out.println("  Pestana cerrada: " + tituloEliminado);

        if (actual != null) {
            System.out.println("  Foco ahora en: " + actual.tituloPagina);
        } else {
            System.out.println("  No quedan pestanas abiertas.");
        }
    }

   
    private int posicionActual() {
        pestaña cursor = cabeza;
        int pos = 1;
        while (cursor != null && cursor != actual) {
            cursor = cursor.siguiente;
            pos++;
        }
        return pos;
    }

    public boolean estaVacio() {
        return tamano == 0;
    }

    public int getTamano() {
        return tamano;
    }
}