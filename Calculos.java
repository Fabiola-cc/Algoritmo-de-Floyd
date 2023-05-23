import java.util.ArrayList;
import java.util.List;

public class Calculos {
    FloydWarshall grafoNormal;
    FloydWarshall grafoLluvia;
    FloydWarshall grafoNieve;
    FloydWarshall grafoTormenta;

    Calculos() {
        ArrayList<FloydWarshall> grafos = readFile.guardarDatos();
        grafoNormal = grafos.get(0);
        grafoLluvia = grafos.get(1);
        grafoNieve = grafos.get(2);
        grafoTormenta = grafos.get(3);
    }

    public void mostrar_ruta(String ciudadOrigen, String ciudadDestino) {
        String clima = "";
        int pesoRuta = 0;
        FloydWarshall temporalGrafo = new FloydWarshall(null, null, 0);
        ArrayList<String> ciudades_intermedias = new ArrayList<>();
        for (Ciudad c : readFile.cities) {
            if (c.getNombre().equalsIgnoreCase(ciudadOrigen)) {
                clima = c.getClima_actual();
            }
        }
        // Mismo proceso para las diversas posibilidades de clima
        if (clima.equalsIgnoreCase("Normal")) {
            temporalGrafo = grafoNormal;
        } else if (clima.equalsIgnoreCase("lluvia")) {
            temporalGrafo = grafoLluvia;
        } else if (clima.equalsIgnoreCase("nieve")) {
            temporalGrafo = grafoNieve;
        } else if (clima.equalsIgnoreCase("tormenta")) {
            temporalGrafo = grafoTormenta;
        }
        temporalGrafo.CalcularRutas();
        int posicion_fila = readFile.resultList.indexOf(ciudadOrigen);
        int posicion_columna = readFile.resultList.indexOf(ciudadDestino);

        pesoRuta = temporalGrafo.getDistancias()[posicion_fila][posicion_columna];

        for (int i = 0; i < temporalGrafo.getRecorridos().length; i++) {
            boolean continuar = true;
            int pos = posicion_columna;
            while (continuar || pos == i) {
                if (!temporalGrafo.getRecorridos()[posicion_fila][pos].equalsIgnoreCase(ciudadDestino)) {// ARREGLAR
                    ciudades_intermedias.add(temporalGrafo.getRecorridos()[posicion_fila][pos]);
                    pos--;
                } else {
                    continuar = false;
                }
            }

        }

        if (!ciudades_intermedias.isEmpty()) {
            ciudades_intermedias.add(0, ciudadOrigen);
            System.out.println("El peso de la ruta es: " + pesoRuta);
            System.out.print("Pasa, en orden, por las ciudades de: ");
            for (String string : ciudades_intermedias) {
                System.out.print(string + "\t");
            }
        } else {
            System.out.println("No existe una ruta entre estas ciudades");
        }

    }

    public void calcular_centro() {

    }

    public void modificar_grafo() {

    }

    public void Interrupcion() {

    }

    public void Agregar_lazo() {

    }

    public void Cambiar_clima() {

    }

    public int[] calcular_posicion(ArrayList<String> lista_a_usar) {
        int Matrix_size = readFile.resultList.size();
        int posicion_fila = 0;
        int posicion_columna = 0;
        // Dirección para grafo con tiempo normal
        for (String ciudades : lista_a_usar) { // Por cada listado que indica una dirección
            if (ciudades.equalsIgnoreCase(ciudades)) {

            }

        }
        int[] temp = { posicion_fila, posicion_columna };
        return temp;
    }

}
