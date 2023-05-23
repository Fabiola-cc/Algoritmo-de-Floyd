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
    public static ArrayList<String> _readfile(String fpath) {

        String data = "";
        ArrayList<String> total = new ArrayList<>();


        try {

            File myObj = new File(fpath);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {

                data = myReader.nextLine();
                total.add(data);

            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return total;
    }

    public static FloydWarshall guardarDatos() {
        return null;
    }

    
    static ArrayList<String> total_ciudades = new ArrayList<>();
    static ArrayList<String> ciudades = new ArrayList<>();
    static ArrayList<String> resultList = new ArrayList<>();
    


    public static void datos_de_ciudades (){
        total_ciudades = _readfile("src\\logistica.txt");
        int vueltas = total_ciudades.size()/6;
        int cambios = 0;
        if (vueltas != 0){
            ciudades.add(total_ciudades.get(cambios));
            ciudades.add(total_ciudades.get(cambios+1));
            cambios = cambios + 7;
            vueltas --;     
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
       
    }

    static ArrayList<String> total_ciudades2 = new ArrayList<>();
    static ArrayList<List<String>> lista_tiempoNormal = new ArrayList<>();
    static ArrayList<List<String>> lista_tiempoLluvia = new ArrayList<>();
    static ArrayList<List<String>> lista_tiempoNieve = new ArrayList<>();
    static ArrayList<List<String>> lista_tiempoTormenta = new ArrayList<>();

    
    public static void datos_de_lazos (){
        total_ciudades2 = _readfile("src\\logistica.txt");
        int vueltas = total_ciudades2.size()/6;
        int cambios = 0;
        if (vueltas != 0){
            String cuidada_de_salida = total_ciudades2.get(cambios);
            String cuidada_de_llegada = total_ciudades2.get(cambios+1);
            String lazo_timeponormal = total_ciudades2.get(cambios+2);
            String lazo_tiempoLluvia  = total_ciudades2.get(cambios+3);
            String lazo_tiempoNieve   = total_ciudades2.get(cambios+4);
            String lazo_tiempoTormenta  = total_ciudades2.get(cambios+5);


            //Guarda en array de lista de 3
            lista_tiempoNormal.add(List.of(cuidada_de_salida, cuidada_de_llegada, lazo_timeponormal));
            lista_tiempoLluvia.add(List.of(cuidada_de_salida, cuidada_de_llegada, lazo_tiempoLluvia));
            lista_tiempoNieve.add(List.of(cuidada_de_salida, cuidada_de_llegada, lazo_tiempoNieve));
            lista_tiempoTormenta.add(List.of(cuidada_de_salida, cuidada_de_llegada, lazo_tiempoTormenta));


            cambios = cambios + 7;
            vueltas --;     
        }
    }
}
