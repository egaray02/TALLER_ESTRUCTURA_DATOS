//Un chat guarda el último mensaje enviado. Se usan dos variables String:
//ultimoMensaje = "Hola"
//copiaTexto = ultimoMensaje
//Luego copiaTexto se cambia a "¿Cómo estás?"
//Muestra ambas variables y explica en un comentario por qué ultimoMensaje no cambió 
// (inmutabilidad de String).
public class ejercicio8 {
    public static void main(String[] args) {
        String UltimoMensaje = "hola";
        String CopiaTexto= UltimoMensaje;
        CopiaTexto="como estas?";
        System.out.println("Ultimo mensaje: "+UltimoMensaje);
        System.out.println("Copia de texto: "+CopiaTexto);
        

    }
}

// Ultimomensaje recibe el valor de copiadetexto.
// Al cambiar ultimomensaje, copiadetexto queda igual porque
// ambas variables son independientes
