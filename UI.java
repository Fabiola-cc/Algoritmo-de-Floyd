import java.util.Scanner;

public class UI {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n¡Bienvenidos a un diccionario inglés - español - francés!");
        boolean salir = false;
        while (!salir) {
            // display menu and get user input
            System.out.println("\nQue deseas hacer?");
            System.out.println("\t1. Mostrar ruta");
            System.out.println("\t2. Calcular centro");
            System.out.println("\t3. Modificar grafo");
            System.out.println("\t4. Agregar interrupción");
            System.out.println("\t5. Cambiar clima");
            System.out.println("\t6. Salir");
            int opcion_de_menu = sc.nextInt();
            switch (opcion_de_menu) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

    }
}
