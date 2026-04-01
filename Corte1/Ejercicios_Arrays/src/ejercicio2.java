//Buscador de Cédulas (Base de Datos Bancaria)
//Un banco tiene una lista de 1.000 clientes organizados de forma estricta y ascendente por su número de cédula o ID.
//El Problema: Un cliente llega a la ventanilla y da su número.
//  El sistema debe encontrar sus datos de la manera más rápida posible (en pocos pasos).
//Algoritmo a usar: Búsqueda Binaria. Como los datos ya están ordenados,
//  este algoritmo permitirá encontrar al cliente dividiendo la lista a la mitad en cada paso.
import java.util.Scanner;

public class ejercicio2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Arreglo ordenado ascendentemente (simulando clientes)
        int[] cedulas = {1001, 1020, 1050, 1100, 1200, 1300, 1500, 1700, 2000};

        System.out.print("Ingrese el número de cédula a buscar: ");
        int cedulaBuscada = sc.nextInt();

        int izquierda = 0;
        int derecha = cedulas.length - 1;
        int posicion = -1;

        // Búsqueda Binaria
        while (izquierda <= derecha) {
            int medio = (izquierda + derecha) / 2;

            if (cedulas[medio] == cedulaBuscada) {
                posicion = medio;
                break;
            } 
            else if (cedulas[medio] < cedulaBuscada) {
                izquierda = medio + 1; 
            } 
            else {
                derecha = medio - 1; 
            }
        }

        // Resultado
        if (posicion != -1) {
            System.out.println("Cliente encontrado en la posición: " + posicion);
        } else {
            System.out.println("Cliente no encontrado en la base de datos.");
        }

        sc.close();
    }

    public static int busquedaBinaria(int[] cedulas, int buscado) {

        int izquierda = 0;
        int derecha = cedulas.length - 1;

        while (izquierda <= derecha) {

            int medio = (izquierda + derecha) / 2;

            if (cedulas[medio] == buscado) {
                return medio; 
            }

            if (buscado < cedulas[medio]) {
                derecha = medio - 1; 
            } else {
                izquierda = medio + 1; 
            }
        }

        return -1;
    }
}