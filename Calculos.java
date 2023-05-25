import java.util.ArrayList;

public class Calculos {
    FloydWarshall grafoNormal;
    FloydWarshall grafoLluvia;
    FloydWarshall grafoNieve;
    FloydWarshall grafoTormenta;

    /**
     * Constructor de clase, manda a llamar a la función adecuada para guardar los
     * datos presentados en el archivo y así tenerlos para iniciar con cálculos.
     */
    Calculos() {
        ArrayList<FloydWarshall> grafos = readFile.guardarDatos();
        grafoNormal = grafos.get(0);
        grafoLluvia = grafos.get(1);
        grafoNieve = grafos.get(2);
        grafoTormenta = grafos.get(3);
    }

    /**
     * Muestra al usuario la mejor ruta registrada para ir de una ciudad a otra
     * 
     * @param ciudadOrigen
     * @param ciudadDestino
     */
    public void mostrar_ruta(String ciudadOrigen, String ciudadDestino) {
        FloydWarshall temporalGrafo = encontrar_grafo_a_utilizar(ciudadOrigen); // Utiliza los datos de un grafo ya
                                                                                // guardado, según el clima en la ciudad
        int pesoRuta = 0; // Guarda el dato obtenido como ruta más corta
        ArrayList<String> ciudades_intermedias = new ArrayList<>(); // Permitirá guardar las ciudades que por las que se
                                                                    // pasa a través del viaje
        int posicion_fila = readFile.resultList.indexOf(ciudadOrigen); // Según la lista ya establecida de ciudades se
                                                                       // busca el índice representativo de la ciudad
                                                                       // para conocer la ubicación del recorrido en la
                                                                       // matriz
        int posicion_columna = readFile.resultList.indexOf(ciudadDestino);

        pesoRuta = temporalGrafo.getDistancias()[posicion_fila][posicion_columna]; // Guarda el dato de peso, de acuerdo
                                                                                   // a los índices antes encontrados
        int pos = posicion_columna; // Permite la movilidad a través de la matriz, a partir de la columna dada
        int max = readFile.resultList.size() - 1;
        while (0 <= pos && pos >= max) { // Verifica las ciudades por las que se pasa durante la ruta
            String ciudad_actual = readFile.resultList.get(pos); // Al obtener un recorrido en la misma ciudad significa
                                                                 // que no atraviesa más ciudades y el loop puede
                                                                 // detenerse
            if (!temporalGrafo.getRecorridos()[posicion_fila][pos].equalsIgnoreCase(ciudad_actual)) {
                ciudades_intermedias.add(temporalGrafo.getRecorridos()[posicion_fila][pos]);
                pos = readFile.resultList.indexOf(temporalGrafo.getRecorridos()[posicion_fila][pos]); // Se mueve a la
                                                                                                      // columna con
                                                                                                      // información del
                                                                                                      // recorrido hasta
                                                                                                      // la ciudad
            } else {
                pos = max + 1; // Termina el ciclo
            }
        }

        if (pesoRuta >= 100000) { // Al tener un dato así de grande no se puede especificar una ruta, se toma como
                                  // infinito
            System.out.println("No hay una ruta registrada desde " + ciudadOrigen + " a " + ciudadDestino);
        } else {
            System.out.println("El peso de la ruta es: " + pesoRuta);
            if (!ciudades_intermedias.isEmpty()) { // Si hay ciudades intermedias las muestra al usuario
                ciudades_intermedias.add(0, ciudadOrigen);
                System.out.print("Pasa por las ciudades de: ");
                for (String string : ciudades_intermedias) {
                    System.out.print(string + "\t");
                }
            } else { // No existen ciudades intermedias durante la ruta
                System.out.println("La ruta va directamente de " + ciudadOrigen + " a " + ciudadDestino);
            }
        }

    }

    /**
     * Calcula el centro del grafo especificado, haciendo uso de los datos de
     * excentricidades
     * 
     * @param grafo_a_usar Es necesario especificar el clima del grafo para el cual
     *                     se quiere el centro.
     * @return arrayList<String> principalmente usado para pruebas unitarias
     */
    public ArrayList<String> calcular_centro(FloydWarshall grafo_a_usar) {
        grafo_a_usar.CalcularRutas(); // Asegura que las rutas registradas sean las menores
        ArrayList<int[]> excentricidades = new ArrayList<>(); // Guarda los datos de excentricidades de cada vértice y
                                                              // la ciudad de la que se habla
        int excentricidad_mínima = 1000000;

        // Compara los datos en distancias para cada vértice para conocer sus
        // excentricidades
        for (int i = 0; i < grafo_a_usar.getDistancias().length; i++) {
            int[] datos_excentricidad = new int[3];
            for (int j = 0; j < grafo_a_usar.getDistancias()[i].length; j++) {
                int value = grafo_a_usar.getDistancias()[i][j]; // Valor actual en la posición de matriz
                if (value < excentricidad_mínima && value != 0) {
                    excentricidad_mínima = value; // Actualizamos el valor del menor si encontramos un número más
                                                  // pequeño
                    datos_excentricidad[0] = excentricidad_mínima; // Dato mínimo obtenido
                    datos_excentricidad[1] = i; // Ciudad de donde va
                    datos_excentricidad[2] = j; // Ciudad a donde llega
                }
            }
            excentricidades.add(datos_excentricidad);
            excentricidad_mínima = 1000000;
        }

        ArrayList<int[]> excentricidad_centro = new ArrayList<>(); // Guarda todas las ciudades con un valor igual de
                                                                   // excentricidad, relacionadas al centro
        for (int[] value : excentricidades) {
            if (excentricidad_centro.isEmpty()) {
                excentricidad_centro.add(value); // Agrega un dato al inicio del ciclo
            } else {
                for (int[] is : excentricidad_centro) { // Revisa cada dato dento del array de excentricidades
                                                        // consideradas
                    if (value[0] <= is[0]) { // Si los datos son iguales se guardan ambos
                        if (value[0] < is[0]) { // Si el valor de 'value' es menor, se elimina el actual guardado
                            excentricidad_centro.remove(is);
                        }
                        excentricidad_centro.add(value); // Actualizamos el valor del menor si encontramos un número más
                                                         // pequeño
                        break;
                    }
                }
            }
        }

        System.out.print("Ciudad(es) en el centro del grafo: ");
        ArrayList<String> central_cities = new ArrayList<>();
        for (int[] is : excentricidad_centro) { // Imprime el nombre de cada ciudad céntrica
            System.out.print(readFile.resultList.get(is[1]));
            central_cities.add(readFile.resultList.get(is[1])); // Guarda el nombre en array para pruebas
            if (excentricidad_centro.indexOf(is) != excentricidad_centro.size() - 1) { // añade una coma entre ciudades,
                                                                                       // sin hacerlo en la última
                System.out.print(", ");
            }

        }
        return central_cities;
    }

