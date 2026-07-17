package projectJava.excepciones;

// Excepción personalizada que se lanza cuando no se encuentra un usuario con el email ingresado dentro del sistema.
public class UsuarioNoEncontradoException extends Exception {

    // Recibe el mensaje de error y lo envía a la clase Exception.
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}