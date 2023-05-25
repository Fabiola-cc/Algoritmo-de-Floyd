public class main2 {
    static readFile read = new readFile();

    public static void main(String[] args) {
        Calculos c = new Calculos();
        c.mostrar_ruta("BuenosAires", "Lima");
        c.calcular_centro(c.grafoNormal);
    }
}
