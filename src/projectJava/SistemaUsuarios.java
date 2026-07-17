package projectJava;

import projectJava.excepciones.DatosInvalidosException;
import projectJava.excepciones.EmailDuplicadoException; // Excepción personalizada que se lanza cuando se intenta registrar un email que ya existe.
import projectJava.excepciones.UsuarioNoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaUsuarios {

     private List<Usuario> usuarios; // Lista donde se almacenan todos los usuarios registrados en el sistema.
     private Scanner scan; // Scanner utilizado para leer los datos ingresados por consola.
     private static SistemaUsuarios instancia; // Única instancia de la clase (Patrón Singleton).

     // Constructor privado para evitar la creación de múltiples instancias y garantizar el uso del patrón Singleton.
     private SistemaUsuarios() {
          usuarios = new ArrayList<>();
          scan = new Scanner(System.in);
          cargarUsuariosPrueba();
     }
     // Devuelve la única instancia del sistema. Si todavía no existe, la crea por primera vez.
     public static SistemaUsuarios getInstancia() {

          if (instancia == null) {
               instancia = new SistemaUsuarios();
          }

          return instancia;
     }
     // Metodo para solicitar al usuario un número entero y valide que no esté vacío ni contenga caracteres inválidos, evitando que el programa se detenga.
     // Si el dato es incorrecto, vuelve a solicitarlo hasta que sea válido
     private int leerEntero(String mensaje) {

          while (true) {

               System.out.println(mensaje);

               String entrada = scan.nextLine().trim();

               if (entrada.isEmpty()) {
                    System.out.println("Ingrese una opcion valida por favor");
                    continue;
               }

               try {
                    return Integer.parseInt(entrada);
               } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido.");
               }
          }
     }

     // Usuarios precargados
     private void cargarUsuariosPrueba() {
          usuarios.add(new Admin("Carlos", "Garcia", "carlos@admin.com", "Argentina", "admin1234"));
          usuarios.add(new Admin("Laura", "Martinez", "laura@admin.com", "Colombia", "admin5678"));
          usuarios.add(new Admin("Juan", "Perez", "juan@admin.com", "Bolivia", "admin1993"));
          usuarios.add(new Admin("Martin", "Hernandez", "martin@admin.com", "Uruguay", "admin1993"));
          usuarios.add(new Tester("Joaquin", "Pereira", "joaquin@tester.com", "Uruguay", "tester456", "Senior"));
          usuarios.add(new Tester("Rodrigo", "Gonzalez", "rodrigo@tester.com", "Brasil", "tester789", "Lider"));
          usuarios.add(new Tester("Jose", "Perez", "jose@tester.com", "Paraguay", "tester123", "Junior"));
          usuarios.add(new Tester("Hugo", "Fernandez", "hugo@tester.com", "Argentina", "tester123", "Junior"));

     }

     // Métodos públicos
     public void registrarAdmin(String nombre, String apellido, String email,String pais, String contrasena)
             throws DatosInvalidosException, EmailDuplicadoException {
          // Verifica que los datos obligatorios sean válidos.
          validarDatosUsuario(nombre, apellido, pais, contrasena);
          // Comprueba que el email no pertenezca a otro usuario registrado.
          if (existeEmail(email)) {
               throw new EmailDuplicadoException("El email ya se encuentra registrado.");
          }

          // Si todas las validaciones son correctas, registra el nuevo administrador.
          usuarios.add(new Admin(nombre, apellido, email, pais, contrasena));
     }

     public void registrarTester(String nombre, String apellido, String email,
                                 String pais, String contrasena, String tipoTester)
             throws DatosInvalidosException, EmailDuplicadoException { // Verifica que los datos obligatorios sean válidos.
          validarDatosUsuario(nombre, apellido, pais, contrasena);
          if (existeEmail(email)) { // Evita registrar dos usuarios con el mismo email.
               throw new EmailDuplicadoException("El email ya se encuentra registrado.");
          }
          // Agrega el nuevo tester a la lista de usuarios.
          usuarios.add(new Tester(nombre, apellido, email, pais, contrasena, tipoTester));
     }
     public boolean existeEmail(String email) { // Recorre la lista para verificar si el email ya fue registrado.
          for (Usuario usuario : usuarios) {
               if (usuario.getEmail().equalsIgnoreCase(email)) {
                    return true;
               }
          }
          return false;
     }

     private boolean emailValido(String email) { // Valida que el email tenga un formato correcto utilizando una expresión regular.

          String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

          return email.matches(regex);
     }
     private String solicitarEmailValido() {

          String email;

          // Solicita el email hasta que el formato ingresado sea válido.
          // La verificación de emails duplicados se realiza durante el registro.
          do {

               System.out.println("Ingrese email:");
               email = scan.nextLine().trim();

               if (!emailValido(email)) {
                    System.out.println("Formato de email inválido. Ejemplo: usuario@dominio.com");
               }

          } while (!emailValido(email));

          return email;
     }
     // Centraliza las validaciones de los datos obligatorios.
     // Si algun dato es incorrecto, lanza una excepción personalizada.
     private void validarDatosUsuario(String nombre, String apellido, String pais, String contrasena)
             throws DatosInvalidosException {

          if (nombre == null || nombre.trim().isEmpty()) {
               throw new DatosInvalidosException("El nombre no puede estar vacío.");
          }

          if (apellido == null || apellido.trim().isEmpty()) {
               throw new DatosInvalidosException("El apellido no puede estar vacío.");
          }

          if (pais == null || pais.trim().isEmpty()) {
               throw new DatosInvalidosException("El país no puede estar vacío.");
          }

          if (contrasena == null || contrasena.length() < 5) {
               throw new DatosInvalidosException(
                       "La contraseña debe tener al menos 5 caracteres.");
          }
     }
     public Usuario buscarPorEmail(String email) throws UsuarioNoEncontradoException {

// Busca un usuario por su email dentro de la colección.
          for (Usuario usuario : usuarios) {
               if (usuario.getEmail().equalsIgnoreCase(email)) {
                    return usuario;
               }
          }
// Si no encuentra coincidencias, informa el error mediante una excepción personalizada.
          throw new UsuarioNoEncontradoException(
                  "No existe un usuario registrado con el email: " + email);
     }

     public Usuario login(String email, String contrasena) {
// Verifica que exista un administrador con el email y contraseña ingresados.
          for (Usuario usuario : usuarios) {

               if (usuario instanceof Admin && usuario.getEmail().equalsIgnoreCase(email) && usuario.getContrasena().equals(contrasena)) {
                    return usuario;
               }
          }

          return null;
     }

     public void listarUsuarios() {

          System.out.println("\n--- Usuarios registrados en el sistema ---");

          int contador = 1;
// Muestra todos los usuarios registrados en el sistema.
          for (Usuario usuario : usuarios) {

               System.out.println(
                       contador + ". "
                               + usuario.getNombre() + " "
                               + " | "
                               + usuario.getApellido()
                               + " | "
                               + usuario.getEmail()
                               + " | "
                               + usuario.getPais()
                               + " | "
                               + usuario.mostrarRol());

               contador++;
          }

          int opcion;

          do {

               System.out.println("\nOpciones");
               System.out.println("1- Eliminar Tester");
               System.out.println("2- Volver");

               opcion = leerEntero("Seleccione una opción:");

               switch (opcion) {

                    case 1:
                         eliminarTester();
                         break;

                    case 2:
                         break;

                    default:
                         System.out.println("Opción inválida.");
               }

          } while (opcion != 2);
     }
     public void eliminarTester() {

          System.out.println("\nIngrese el email del tester que desea eliminar:");
          String email = scan.nextLine();

          Usuario usuario;
// Busca el usuario utilizando el email ingresado.
// Si no existe, se captura la excepción y se informa el motivo.
          try {
               usuario = buscarPorEmail(email);
          } catch (UsuarioNoEncontradoException e) {
               System.out.println(e.getMessage());
               return;
          }

          if (usuario instanceof Admin) {
               System.out.println("No es posible eliminar un administrador.");
               return;
          }

          String opcion;

          do {
               System.out.println("\n¿Está seguro que desea eliminar al tester " + usuario.getNombre() + " " + usuario.getApellido() + "?");
               System.out.println("1- Sí");
               System.out.println("2- Cancelar");
               opcion = scan.nextLine();

               switch (opcion) {

                    case "1":
                         usuarios.remove(usuario); // Elimina el tester de la lista de usuarios.
                         System.out.println("\nTester eliminado correctamente.");
                         break;

                    case "2":
                         System.out.println("\nOperación cancelada.");
                         break;

                    default:
                         System.out.println("Opción inválida. Intente nuevamente.");
               }

          } while (!opcion.equals("1") && !opcion.equals("2"));
     }
     public void buscarUsuario() {

          System.out.println("Ingrese email del usuario:");
          String email = scan.nextLine();
// Busca el usuario por email y muestra sus datos.
// Si no existe, se informa mediante una excepción personalizada.
          try {

               Usuario usuario = buscarPorEmail(email);

               System.out.println("\nUsuario encontrado");
               System.out.println("Nombre: " + usuario.getNombre());
               System.out.println("Apellido: " + usuario.getApellido());
               System.out.println("Email: " + usuario.getEmail());
               System.out.println("Pais: " + usuario.getPais());
               System.out.println("Rol: " + usuario.mostrarRol());

          } catch (UsuarioNoEncontradoException e) {
               System.out.println(e.getMessage());
          }
     }

