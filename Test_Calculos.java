import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class Test_Calculos {
        Calculos c = new Calculos();

        @Test
        public void Test_CalcularCentro() {

                ArrayList<String> resp = new ArrayList<>();
                resp.add("BuenosAires");
                resp.add("Lima");

                assertEquals(resp, c.calcular_centro(c.grafoNormal));

        }

        @Test
        public void Test_Interrupciones() {
                c.Interrupcion("Lima", "Quito", "lluvia", 30);
                assertEquals(30, c.grafoLluvia.getDistancias()[2][3]);
        }

        @Test
        public void Test_AgregarLazo() {
                c.Agregar_lazo("SaoPaulo", "Quito", 10, 20, 30, 40);
                assertEquals(10, c.grafoNormal.getDistancias()[1][3]);
                assertEquals(20, c.grafoLluvia.getDistancias()[1][3]);
                assertEquals(30, c.grafoNieve.getDistancias()[1][3]);
                assertEquals(40, c.grafoTormenta.getDistancias()[1][3]);
        }
}
