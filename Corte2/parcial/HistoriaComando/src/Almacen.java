// Esta clase ES la lista enlazada
// Maneja todos los productos: agregar, buscar, editar, mostrar y eliminar
public class Almacen {

    // La cabeza es el PRIMER producto de la lista
    // Desde ella podemos llegar a todos los demas siguiendo los punteros "siguiente"
    private Producto cabeza;

    // Constructor: cuando se crea el almacen, la lista empieza vacia
    public Almacen() {
        this.cabeza = null; // null significa que no hay ningun producto aun
    }

    // =====================================================================
    // METODO 1: REGISTRAR - agrega un nuevo producto AL FINAL de la lista
    // =====================================================================
    public void registrar(int codigo, String nombre, int cantidad, double precio) {

        // Validacion 1: no permitir cantidades negativas
        if (cantidad < 0) {
            System.out.println("  [ERROR] La cantidad no puede ser negativa.");
            return; // salimos del metodo sin agregar nada
        }

        // Validacion 2: no permitir codigos repetidos
        if (existeCodigo(codigo)) {
            System.out.println("  [ERROR] Ya existe un producto con el codigo " + codigo);
            return; // salimos del metodo sin agregar nada
        }

        // Creamos el nuevo nodo (producto)
        Producto nuevo = new Producto(codigo, nombre, cantidad, precio);

        // Caso 1: la lista esta vacia → el nuevo producto ES la cabeza
        if (cabeza == null) {
            cabeza = nuevo;

        // Caso 2: ya hay productos → recorremos hasta el final y lo enlazamos
        } else {
            Producto actual = cabeza; // empezamos desde el primero

            // avanzamos mientras haya un siguiente
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            // cuando salimos del while, "actual" ES el ultimo producto
            actual.siguiente = nuevo; // el ultimo ahora apunta al nuevo
        }

        System.out.println("  [OK] Producto '" + nombre + "' registrado.");
    }

    // =====================================================================
    // METODO 2: BUSCAR Y EDITAR - modifica el stock de un producto
    // =====================================================================
    public void buscarYEditar(int codigo, int cambio) {

        // Buscamos el producto por su codigo
        Producto p = buscar(codigo);

        // Si buscar() devuelve null, significa que no existe
        if (p == null) {
            System.out.println("  [ERROR] No existe un producto con codigo " + codigo);
            return;
        }

        // Calculamos el nuevo stock (cambio puede ser positivo o negativo)
        int nuevaCantidad = p.cantidad + cambio;

        // Validacion: el stock no puede quedar negativo
        if (nuevaCantidad < 0) {
            System.out.println("  [ERROR] Stock insuficiente. Stock actual: " + p.cantidad);
            return;
        }

        // Actualizamos el stock directamente en el nodo
        p.cantidad = nuevaCantidad;
        System.out.println("  [OK] '" + p.nombre + "' ahora tiene " + p.cantidad + " unidades.");
    }

    // =====================================================================
    // METODO 3: MOSTRAR INVENTARIO - lista todos los productos y el total
    // =====================================================================
    public void mostrarInventario() {

        // Si la lista esta vacia, avisamos y salimos
        if (cabeza == null) {
            System.out.println("  El inventario esta vacio.");
            return;
        }

        double valorTotal = 0;      // acumulador del valor total
        Producto actual = cabeza;   // empezamos desde el primer producto

        // Recorremos toda la lista hasta llegar al final (null)
        while (actual != null) {

            double subtotal = actual.cantidad * actual.precio; // subtotal de este producto
            valorTotal = valorTotal + subtotal;                // lo sumamos al total

            // Mostramos la informacion de este producto
            System.out.println("  Codigo   : " + actual.codigo);
            System.out.println("  Nombre   : " + actual.nombre);
            System.out.println("  Cantidad : " + actual.cantidad);
            System.out.println("  Precio   : $" + actual.precio);
            System.out.println("  Subtotal : $" + subtotal);
            System.out.println("  ----------------------------");

            actual = actual.siguiente; // avanzamos al siguiente producto
        }

        // Redondeamos el total a 2 decimales antes de mostrarlo
        double totalRedondeado = Math.round(valorTotal * 100.0) / 100.0;
        System.out.println("  VALOR TOTAL: $" + totalRedondeado);
    }

    // =====================================================================
    // METODO 4: ELIMINAR - quita un producto de la lista por su codigo
    // =====================================================================
    public void eliminar(int codigo) {

        // Si la lista esta vacia, no hay nada que eliminar
        if (cabeza == null) {
            System.out.println("  [ERROR] El inventario esta vacio.");
            return;
        }

        // Caso 1: el producto a eliminar ES la cabeza
        if (cabeza.codigo == codigo) {
            String nombre = cabeza.nombre;
            cabeza = cabeza.siguiente; // la nueva cabeza es el segundo producto
            System.out.println("  [OK] Producto '" + nombre + "' eliminado.");
            return;
        }

        // Caso 2: el producto esta en el medio o al final
        // Necesitamos dos punteros porque no podemos ir hacia atras
        Producto anterior = cabeza;          // se queda un paso atras
        Producto actual   = cabeza.siguiente; // este avanza buscando el codigo

        while (actual != null) {

            if (actual.codigo == codigo) {
                // Encontramos el producto: "saltamos" este nodo
                // el anterior apunta directamente al que esta despues del eliminado
                anterior.siguiente = actual.siguiente;
                System.out.println("  [OK] Producto '" + actual.nombre + "' eliminado.");
                return;
            }

            // Si no es el que buscamos, ambos avanzan un paso
            anterior = actual;
            actual   = actual.siguiente;
        }

        // Si salimos del while sin encontrarlo, no existe
        System.out.println("  [ERROR] No existe un producto con codigo " + codigo);
    }

    // =====================================================================
    // METODOS PRIVADOS DE APOYO (solo los usa esta clase internamente)
    // =====================================================================

    // Recorre la lista buscando un codigo, devuelve el nodo o null si no existe
    private Producto buscar(int codigo) {
        Producto actual = cabeza;

        while (actual != null) {
            if (actual.codigo == codigo) {
                return actual; // lo encontramos, devolvemos el nodo
            }
            actual = actual.siguiente;
        }

        return null; // recorrimos toda la lista y no lo encontramos
    }

    // Devuelve true si ya existe un producto con ese codigo, false si no
    private boolean existeCodigo(int codigo) {
        return buscar(codigo) != null;
    }

    // Devuelve true si la lista esta vacia
    public boolean estaVacio() {
        return cabeza == null;
    }
}