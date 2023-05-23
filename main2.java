import java.util.List;

public class main2 {
    static readFile read = new readFile();

    public static void main(String[] args) {
        read.datos_de_ciudades();
        read.datos_de_lazos();

        
        for (int i = 0; i < read.lista_tiempoLluvia.size(); i++) {
            String element1 = read.lista_tiempoLluvia.get(0).get(0);
            String element2 = read.lista_tiempoLluvia.get(1).get(1);
            String element3 = read.lista_tiempoLluvia.get(2).get(2);

            System.out.println(element1);
            System.out.println(element2);
            System.out.println(element3);

    
        }

        System.out.println(read.lista_tiempoLluvia);
        System.out.println(read.lista_tiempoNieve);
        System.out.println(read.lista_tiempoNormal);
        System.out.println(read.lista_tiempoTormenta);

    }
}