// Menú principal del sistema.
// Permite iniciar sesión, registrar administradores o salir de la aplicación.
     public void mostrarMenu() {
          int opcion;
          do {
               System.out.println("\nMenu Principal");
               System.out.println("1- Login");
               System.out.println("2- Registro");
               System.out.println("3- Salir");

               opcion = leerEntero("Seleccione una opción:");
               switch (opcion) {
                    case 1:
                         menuLogin();
                         break;
                    case 2:
                         menuRegistroAdmin();
                         break;
                    case 3:
                         System.out.println("Saliendo...");
                         break;
                    default:
                         System.out.println("Opcion invalida");
               }
          } while (opcion != 3);
     }

     public void menuUsuarioAdmin() { // Menú disponible para los administradores una vez que inician sesión.
          int opcion;

          do {
               System.out.println("\n--- MENU DE USUARIO ---");
               System.out.println("1- Registrar Tester");
               System.out.println("2- Listar usuarios");
               System.out.println("3- Buscar usuario");
               System.out.println("4- Cerrar sesión");

               opcion = leerEntero("Seleccione una opción:");
               switch (opcion) {
                    case 1:
                         menuRegistroTester();
                         break;

                    case 2:
                         listarUsuarios();
                         break;

                    case 3:
                         buscarUsuario();
                         break;

                    case 4:
                         System.out.println("Sesión finalizada.");
                         break;

                    default:
                         System.out.println("Opción inválida.");
               }

          } while (opcion != 4);
     }
