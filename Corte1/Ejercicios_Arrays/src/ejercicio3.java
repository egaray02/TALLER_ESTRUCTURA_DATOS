//. Sensor de Temperatura (Control de Calidad)
//Una máquina industrial registra la temperatura cada 10 minutos y guarda los datos en un arreglo.

//El Problema: Al final del día, el supervisor quiere saber cuál fue la temperatura más alta registrada para asegurarse de que la máquina no se recalentó.
//Algoritmo a usar: Búsqueda Lineal de Máximo. Debes recorrer todo el arreglo comparando cada valor para encontrar el mayor de todos.

public class ejercicio3 {

    public static void main(String[] args) {

        
        double[] registros = new double[120];

        for (int i = 0; i < registros.length; i++) {
            registros[i] = 60 + Math.random() * 40;
        }

        double mayorTemperatura = registros[0];

        for (int i = 1; i < registros.length; i++) {
            if (registros[i] > mayorTemperatura) {
                mayorTemperatura = registros[i];
            }
        }

        System.out.println("La temperatura mas alta registrada fue: " + mayorTemperatura);
    }
}