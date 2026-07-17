package projectJava;

// Clase que representa a un usuario de tipo Administrador. Hereda los atributos y comportamientos comunes de la clase Usuario.
public class Admin extends Usuario {

    // Constructor que inicializa los datos del administrador.
    public Admin(String nombre,
                 String apellido,
                 String email,
                 String pais,
                 String contrasena) {

        super(nombre, apellido, email, pais, contrasena);
    }

    // Implementacion del metodo abstracto de la clase Usuario. Devuelve el rol correspondiente al administrador.
    @Override
    public String mostrarRol() {
        return "Administrador";
    }
}