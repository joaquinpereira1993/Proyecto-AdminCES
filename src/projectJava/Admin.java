package projectJava;

public class Admin extends Usuario {

    private String nivelAcceso;

    public Admin(String nombre, String apellido, String email, String pais, String contrasena, String nivelAcceso) {

        super(nombre, apellido, email, pais, contrasena);
        this.nivelAcceso = nivelAcceso;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
}