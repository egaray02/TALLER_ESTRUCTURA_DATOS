
//Una app tiene un objeto int[] configuracion = {1920, 1080, 60} (resolución y FPS).
//Crea una copia real del arreglo (no una referencia) usando un nuevo arreglo y copiando elemento por elemento.
//Modifica la copia y demuestra que el original no cambia.
//Comenta la diferencia con la asignación directa.
public class ejercicio10 {
    public static void main(String[] args) {
        
        int[] configuracion = {1920, 1080, 60};

        int[] copia = new int[configuracion.length];
        for (int i = 0; i < configuracion.length; i++) {
            copia[i] = configuracion[i];
        }

       
        copia[2] = 115; 

        System.out.println("Configuración original:");
        for (int i = 0; i < configuracion.length; i++) {
            System.out.println(configuracion[i]);
        }

        System.out.println("\nCopia modificada:");
        for (int i = 0; i < copia.length; i++) {
            System.out.println(copia[i]);
        }

        int[] referencia = configuracion; 
        referencia[2] = 30; 
        System.out.println("Después de asignación directa:");
        System.out.println("Original: " + configuracion[2]);
        System.out.println("Referencia: " + referencia[2]);

    }
    
}
