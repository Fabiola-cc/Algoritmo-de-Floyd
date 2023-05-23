import java.util.ArrayList;

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
        FloydWarshall temporalGrafo = encontrar_grafo_a_utilizar(ciudadOrigen);
        int pesoRuta = 0; // Guarda el dato obtenido como ruta más corta
        ArrayList<String> ciudades_intermedias = new ArrayList<>();
        int posicion_fila = readFile.resultList.indexOf(ciudadOrigen);
        int posicion_columna = readFile.resultList.indexOf(ciudadDestino);

        pesoRuta = temporalGrafo.getDistancias()[posicion_fila][posicion_columna];
        boolean continuar = true;
        int pos = posicion_columna;
        while (continuar && pos >= 0) {
            if (!temporalGrafo.getRecorridos()[posicion_fila][pos].equalsIgnoreCase(ciudadDestino)) {// ARREGLAR
                ciudades_intermedias.add(temporalGrafo.getRecorridos()[posicion_fila][pos]);
                pos--;
            } else {
                continuar = false;
            }
        }

        System.out.println("El peso de la ruta es: " + pesoRuta);
        if (!ciudades_intermedias.isEmpty()) {
            ciudades_intermedias.add(0, ciudadOrigen);
            System.out.print("Pasa por las ciudades de: ");
            for (String string : ciudades_intermedias) {
                System.out.print(string + "\t");
            }
        } else {
            System.out.println("La ruta va directamente de " + ciudadOrigen + " a " + ciudadDestino);
        }

    }

    public void calcular_centro() {
        ArrayList<Integer> excentricidades = new ArrayList<>();
        int excentricidad_mínima = 10000;

        for (int i = 0; i < grafoNormal.getDistancias().length; i++) {
            for (Integer value : grafoNormal.getDistancias()[i]) {
                if (value < excentricidad_mínima && value != 0) {
                    excentricidad_mínima = value; // Actualizamos el valor del menor si encontramos un número más
                                                  // pequeño
                }
            }
            excentricidades.add(excentricidad_mínima);
            excentricidad_mínima = 10000;
        }

        int excentricidad_centro = 10000;
        for (Integer value : excentricidades) {
            if (value < excentricidad_centro) {
                excentricidad_centro = value; // Actualizamos el valor del menor si encontramos un número más
                                              // pequeño
            }
        }

        for (int i = 0; i < grafoNormal.getDistancias().length; i++) {

        }
    }

    public void Interrupcion(String ciudadOrigen, String ciudadDestino, String clima, int cambio) {
        int posicion_fila = readFile.resultList.indexOf(ciudadOrigen);
        int posicion_columna = readFile.resultList.indexOf(ciudadDestino);
        Cambiar_clima(ciudadOrigen, clima);
        FloydWarshall porCambiar = encontrar_grafo_a_utilizar(ciudadOrigen);

        porCambiar.getDistancias()[posicion_fila][posicion_columna] = cambio;

    }

    public void Agregar_lazo(String ciudadOrigen, String ciudadDestino, int normal, int lluvia, int nieve,
            int tormenta) {
        int posicion_fila = readFile.resultList.indexOf(ciudadOrigen);
        int posicion_columna = readFile.resultList.indexOf(ciudadDestino);
        grafoNormal.getDistancias()[posicion_fila][posicion_columna] = normal;
        grafoLluvia.getDistancias()[posicion_fila][posicion_columna] = lluvia;
        grafoNieve.getDistancias()[posicion_fila][posicion_columna] = nieve;
        grafoTormenta.getDistancias()[posicion_fila][posicion_columna] = tormenta;

    }

    public void Cambiar_clima(String Ciudad_a_Cambiar, String nuevoClima) {
        for (Ciudad c : readFile.cities) { // Buscar el dato de clima para utilizar
            if (c.getNombre().equalsIgnoreCase(Ciudad_a_Cambiar)) {
                c.setClima_actual(nuevoClima);
            }
        }
    }

    public FloydWarshall encontrar_grafo_a_utilizar(String ciudadOrigen) {
        String clima = "";
        FloydWarshall temporalGrafo = new FloydWarshall(null, null, 0);

        for (Ciudad c : readFile.cities) { // Buscar el dato de clima para utilizar
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
        return temporalGrafo;
    }

}
