package projectJava.excepciones;

// Excepción personalizada que se lanza cuando alguno de los datos ingresados por el usuario no cumple con las validaciones definidas por el sistema.
public class DatosInvalidosException extends Exception {

    // Recibe el mensaje de error y lo envía a la clase Exception.
    public DatosInvalidosException(String mensaje) {
        super(mensaje);
    }
}