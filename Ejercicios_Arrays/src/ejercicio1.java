//Un cajero escanea un producto con el código de barras 770123.
//El sistema tiene un arreglo desordenado con los códigos de los productos disponibles en la estantería actual.
//El Problema: Debes recorrer la lista para verificar si el producto existe y en qué posición de la estantería se encuentra.
//Algoritmo a usar: Búsqueda Lineal. Es el ideal porque los productos en la estantería no tienen un orden numérico específico.
public class ejercicio1 {

    public static void main(String[] args) {
        int[] codigos = {112345, 235678, 346789, 770123};
        int codigobuscado = 770123;

        int posicion = buscarCodigo(codigos, codigobuscado);

        if (posicion != -1) {
            System.out.println("Código encontrado en la posición: " + posicion);
        } else {
            System.out.println("Código no encontrado");
        }
    }

    public static int buscarCodigo(int[] codigos, int codigobuscado) {
        for (int i = 0; i < codigos.length; i++) {
            if (codigos[i] == codigobuscado) {
                return i; 
            }
        }
        return -1; 
    }
}