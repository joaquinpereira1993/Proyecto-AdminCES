package projectJava;

public class Tester extends Usuario {

    private String tipoTester;

    public Tester(String nombre, String apellido, String email, String pais, String contrasena, String tipoTester) {
        super(nombre, apellido, email, pais, contrasena);
        this.tipoTester = tipoTester;
    }

    public String getTipoTester() {
        return tipoTester;
    }

    public void setTipoTester(String tipoTester) {
        this.tipoTester = tipoTester;
    }
}