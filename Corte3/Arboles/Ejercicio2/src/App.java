/*2. Catálogo Digital (Biblioteca Universitaria)
La biblioteca de la universidad organiza su colección de libros en un BST usando el ISBN como clave única. Cada vez que ingresa un libro nuevo, se inserta automáticamente en el árbol. El sistema debe poder confirmar si un ISBN ya existe antes de registrar un nuevo ejemplar y listar toda la colección ordenada por ISBN.

La Clase Libro (Nodo): Debe contener isbn (long, clave del BST), titulo (String), autor (String) y anioPublicacion (int).
El Problema: Al final del semestre, el bibliotecario necesita imprimir el inventario completo ordenado de menor a mayor por ISBN para enviarlo a la sede central. También debe verificar si un ISBN específico ya existe en el catálogo antes de registrar una donación.
Reto: Implementa el método imprimirCatalogo() usando el recorrido InOrden para listar todos los libros en orden ascendente de ISBN (imprime isbn - titulo - autor). Además, implementa existeISBN(long isbn) que retorne true si el libro ya está registrado y false en caso contrario.
 */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArbolBiblioteca arbol = new ArbolBiblioteca();
        Scanner scanner = new Scanner(System.in);

        arbol.insertar(9780061969492L, "El nombre del viento",     "Patrick Rothfuss",  2007);
        arbol.insertar(9780141439518L, "Orgullo y prejuicio",      "Jane Austen",        1813);
        arbol.insertar(9780743273565L, "El gran Gatsby",           "F. Scott Fitzgerald",1925);
        arbol.insertar(9780451524935L, "1984",                     "George Orwell",      1949);
        arbol.insertar(9780060935467L, "Sapiens",                  "Yuval Noah Harari",  2011);
        arbol.insertar(9780316769174L, "El guardián entre el centeno", "J.D. Salinger",  1951);
        arbol.insertar(9780385490818L, "El túnel",                 "Ernesto Sabato",     1948);
        arbol.insertar(9780307474728L, "Cien años de soledad",     "Gabriel Garcia Marquez", 1967);

        int opcion;
        do {
            System.out.println("\n--- CATALOGO DIGITAL BIBLIOTECA UNIVERSITARIA (BST) ---");
            System.out.println("1. Imprimir catalogo ordenado por ISBN");
            System.out.println("2. Ver arbol en InOrden");
            System.out.println("3. Ver arbol en PreOrden");
            System.out.println("4. Insertar nuevo libro");
            System.out.println("5. Verificar si existe un ISBN");
            System.out.println("6. Propiedades del arbol (altura y hojas)");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println();
                    arbol.imprimirCatalogo();
                    break;

                case 2:
                    System.out.println();
                    arbol.inOrden();
                    break;

                case 3:
                    System.out.println();
                    arbol.preOrden();
                    break;

                case 4:
                    System.out.print("ISBN: ");
                    long isbn = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Anio de publicacion: ");
                    int anio = scanner.nextInt();
                    scanner.nextLine();
                    arbol.insertar(isbn, titulo, autor, anio);
                    System.out.println("Libro insertado correctamente.");
                    break;

                case 5:
                    System.out.print("Ingrese el ISBN a verificar: ");
                    long busqueda = scanner.nextLong();
                    scanner.nextLine();
                    if (arbol.existeISBN(busqueda))
                        System.out.println("El ISBN " + busqueda + " ya existe en el catalogo.");
                    else
                        System.out.println("El ISBN " + busqueda + " no esta registrado. Puede insertar el libro.");
                    break;

                case 6:
                    System.out.println("Altura del arbol:  " + arbol.altura());
                    System.out.println("Nodos hoja:        " + arbol.contarHojas());
                    break;

                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 7);

        scanner.close();
    }
}
