public class ejercicio3 {
    public static void main(String[] args) {
        
        double Temperatura = 81.0;
        String Sensor = "DHT11";
        int numerolecturas = 5;
        boolean alarma = false;

        if (Temperatura>80){
            alarma = true;
        }
        System.out.println("**********Datos del sensor de temperatura*************" );
        System.out.println("Temperatura: " + Temperatura);
        System.out.println("Sensor: " + Sensor);
        System.out.println("Numero de lecturas: " + numerolecturas);
        System.out.println("Alarma activada: " + alarma);

    }

}
