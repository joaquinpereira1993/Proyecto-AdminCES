package projectJava;

public class Admin extends Usuario {

    public Admin(String nombre,
                 String apellido,
                 String email,
                 String pais,
                 String contrasena) {

        super(nombre, apellido, email, pais, contrasena);
    }

    @Override
    public String mostrarRol() {
        return "Administrador";
    }
}