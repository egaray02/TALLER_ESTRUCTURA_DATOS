
//Un cajero de banco trabaja con el número del turno actual. 
// Se crea una variable turnoActual y se asigna a turnoEnPantalla.
//Demuestra con código que cambiar turnoEnPantalla no afecta a turnoActual 
//(comportamiento por valor).
//Explica en un comentario por qué ocurre esto.
public class ejercicio6 {
    public static void main(String[] args) {
        int turnoActual=24;
        int turnoEnPantalla=turnoActual;
        turnoEnPantalla=26;

        System.err.println("turno actual:"+ turnoActual);
        
        System.out.println("turno en pantalla:"+ turnoEnPantalla);

    }
    
}
//los tipos como int se asignan por valor.
// turnoEnPantalla recibe el valor de turnoActual.
// Al cambiar turnoEnPantalla, turnoActual queda igual porque
// ambas variables son independientes