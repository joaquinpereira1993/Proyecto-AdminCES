package projectJava.excepciones;

// Excepción personalizada que indica que el email ingresado ya pertenece a un usuario registrado en el sistema.
public class EmailDuplicadoException extends Exception {

    public EmailDuplicadoException(String mensaje) {
        super(mensaje);
    }

}