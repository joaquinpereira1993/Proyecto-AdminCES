package projectJava;

// Clase principal de la aplicación. Se encarga de iniciar el sistema y mostrar el menú principal al usuario.
public class Main {

    public static void main(String[] args) {

        // Obtiene la única instancia de SistemaUsuarios utilizando el patrón Singleton.
        SistemaUsuarios sistema = SistemaUsuarios.getInstancia();

        // Inicia la ejecución del sistema mostrando el menú principal.
        sistema.mostrarMenu();
    }
}