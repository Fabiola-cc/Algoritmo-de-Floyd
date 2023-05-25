import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * @author
 *         Maria José Villafuerte
 *         Fabiola Contreras
 * @category Hoja de trabajo #10
 *           Propósito: Leer el arhivo txt con los datos necesarios del grafo
 * @date 23/05/2023
 */

public class readFile {

    /**
     * Metodo que lee el documento con los datos para cada grafo
     * 
     * @param fpath Mensaje de lugar donde se encuentra el archivo datos.txt
     * @return data String
     */
    private static ArrayList<String> _readfile(String fpath) {

        String data = "";
        ArrayList<String> total = new ArrayList<>();

        try {

            File myObj = new File(fpath);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                data = myReader.nextLine();
                String[] splittedData = data.split(" ");
                for (String word : splittedData) {
                    total.add(word);
                }

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return total;
    }

    static ArrayList<String> total_ciudades = new ArrayList<>();
    static ArrayList<String> ciudades = new ArrayList<>();
    static ArrayList<String> resultList = new ArrayList<>();
    static ArrayList<Ciudad> cities = new ArrayList<>();

    private static void datos_de_ciudades() {
        total_ciudades = _readfile("C:\\Users\\Fabi\\Documents\\GitHub\\Algoritmo-de-Floyd\\logistica.txt");
        int vueltas = total_ciudades.size() / 6;
        int cambios = 0;
        while (vueltas != 0) {
            ciudades.add(total_ciudades.get(cambios));
            ciudades.add(total_ciudades.get(cambios + 1));
            cambios = cambios + 6;
            vueltas--;
        }
        for (String element : ciudades) {
            boolean isDuplicate = false;
            for (String result : resultList) {
                if (element.equals(result)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                resultList.add(element);
            }
        }

        for (String nombre : resultList) {
            cities.add(new Ciudad(nombre, "normal"));
        }
    }

    static ArrayList<List<String>> lista_tiempoNormal = new ArrayList<>();
    static ArrayList<List<String>> lista_tiempoLluvia = new ArrayList<>();
    static ArrayList<List<String>> lista_tiempoNieve = new ArrayList<>();
    static ArrayList<List<String>> lista_tiempoTormenta = new ArrayList<>();

    private static void datos_de_lazos() {
        int vueltas = total_ciudades.size() / 6;
        int cambios = 0;
        while (vueltas != 0) {
            String cuidada_de_salida = total_ciudades.get(cambios);
            String cuidada_de_llegada = total_ciudades.get(cambios + 1);
            String lazo_timeponormal = total_ciudades.get(cambios + 2);
            String lazo_tiempoLluvia = total_ciudades.get(cambios + 3);
            String lazo_tiempoNieve = total_ciudades.get(cambios + 4);
            String lazo_tiempoTormenta = total_ciudades.get(cambios + 5);

            // Guarda en array de lista de 3
            lista_tiempoNormal.add(List.of(cuidada_de_salida, cuidada_de_llegada, lazo_timeponormal));
            lista_tiempoLluvia.add(List.of(cuidada_de_salida, cuidada_de_llegada, lazo_tiempoLluvia));
            lista_tiempoNieve.add(List.of(cuidada_de_salida, cuidada_de_llegada, lazo_tiempoNieve));
            lista_tiempoTormenta.add(List.of(cuidada_de_salida, cuidada_de_llegada, lazo_tiempoTormenta));

            cambios = cambios + 6;
            vueltas--;
        }
    }

    public static ArrayList<FloydWarshall> guardarDatos() {
        datos_de_ciudades();
        datos_de_lazos();
        int Matrix_size = resultList.size();
        int[][] dNormal = darValoresA_recorrido(lista_tiempoNormal, Matrix_size);
        int[][] dLluvia = darValoresA_recorrido(lista_tiempoLluvia, Matrix_size);
        int[][] dNieve = darValoresA_recorrido(lista_tiempoNieve, Matrix_size);
        int[][] dTormenta = darValoresA_recorrido(lista_tiempoTormenta, Matrix_size);
        String[][] recorridos = new String[Matrix_size][Matrix_size];

        for (int i = 0; i < Matrix_size; i++) { // Creación de matriz de recorridos inicial
            for (int j = 0; j < Matrix_size; j++) {
                recorridos[i][j] = resultList.get(j);
            }
        }

        FloydWarshall grafoNormal = new FloydWarshall(dNormal, recorridos, Matrix_size);
        FloydWarshall grafolluvia = new FloydWarshall(dLluvia, recorridos, Matrix_size);
        FloydWarshall grafoNieve = new FloydWarshall(dNieve, recorridos, Matrix_size);
        FloydWarshall grafoTormenta = new FloydWarshall(dTormenta, recorridos, Matrix_size);

        ArrayList<FloydWarshall> result = new ArrayList<>();
        result.add(grafoNormal);
        result.add(grafolluvia);
        result.add(grafoNieve);
        result.add(grafoTormenta);
        return result;
    }

    static int[][] darValoresA_recorrido(ArrayList<List<String>> lista_a_usar, int Matrix_size) {
        int[][] results = new int[Matrix_size][Matrix_size];
        // Dirección para grafo con tiempo normal
        for (List<String> lista : lista_a_usar) { // Por cada listado que indica una dirección
            int posicion_fila = 0;
            int posicion_columna = 0;
            for (int j = 0; j < Matrix_size; j++) {
                if (lista.get(0).equals(resultList.get(j))) {
                    posicion_fila = j;
                }
                if (lista.get(1).equals(resultList.get(j))) {
                    posicion_columna = j;
                }
            }
            results[posicion_fila][posicion_columna] = Integer.valueOf(lista.get(2));
        }
        for (int i = 0; i < results.length; i++) { // Verificar que todas las casillas tengan información
            for (int j = 0; j < results[i].length; j++) {
                if (i != j) { // Las casillas que van de una ciudad a la misma quedan como 0
                    if (results[i][j] == 0) {
                        results[i][j] = 100000;
                    }
                }
            }
        }
        return results;
    }
}
