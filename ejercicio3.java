//un sensor industrial registra temperaturas. El sistema recibe:
//Temperatura actual en Celsius (double)
//Nombre del sensor (String)
//Número de lectura (int)
//Si la temperatura supera el límite de 80°C, una variable boolean llamada enAlarma debe ser true.
//Imprime el estado del sensor con todos sus datos.
public class ejercicio3 {
    public static void main(String[] args) {
        
        double Temperatura = 81.0;
        String Sensor = "DH111";
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
