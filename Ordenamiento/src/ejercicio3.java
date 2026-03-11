//3. El Salto de Shell (Optimización de Datos)
//Algoritmo obligatorio: Ordenamiento Shell (Shell Sort)

//Contexto: Una empresa de logística maneja paquetes con diferentes pesos y necesita ordenarlos de forma más eficiente que el método de burbuja o inserción simple.
//Detalle del ejercicio: Solicita al usuario el peso de N paquetes (se recomienda probar con al menos 10 para notar el efecto).
//Lógica de Shell: Implementa el algoritmo usando el salto (gap) inicial de 
//N/2El estudiante debe explicar en comentarios por qué este método es generalmente más rápido que la inserción simple al trabajar con elementos que están muy lejos de su posición final.
//Resultado esperado: Mostrar el arreglo original y el arreglo final ordenado después de aplicar todas las fases de reducción de saltos.

import java.util.Arrays;
import java.util.Scanner;

public class ejercicio3 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de paquetes: ");
        int cantidad = teclado.nextInt();

        double[] listapesos = new double[cantidad];

        for (int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese el peso del paquete " + (i + 1) + ": ");
            listapesos[i] = teclado.nextDouble();
        }

        System.out.println("\nPesos originales:");
        System.out.println(Arrays.toString(listapesos));

        
        for (int salto = cantidad / 2; salto > 0; salto /= 2) {

            for (int i = salto; i < cantidad; i++) {

                double auxiliar = listapesos[i];
                int posicion;

                for (posicion = i; posicion >= salto && listapesos[posicion - salto] > auxiliar; posicion -= salto) {
                    listapesos[posicion] = listapesos[posicion - salto];
                }
                listapesos[posicion] = auxiliar;
            }
        }

        System.out.println("\nPesos ordenados:");
        System.out.println(Arrays.toString(listapesos));

        teclado.close();
    }
}

// RESPUESTA:
//Shell Sort es más rápido que Insertion Sort porque usa saltos (gap) para comparar elementos que están lejos entre sí. Esto permite mover más rápido los valores que están desordenados. Cuando el salto llega a 1, el arreglo ya está casi ordenado y el proceso final es más rápido.