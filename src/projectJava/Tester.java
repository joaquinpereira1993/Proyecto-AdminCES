package projectJava;

// Clase que representa a un usuario de tipo Tester. Hereda los atributos y comportamientos comunes de la clase Usuario.
public class Tester extends Usuario {

    private String tipoTester;

    // Constructor que inicializa los datos del tester y normaliza el tipo de tester.
    public Tester(String nombre,
                  String apellido,
                  String email,
                  String pais,
                  String contrasena,
                  String tipoTester)
    {
        super(nombre, apellido, email, pais, contrasena);
        this.tipoTester = TipoTester(tipoTester);
    }

    // Devuelve el tipo de tester.
    public String getTipoTester() {
        return tipoTester;
    }

    // Modifica el tipo de tester aplicando el mismo formato de escritura.
    public void setTipoTester(String tipoTester) {
        this.tipoTester = TipoTester(tipoTester);
    }

    // Implementación del metodo abstracto de la clase Usuario. Devuelve el rol del usuario junto con el tipo de tester.
    @Override
    public String mostrarRol() {
        return "Tester - Tipo: " + tipoTester;
    }

// Normaliza el texto ingresado para que el tipo de tester siempre tenga la primera letra en mayúscula y el resto en minúscula.
    private String TipoTester(String tipoTester) {

        // Si el valor es nulo o está vacío, lo devuelve sin modificaciones.
        if (tipoTester == null || tipoTester.trim().isEmpty()) {
            return tipoTester;
        }

        tipoTester = tipoTester.trim().toLowerCase();

        return tipoTester.substring(0, 1).toUpperCase() + tipoTester.substring(1);
    }
}