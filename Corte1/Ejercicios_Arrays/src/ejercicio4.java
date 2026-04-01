 //Control de Acceso (Gimnasio)
//Un gimnasio tiene una lista de códigos de acceso de los socios que pagaron 
//la mensualidad. La lista está ordenada de menor a mayor.

//El Problema: Cuando un socio digita su código, el sistema debe validar si el código está en la lista de "pagos al día". Si no está, se le niega la entrada.
//Algoritmo a usar: Búsqueda Binaria. Es eficiente para buscar códigos numéricos en una lista que ya está organizada.

import java.util.Scanner;

public class ejercicio4 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        int[] listaCodigos = new int[500];

        for (int i = 0; i < listaCodigos.length; i++) {
            listaCodigos[i] = 1000 + i;
        }

        System.out.print("Ingrese su codigo de acceso: ");
        int codigoUsuario = teclado.nextInt();

        int inicio = 0;
        int fin = listaCodigos.length - 1;
        boolean encontrado = false;

        while (inicio <= fin) {

            int centro = (inicio + fin) / 2;

            if (listaCodigos[centro] == codigoUsuario) {
                encontrado = true;
                break;
            } 
            else if (codigoUsuario > listaCodigos[centro]) {
                inicio = centro + 1;
            } 
            else {
                fin = centro - 1;
            }
        }

        if (encontrado) {
            System.out.println("Acceso permitido. Bienvenido al gimnasio.");
        } else {
            System.out.println("Acceso denegado. Mensualidad pendiente.");
        }

        teclado.close();
    }
}