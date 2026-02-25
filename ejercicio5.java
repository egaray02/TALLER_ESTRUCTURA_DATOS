//Una aplicación de cocina necesita convertir unidades:
//Declara una variable tazas (double) con la cantidad de tazas de un ingrediente.
//Convierte a mililitros (1 taza = 236.588 ml) y guarda el resultado en otra variable.
//Convierte también a litros.
//Muestra las tres medidas por consola.
public class ejercicio5 {
    public static void main(String[] args) {
        double tazas = 2.5;
        double mililitros = tazas * 236.588;
        double litros = mililitros / 1000;
        System.out.println("Tazas: " + tazas);
        System.out.println("Mililitros: " + mililitros);
        System.out.println("Litros: " + litros);
    }

}