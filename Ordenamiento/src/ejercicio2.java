//2. Organización de Biblioteca Dinámica
//Algoritmo obligatorio: Ordenamiento por Inserción (Insertion Sort)
//Contexto: Un bibliotecario recibe una caja de libros y los va colocando uno a uno en el estante en su posición correcta.
//Detalle del ejercicio: El usuario debe ingresar la cantidad de libros y luego el código ISBN (número entero) de cada uno.
//Lógica de Inserción: A medida que el usuario ingresa un número, o una vez llenado el arreglo, el algoritmo debe simular el proceso de "insertar" el elemento comparándolo con los que ya están a su izquierda.
//Visualización Paso a Paso: En cada ciclo del ordenamiento, el programa debe imprimir cómo va quedando el arreglo (ej: [10, 25, 5, 30] -> [5, 10, 25, 30]). Esto permite al estudiante ver cómo los elementos se desplazan para abrir espacio al nuevo valor.

import java.util.Arrays;
import java.util.Scanner;

public class ejercicio2 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de libros: ");
        int cantidad = teclado.nextInt();

        int[] codigosisbn = new int[cantidad];

        for (int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese el ISBN del libro " + (i + 1) + ": ");
            codigosisbn[i] = teclado.nextInt();
        }

        System.out.println("\nArreglo inicial: " + Arrays.toString(codigosisbn));

    
        for (int i = 1; i < cantidad; i++) {

            int valoractual = codigosisbn[i];
            int posicion = i - 1;

            while (posicion >= 0 && codigosisbn[posicion] > valoractual) {
                codigosisbn[posicion + 1] = codigosisbn[posicion];
                posicion--;
            }

            codigosisbn[posicion + 1] = valoractual;

            System.out.println("Paso " + i + ": " + Arrays.toString(codigosisbn));
        }

        System.out.println("\nISBN ordenados en el estante:");
        System.out.println(Arrays.toString(codigosisbn));

        teclado.close();
    }
}