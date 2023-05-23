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

    public void mostrar_ruta() {

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

}
