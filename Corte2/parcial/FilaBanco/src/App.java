import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        java.util.Scanner sc = new Scanner(System.in);
        Fila fila = new Fila();
        int i = 0;
        do{
            System.out.println("\n==============================================");
            System.out.println("FILA DE ATENCION EN UN BANCO");
            System.out.println("\n==============================================");
            System.out.println("1. Tomar turno ");
            System.out.println("2. Atender siguiente");
            System.out.println("3. Ver fila");
            System.out.println("0. Salir");

            i = sc.nextInt();

            switch (i) {
                case 1:
                    System.out.println("Ingrese el nombre, turno");
                    System.out.println("turno");
                    int turno = sc.nextInt();
                    System.out.println("nombre");
                    String nombre = sc.next();
                    fila.agregarFinal(nombre,turno);
                    break;
                case 2:
                    System.out.println("Ingrese el turno");
                    int turno2 = sc.nextInt();
                    fila.eliminarCliente(turno2);
                    break;
                case 3:
                    
                    fila.mostrar();
                    break;  
                case 0:
                    System.out.println("saliendo es saliendo");
                    break;
                        
                default:
                    break;
            }
        }
        while(i != 0);
        sc.close();
    
    }
    
}
