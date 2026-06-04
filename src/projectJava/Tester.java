package projectJava;

public class Tester extends Usuario {

    private String ambiente;

    public Tester(String nombre, String apellido, String email, String pais, String contrasena, String ambiente) {
        super(nombre, apellido, email, pais, contrasena);
        this.ambiente = ambiente;
        setTipo("Tester");
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }
}
