// Esta clase representa UN producto (es el "nodo" de la lista enlazada)
// Cada producto guarda su informacion Y apunta al siguiente producto
public class Producto {

    // Atributos: la informacion que guarda cada producto
    int codigo;        // numero unico que identifica el producto
    String nombre;     // nombre del producto
    int cantidad;      // cuantas unidades hay en stock
    double precio;     // precio unitario
    Producto siguiente; // puntero al siguiente producto en la lista (null si es el ultimo)

    // Constructor: se llama cuando creamos un nuevo producto con "new Producto(...)"
    public Producto(int codigo, String nombre, int cantidad, double precio) {
        this.codigo = codigo;        // "this" se refiere al atributo de la clase
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.siguiente = null;       // al crearse, no apunta a nadie todavia
    }
}