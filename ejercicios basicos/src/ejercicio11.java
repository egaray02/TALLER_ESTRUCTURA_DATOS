//Una aerolínea registra los nombres de los pasajeros de un vuelo en un arreglo de String con capacidad para 6 personas.
//Llena el arreglo con nombres.
//Recórrelo con un for e imprime el número de asiento y el nombre.
//Muestra cuántos asientos están ocupados.
public class ejercicio11 {
    public static void main(String[] args) {
        String[] pasajeros = new String[6];

        pasajeros[0] = "Ana";
        pasajeros[1] = "Luis";
        pasajeros[2] = "María";
        pasajeros[3] = "Carlos";
        pasajeros[4] = "Sofía";
        pasajeros[5] = "Pedro";

        int ocupados = 0;

        for (int i = 0; i < pasajeros.length; i++) {
            System.out.println("Asiento " + (i + 1) + ": " + pasajeros[i]);
            if (pasajeros[i] != null) {
                ocupados++;
            }
        }

        System.out.println("Asientos ocupados: " + ocupados);
    }
    
}