// Solicita los datos necesarios para registrar un nuevo tester realizando todas las validaciones correspondientes.
     private void menuRegistroTester() {

          System.out.println("\n--- REGISTRO DE TESTER ---");

          System.out.println("Ingrese nombre:");
          String nombre = scan.nextLine();
          while (nombre.trim().isEmpty()) {
               System.out.println("El nombre no puede estar vacío:");
               nombre = scan.nextLine();
          }

          System.out.println("Ingrese apellido:");
          String apellido = scan.nextLine();
          while (apellido.trim().isEmpty()) {
               System.out.println("El apellido no puede estar vacío:");
               apellido = scan.nextLine();
          }

          String email = solicitarEmailValido(); // Para evitar duplicado en el codigo en el registro de un tester

          String pais = "";

          int opcionPais;

          do {

               System.out.println("\nSeleccione el país:");
               System.out.println("1- Paraguay");
               System.out.println("2- Uruguay");
               System.out.println("3- Argentina");
               System.out.println("4- Brasil");

               opcionPais = leerEntero("Seleccione el país de la lista:");

               switch (opcionPais) {

                    case 1:
                         pais = "Paraguay";
                         break;

                    case 2:
                         pais = "Uruguay";
                         break;

                    case 3:
                         pais = "Argentina";
                         break;

                    case 4:
                         pais = "Brasil";
                         break;

                    default:
                         pais = "";
                         System.out.println("Opción inválida. Intente nuevamente.");
               }

          } while (pais.trim().isEmpty());


          System.out.println("Ingrese contraseña:");
          String contrasena = scan.nextLine();

          while (contrasena.length() < 5) {
               System.out.println("La contraseña debe tener al menos 5 caracteres:");
               contrasena = scan.nextLine();
          }

          System.out.println("Ingrese tipo de tester (Junior, Senior o Lider):");
          String tipoTester = scan.nextLine();

          while (!tipoTester.equalsIgnoreCase("Junior")
                  && !tipoTester.equalsIgnoreCase("Senior")
                  && !tipoTester.equalsIgnoreCase("Lider")) {

               System.out.println("Ingrese un tipo válido (Junior, Senior o Lider):");
               tipoTester = scan.nextLine();
          }

          try {

               registrarTester(nombre, apellido, email, pais, contrasena, tipoTester);

               System.out.println("\nTester registrado correctamente.");
          // Captura errores de validación y de duplicidad de email.
          } catch (DatosInvalidosException | EmailDuplicadoException e) {

               System.out.println(e.getMessage());

          }
     }
     // Solicita las credenciales del administrador y valida el acceso al sistema.
     private void menuLogin() {
          System.out.println("Ingrese email:");
          String emailIngresado = scan.nextLine();

          System.out.println("Ingrese contrasena:");
          String contrasenaIngresada = scan.nextLine();

          Usuario resultado = login(emailIngresado, contrasenaIngresada);

          if (resultado != null) {
               System.out.println("\nLogin exitoso.");
               System.out.println("Bienvenido " + resultado.getNombre());
               System.out.println(resultado.mostrarRol());

               menuUsuarioAdmin();
          } else {
               System.out.println("Error. Ingrese un usuario de tipo Administrador");
          }
     }
