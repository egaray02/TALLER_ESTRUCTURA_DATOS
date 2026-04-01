import java.util.Scanner;
public class reto {
    public static void main(String[] args) {
        double [] precios = new double [5];
        Scanner scanner = new Scanner(System.in); 
        double suma = 0;
        double mayor = 0;

        for (int i = 0; i < precios.length; i++) {
            System.out.print("Ingresa el precio " + (i + 1) + ": ");
            precios[i] = scanner.nextDouble();

            suma += precios[i];

            if (i == 0 || precios[i] > mayor) {
                mayor = precios[i];
            }
        }
        System.out.println("Suma total: " + suma);
        System.out.println("Precio más alto: " + mayor);

        if (suma > 20000) {
            System.out.println("Tienes un descuento de 2.000");
            suma -= 2000;
        }

        System.out.println("Total final a pagar: " + suma);

        scanner.close();
    }

    
    
}
