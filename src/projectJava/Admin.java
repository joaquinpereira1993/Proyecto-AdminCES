package projectJava;

public class Admin extends Usuario {

    private String nivelAcceso;

    public Admin(String nombre,
                 String apellido,
                 String email,
                 String pais,
                 String contrasena,
                 String nivelAcceso)
    {
        super(nombre, apellido, email, pais, contrasena);
        this.nivelAcceso = NivelAcceso(nivelAcceso);
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = NivelAcceso(nivelAcceso);
    }

    @Override
    public String mostrarRol() {
        return "Administrador - Nivel: " + nivelAcceso;
    }

    private String NivelAcceso(String nivelAcceso) {
        if (nivelAcceso == null || nivelAcceso.trim().isEmpty()) {
            return nivelAcceso;
        }

        nivelAcceso = nivelAcceso.trim().toLowerCase();

        return nivelAcceso.substring(0, 1).toUpperCase() + nivelAcceso.substring(1);
    }
}