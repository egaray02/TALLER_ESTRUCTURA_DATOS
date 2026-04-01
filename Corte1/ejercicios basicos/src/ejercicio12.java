//Una tienda online guarda las ventas del día en un arreglo de double:
//Almacena 7 ventas de ejemplo.
//Calcula e imprime: la venta más alta, la venta más baja y el total del día.
public class ejercicio12 {
    public static void main(String[] args) {
        double [] ventas={500.000,700.000,200.000,340.000,150.000,460.000,900.000};
        double mayor = ventas[0];
        double menor = ventas[0];
        double total = 0;

        for (int i = 0; i < ventas.length; i++) {
            double v = ventas[i];

            if (v > mayor) {
                mayor = v;
            }

            if (v < menor) {
                menor = v;
            }

            total += v;
        }

        System.out.println("Venta más alta: " + mayor);
        System.out.println("Venta más baja: " + menor);
        System.out.println("Total del día: " + total);
    }
    

}
