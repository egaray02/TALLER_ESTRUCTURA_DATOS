/*4. Torneo de Ajedrez (Sistema de Rankings ELO)
Una plataforma de ajedrez en línea gestiona el ranking ELO de sus jugadores en un BST. A medida que los jugadores completan partidas, su ELO se actualiza y se reinsertan en el árbol. El director del torneo necesita obtener la lista de jugadores que clasifican a la siguiente ronda, es decir, todos aquellos cuyo ELO se encuentre dentro de un rango específico.

La Clase Jugador (Nodo): Debe contener elo (int, clave del BST), nombreUsuario (String), pais (String) y partidasJugadas (int).
El Problema: Para la ronda de clasificación, el director fija un rango mínimo y máximo de ELO (por ejemplo, entre 1800 y 2200). Todos los jugadores en ese rango deben ser listados de menor a mayor ELO para armar los emparejamientos.
Reto: Implementa el método listarEnRango(int eloMin, int eloMax) que recorra el árbol e imprima los datos de todos los jugadores cuyo elo esté dentro del rango [eloMin, eloMax] en orden ascendente. Usa la propiedad del BST para podar: si el nodo actual es menor que eloMin, solo explora el subárbol derecho; si es mayor que eloMax, solo explora el izquierdo. */
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArbolTorneo arbol = new ArbolTorneo();
        Scanner scanner = new Scanner(System.in);

        arbol.insertar(2450, "MagnusC",     "Noruega",     1240);
        arbol.insertar(1950, "QueenGambit", "Estados Unidos", 870);
        arbol.insertar(2100, "DragonFire",  "Rusia",       995);
        arbol.insertar(1750, "PawnStorm",   "Argentina",   630);
        arbol.insertar(2300, "SilentKing",  "China",       1105);
        arbol.insertar(1850, "RookRush",    "Colombia",    740);
        arbol.insertar(2600, "DeepMind99",  "Alemania",    1580);
        arbol.insertar(2050, "KnightRider", "Francia",     910);

        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE RANKING ELO TORNEO DE AJEDREZ (BST) ---");
            System.out.println("1. Ver jugadores ordenados por ELO (InOrden)");
            System.out.println("2. Ver arbol en PreOrden");
            System.out.println("3. Insertar nuevo jugador");
            System.out.println("4. Buscar jugador por ELO");
            System.out.println("5. Listar jugadores en rango de ELO");
            System.out.println("6. Propiedades del arbol (altura y hojas)");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println();
                    arbol.inOrden();
                    break;

                case 2:
                    System.out.println();
                    arbol.preOrden();
                    break;

                case 3:
                    System.out.print("ELO: ");
                    int elo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre de usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.print("Pais: ");
                    String pais = scanner.nextLine();
                    System.out.print("Partidas jugadas: ");
                    int partidas = scanner.nextInt();
                    scanner.nextLine();
                    arbol.insertar(elo, usuario, pais, partidas);
                    System.out.println("Jugador insertado correctamente.");
                    break;

                case 4:
                    System.out.print("ELO a buscar: ");
                    int busqueda = scanner.nextInt();
                    scanner.nextLine();
                    Jugador encontrado = arbol.buscar(busqueda);
                    if (encontrado != null)
                        System.out.println("Jugador encontrado: " + encontrado);
                    else
                        System.out.println("No se encontró ningun jugador con ELO " + busqueda + ".");
                    break;

                case 5:
                    System.out.print("ELO minimo: ");
                    int eloMin = scanner.nextInt();
                    System.out.print("ELO maximo: ");
                    int eloMax = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println();
                    arbol.listarEnRango(eloMin, eloMax);
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
