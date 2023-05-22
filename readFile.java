import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
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

    public static FloydWarshall guardarDatos(ArrayList<String> informacion) {
        return null;
    }
}
