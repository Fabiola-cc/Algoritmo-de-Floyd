import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

public class Test_GuardarDatos {
        String[][] matriz_recorridos = { { "BuenosAires", "SaoPaulo", "Lima", "Quito" },
                        { "BuenosAires", "SaoPaulo", "Lima", "Quito" },
                        { "BuenosAires", "SaoPaulo", "Lima", "Quito" },
                        { "BuenosAires", "SaoPaulo", "Lima", "Quito" } };

        int[][] matriz_normal = { { 0, 10, 15, 100000 },
                        { 100000, 0, 100000, 100000 },
                        { 100000, 100000, 0, 10 },
                        { 100000, 100000, 100000, 0 } };
        int[][] matriz_lluvia = { { 0, 15, 20, 100000 },
                        { 100000, 0, 100000, 100000 },
                        { 100000, 100000, 0, 12 },
                        { 100000, 100000, 100000, 0 } };
        int[][] matriz_nieve = { { 0, 20, 30, 100000 },
                        { 100000, 0, 100000, 100000 },
                        { 100000, 100000, 0, 15 },
                        { 100000, 100000, 100000, 0 } };
        int[][] matriz_tormenta = { { 0, 50, 70, 100000 },
                        { 100000, 0, 100000, 100000 },
                        { 100000, 100000, 0, 20 },
                        { 100000, 100000, 100000, 0 } };

        @Test
        public void Test_GuardaDatos() {
                ArrayList<FloydWarshall> grafos = readFile.guardarDatos();
                FloydWarshall grafoNormal = grafos.get(0);
                FloydWarshall grafoLluvia = grafos.get(1);
                FloydWarshall grafoNieve = grafos.get(2);
                FloydWarshall grafoTormenta = grafos.get(3);

                assertArrayEquals(matriz_recorridos, grafoLluvia.getRecorridos());
                assertArrayEquals(matriz_normal, grafoNormal.getDistancias());
                assertArrayEquals(matriz_lluvia, grafoLluvia.getDistancias());
                assertArrayEquals(matriz_nieve, grafoNieve.getDistancias());
                assertArrayEquals(matriz_tormenta, grafoTormenta.getDistancias());
        }

}
