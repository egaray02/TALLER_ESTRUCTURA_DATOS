//El RUNT almacena datos básicos de vehículos. Modela la información de un carro con:
//Placa (String)
//Año de fabricación (int)
//Cilindraje en cc (int)
//Precio de compra (double)
//Inicial del color (char)
//Si tiene SOAT vigente (boolean)
public class ejercicio4 {
    public static void main(String[] args) {
        String Placa = "ABC123";
        int Cilindraje_en_cc = 1500;
        double Precio_de_compra = 20000.50;
        char Inicial_del_color = 'R';
        boolean Tiene_SOAT_vigente = true;
        int Año_de_fabricacion = 2020;
        
        System.out.println("**********Datos del vehículo*************" );
        System.out.println("Placa: " + Placa);
        System.out.println("Año de fabricación: " + Año_de_fabricacion);
        System.out.println("Cilindraje en cc: " + Cilindraje_en_cc);
        System.out.println("Precio de compra: " + Precio_de_compra);
        System.out.println("Inicial del color: " + Inicial_del_color);
        System.out.println("Tiene SOAT vigente: " + Tiene_SOAT_vigente);
    }

}
