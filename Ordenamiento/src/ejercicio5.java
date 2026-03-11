/*
Algoritmos obligatorios: Selección vs Inserción. Un analista de sistemas quiere saber cuál algoritmo
es más "pesado" en términos de operaciones para un caso específico.
El usuario debe ingresar 6 números enteros desordenados y el programa aplicará ambos métodos
al mismo conjunto de datos usando una copia del arreglo original.
En Selección se cuenta cada vez que ocurre un swap (intercambio de posiciones) y en Inserción
cada vez que un elemento se mueve hacia la izquierda dentro del bucle interno.
Al final el programa mostrará cuántos intercambios hizo Selección y cuántos movimientos hizo Inserción
para concluir cuál fue más eficiente con los datos ingresados por el usuario.
*/
import java.util.Scanner;

public class ejercicio5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] numeros = new int[6];

        System.out.println("Ingrese 6 numeros enteros:");

        for (int i = 0; i < 6; i++) {
            System.out.print("Numero " + (i + 1) + ": ");
            numeros[i] = sc.nextInt();
        }
      
        int[] seleccion = numeros.clone();
        int[] insercion = numeros.clone();

        int swapsSeleccion = ordenSeleccion(seleccion);
        int movimientosInsercion = ordenInsercion(insercion);

        System.out.println("\nResultados:");
        System.out.println("Intercambios en Seleccion: " + swapsSeleccion);
        System.out.println("Movimientos en Insercion: " + movimientosInsercion);

        if (swapsSeleccion < movimientosInsercion) {
            System.out.println("Seleccion fue mas eficiente para estos datos.");
        } else if (movimientosInsercion < swapsSeleccion) {
            System.out.println("Insercion fue mas eficiente para estos datos.");
        } else {
            System.out.println("Ambos algoritmos tuvieron el mismo costo.");
        }
    }

    public static int ordenSeleccion(int[] arr) {

        int swaps = 0;

        for (int i = 0; i < arr.length - 1; i++) {

            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
                swaps++;
            }
        }

        return swaps;
    }
    public static int ordenInsercion(int[] arr) {

        int movimientos = 0;

        for (int i = 1; i < arr.length; i++) {

            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                movimientos++;
            }

            arr[j + 1] = key;
        }

        return movimientos;
    }
}
