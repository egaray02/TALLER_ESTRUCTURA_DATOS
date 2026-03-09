//Contador de Estudiantes (Registro Académico)
//Un profesor tiene un arreglo con las notas finales de 30 estudiantes 
//(ej: 3.5, 4.0, 2.8, 5.0, ...).

//El Problema: El profesor necesita saber exactamente cuántos estudiantes sacaron una nota de 5.0.
//Algoritmo a usar: Búsqueda Lineal con Contador. Debes recorrer todo el arreglo y, cada vez que encuentres un 5.0, aumentar una variable contador.

import java.util.Scanner;

public class ejercicio5 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        //double[] notas = new double[5];
        double[] calificaciones = {3, 4.5, 5, 5, 2, 5, 3.4, 5};

        int totalCinco = 0;

        // for (int i = 0; i < calificaciones.length; i++) {
        //     System.out.print("Ingrese la nota del estudiante " + (i + 1) + ": ");
        //     calificaciones[i] = teclado.nextDouble();
        // }

        for (int i = 0; i < calificaciones.length; i++) {
            if (calificaciones[i] == 5.0) {
                totalCinco++;
            }
        }

        System.out.println("Cantidad de estudiantes con nota 5.0: " + totalCinco);

        teclado.close();
    }
}