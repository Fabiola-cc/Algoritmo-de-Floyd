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