// Solicita los datos necesarios para registrar un nuevo administrador validando que la información ingresada sea correcta.
     private void menuRegistroAdmin() {

          System.out.println("\n--- REGISTRO DE ADMINISTRADOR ---");

          System.out.println("Ingrese nombre:");
          String nombre = scan.nextLine();
          while (nombre.trim().isEmpty()) {
               System.out.println("El nombre no puede estar vacío:");
               nombre = scan.nextLine();
          }

          System.out.println("Ingrese apellido:");
          String apellido = scan.nextLine();
          while (apellido.trim().isEmpty()) {
               System.out.println("El apellido no puede estar vacío:");
               apellido = scan.nextLine();
          }

          String email = solicitarEmailValido(); //Para evitar duplicados de codigo de un registro de administrador

          System.out.println("Ingrese país:");
          String pais = scan.nextLine();
          while (pais.trim().isEmpty()) {
               System.out.println("El país no puede estar vacío:");
               pais = scan.nextLine();
          }

          String contrasena;

          do {
               System.out.println("Ingrese contraseña:");
               contrasena = scan.nextLine();

               if (contrasena.length() < 5) {
                    System.out.println("La contraseña debe tener al menos 5 caracteres.");
               }

          } while (contrasena.length() < 5);

          String repetirContrasena;

          do {

               System.out.println("Repita la contraseña:");
               repetirContrasena = scan.nextLine();

               if (!contrasena.equals(repetirContrasena)) {
                    System.out.println("Las contraseñas no coinciden.");
               }

          } while (!contrasena.equals(repetirContrasena));
// Intenta registrar el administrador.
// Si ocurre un error de validación, se muestra el mensaje correspondiente.
          try {

               registrarAdmin(nombre, apellido, email, pais, contrasena);

               System.out.println("\nAdministrador registrado correctamente.");
               menuUsuarioAdmin();

          // Captura tanto errores de validación como intentos de registrar un email ya existente.
          } catch (DatosInvalidosException | EmailDuplicadoException e) {

               System.out.println(e.getMessage());

          }
     }
}
