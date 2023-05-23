import java.util.ArrayList;

public class main2 {
    static readFile read = new readFile();

    public static void main(String[] args) {
        ArrayList<FloydWarshall> grafos = readFile.guardarDatos();
        FloydWarshall grafoNormal = grafos.get(0);
        FloydWarshall grafoLluvia = grafos.get(1);
        FloydWarshall grafoNieve = grafos.get(2);
        FloydWarshall grafoTormenta = grafos.get(3);

    }
}
