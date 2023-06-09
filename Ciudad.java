/**
 * @author
 * Maria José Villafuerte 22129
 * Fabiola Contreras 22787
 * @category Ejercicio #3
 * Propósito: Crear un programa que de las mejores direcciones con un grado de Floyd
 * @date 26/05/2023
 */
/*
 * Clase para mantener el control de los climas en las diversas ciudades dadas
 */
public class Ciudad {
    private String nombre;
    private String clima_actual;

    Ciudad(String _nombre, String _clima) {
        nombre = _nombre;
        clima_actual = _clima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClima_actual() {
        return clima_actual;
    }

    public void setClima_actual(String clima_actual) {
        this.clima_actual = clima_actual;
    }

}
