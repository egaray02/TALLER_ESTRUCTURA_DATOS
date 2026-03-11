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

        System.out.print("Numero de libros: ");
        int cantidad = teclado.nextInt();

        int[] isbn = new int[cantidad];

        for (int i = cantidad - 1; i >= 0; i--) {

            System.out.print("\nIngrese ISBN: ");
            int valor = teclado.nextInt();

            int posicion = i;

            while (posicion < cantidad - 1 && isbn[posicion + 1] < valor) {
                isbn[posicion] = isbn[posicion + 1];
                posicion++;
            }

            isbn[posicion] = valor;

            System.out.println(Arrays.toString(isbn));
        }

        teclado.close();
    }
}