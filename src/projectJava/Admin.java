package projectJava;

public class Admin extends Usuario {

    private int nivelAcceso;

    public Admin(String nombre, String apellido, String email, String pais, String contrasena, int nivelAcceso) {
        super(nombre, apellido, email, pais, contrasena);
        this.nivelAcceso = nivelAcceso;
        setTipo("Admin");
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
}
