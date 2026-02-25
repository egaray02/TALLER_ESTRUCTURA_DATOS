//Ejercicio 15 — Registro de temperaturas semanales (lineal)
//Una estación meteorológica registra la temperatura máxima de cada día de la semana en un arreglo.
//Llena el arreglo con 7 valores.
//Calcula el promedio semanal.
//Indica qué días estuvieron por encima del promedio.

public class ejercicio15 {
    public static void main(String[] args) {

        double[] temperaturas = {28.5, 30.0, 27.8, 29.2, 31.4, 26.9, 30.6};

        double suma = 0;
        for (int i = 0; i < temperaturas.length; i++) {
            suma += temperaturas[i];
        }

        double promedio = suma / temperaturas.length;

        System.out.println("Promedio semanal: " + promedio);

        System.out.println("Días por encima del promedio:");
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] > promedio) {
                System.out.println("Día " + (i + 1) + ": " + temperaturas[i]);
            }
        }
    }
}
