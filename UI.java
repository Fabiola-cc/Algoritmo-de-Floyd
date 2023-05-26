/**
 * @author
 * Maria José Villafuerte 22129
 * Fabiola Contreras 22787
 * @category Ejercicio #3
 * Propósito: Crear un programa que de las mejores direcciones con un grado de Floyd
 * @date 26/05/2023
 */
import java.util.Scanner;

public class UI {
    static Scanner sc = new Scanner(System.in);
    static Calculos cal = new Calculos();

    public static void main(String[] args) {
        System.out.println("\n¡Bienvenidos a HOJA DE DATOS 10, GRAFO DE BÚSQUEDA");
        boolean salir = false;
        while (!salir) {
            // display menu and get user input
            System.out.println("\nQue deseas hacer?");
            System.out.println("\t1. Mostrar ruta");
            System.out.println("\t2. Calcular centro");
            System.out.println("\t3. Agregar interrupción");
            System.out.println("\t4. Agregar Lazo");
            System.out.println("\t5. Cambiar clima");
            System.out.println("\t6. Salir");
            cal.Ciudades_disponibles();
            System.out.println("Por favor usa solo las ciudades y climas dados anteriormente");
            int opcion_de_menu = sc.nextInt();
            switch (opcion_de_menu) {
                case 1:
                    System.out.println("\tIndica la cuidad de inicio");
                    String ciudad_origen = sc.next();
                    System.out.println("\tIndica la cuidad de destino");
                    String ciudad_destino = sc.next();
                    cal.mostrar_ruta(ciudad_origen,ciudad_destino);
                    break;
                case 2:
                    System.out.println("\tIndica la cuidad de inicio");
                    String ciudad_origen1 = sc.next();
                    cal.calcular_centro(cal.encontrar_grafo_a_utilizar(ciudad_origen1));
                    break;
                case 3:
                    System.out.println("\tIndica la cuidad de inicio");
                    String ciudad_origen2 = sc.next();
                    System.out.println("\tIndica la cuidad de destino");
                    String ciudad_destino2 = sc.next();
                    System.out.println("\tIndica el clima en el que se encuentra");
                    String clima_temp1 = sc.next();
                    System.out.println("\tIndica la estumació de tiempo que se llevará a cabo en la interrupción");
                    int tiempo_aumento_temp = sc.nextInt();
                    cal.Interrupcion(ciudad_origen2,ciudad_destino2,clima_temp1,tiempo_aumento_temp);
                    break;
                case 4:
                    System.out.println("\tIndica la cuidad de inicio");
                    String ciudad_origen3 = sc.next();
                    System.out.println("\tIndica la cuidad de destino");
                    String ciudad_destino3 = sc.next();
                    System.out.println("\tEn cuanto tiempo se llega esa ciudad con un clima normal");
                    int tiempo_normal_temp = sc.nextInt();
                    System.out.println("\tEn cuanto tiempo se llega esa ciudad con un clima lluvioso ");
                    int tiempo_lluvioso_temp = sc.nextInt();
                    System.out.println("\tEn cuanto tiempo se llega esa ciudad con un clima nevoso");
                    int tiempo_nevoso_temp = sc.nextInt();
                    System.out.println("\tEn cuanto tiempo se llega esa ciudad con una tormenta");
                    int tiempo_tomenta_temp = sc.nextInt();
                    cal.Agregar_lazo(ciudad_origen3,ciudad_destino3,tiempo_normal_temp,tiempo_lluvioso_temp,tiempo_nevoso_temp,tiempo_tomenta_temp);
                    break;
                case 5:
                    System.out.println("\tQue cuidad ha cambiado de clima?");
                    String ciudad_cambio = sc.next();
                    System.out.println("\tQuie clima tiene ahora? normal, lluvia, nieve, tormenta");
                    String clima_temp = sc.next();
                    cal.Cambiar_clima(ciudad_cambio,clima_temp);
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }

    }
}