    /**
     * Registra interrupciones, cambios en el lazo, de una ciudad a otra
     * 
     * @param ciudadOrigen
     * @param ciudadDestino
     * @param clima         ¿en qué clima está ocurriendo la interrupción?
     * @param aumento       ¿cual es el aumento de tiempo por la interrupción?
     */
    public void Interrupcion(String ciudadOrigen, String ciudadDestino, String clima, int aumento) {
        // Busca los datos de fila y columna para cambiar la información en la matriz
        // dada
        int posicion_fila = readFile.resultList.indexOf(ciudadOrigen);
        int posicion_columna = readFile.resultList.indexOf(ciudadDestino);
        Cambiar_clima(ciudadOrigen, clima); // Cambia el clima actual de la ciudad originaria de la interrupción
        FloydWarshall porCambiar = encontrar_grafo_a_utilizar(ciudadOrigen);

        porCambiar.getDistancias()[posicion_fila][posicion_columna] += aumento; // Cambia el dato de viaje registrado

    }

    /**
     * Crea un lazo, cambiando el valor del peso, entre ciudades para sus diversos
     * climas
     * 
     * @param ciudadOrigen
     * @param ciudadDestino
     * @param normal
     * @param lluvia
     * @param nieve
     * @param tormenta
     */
    public void Agregar_lazo(String ciudadOrigen, String ciudadDestino, int normal, int lluvia, int nieve,
            int tormenta) {
        // Busca los datos de fila y columna para cambiar la información en la matriz
        int posicion_fila = readFile.resultList.indexOf(ciudadOrigen);
        int posicion_columna = readFile.resultList.indexOf(ciudadDestino);
        // Agrega los datos en los diversos climas obtenidos
        grafoNormal.getDistancias()[posicion_fila][posicion_columna] = normal;
        grafoLluvia.getDistancias()[posicion_fila][posicion_columna] = lluvia;
        grafoNieve.getDistancias()[posicion_fila][posicion_columna] = nieve;
        grafoTormenta.getDistancias()[posicion_fila][posicion_columna] = tormenta;

    }

    /**
     * Cambia el dato de clima para la ciudad especificada
     * 
     * @param Ciudad_a_Cambiar
     * @param nuevoClima
     */
    public void Cambiar_clima(String Ciudad_a_Cambiar, String nuevoClima) {
        for (Ciudad c : readFile.cities) { // Buscar el dato de ciudad para utilizar
            if (c.getNombre().equalsIgnoreCase(Ciudad_a_Cambiar)) {
                c.setClima_actual(nuevoClima);
            }
        }
    }

    /**
     * Permite reconocer que datos usar, psegpun el clima registrado de la ciudad de
     * origen
     * 
     * @param ciudadOrigen
     * @return
     */
    private FloydWarshall encontrar_grafo_a_utilizar(String ciudadOrigen) {
        String clima = "";
        FloydWarshall temporalGrafo = new FloydWarshall(null, null, 0);

        for (Ciudad c : readFile.cities) { // Buscar el dato de clima para utilizar
            if (c.getNombre().equalsIgnoreCase(ciudadOrigen)) {
                clima = c.getClima_actual();
            }
        }
        // Dependiendo el clima devuelve alguno de los grafos
        if (clima.equalsIgnoreCase("Normal")) {
            temporalGrafo = grafoNormal;
        } else if (clima.equalsIgnoreCase("lluvia")) {
            temporalGrafo = grafoLluvia;
        } else if (clima.equalsIgnoreCase("nieve")) {
            temporalGrafo = grafoNieve;
        } else if (clima.equalsIgnoreCase("tormenta")) {
            temporalGrafo = grafoTormenta;
        }

        temporalGrafo.CalcularRutas(); // Assegura que las rutas dadas son mínimas
        return temporalGrafo;
    }

}
