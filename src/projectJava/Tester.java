package projectJava;

public class Tester extends Usuario {

    private String tipoTester;

    public Tester(String nombre, String apellido, String email, String pais, String contrasena, String tipoTester) {
        super(nombre, apellido, email, pais, contrasena);
        this.tipoTester = TipoTester(tipoTester);
    }

    public String getTipoTester() {
        return tipoTester;
    }

    public void setTipoTester(String tipoTester) {
        this.tipoTester = TipoTester(tipoTester);
    }

    @Override
    public String mostrarRol() {
        return "Tester - Tipo: " + tipoTester;
    }

    private String TipoTester(String tipoTester) {
        if (tipoTester == null || tipoTester.trim().isEmpty()) {
            return tipoTester;
        }

        tipoTester = tipoTester.trim().toLowerCase();

        return tipoTester.substring(0, 1).toUpperCase() + tipoTester.substring(1);
    }
}