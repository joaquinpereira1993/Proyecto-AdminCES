package projectJava;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class SistemaUsuarios {

     private List<Usuario> usuarios;
     private Scanner scan;

     public SistemaUsuarios() {
          usuarios = new ArrayList<>();
          scan = new Scanner(System.in);
          cargarUsuariosPrueba();
     }

     // Usuarios precargados
     private void cargarUsuariosPrueba() {
          registrarAdmin("Carlos", "Garcia", "carlos@admin.com", "Argentina", "admin1234", "Alto");
          registrarAdmin("Laura", "Martinez", "laura@admin.com", "Mexico", "admin5678", "Medio");
          registrarTester("Juan", "Perez", "juan@tester.com", "Argentina", "tester123", "Junior");
          registrarTester("Joaquin", "Pereira", "joaquin@tester.com", "Uruguay", "tester456", "Senior");
          registrarTester("Rodrigo", "Gonzalez", "rodrigo@tester.com", "Bolivia", "tester789", "Lider");
          registrarUsuarioComun("Pedro", "Ruiz", "pedro@gmail.com", "Chile", "pedro1234");
     }

     // Métodos públicos
     public void registrarAdmin(String nombre, String apellido, String email, String pais, String contrasena, String nivelAcceso) {
          usuarios.add(new Admin(nombre, apellido, email, pais, contrasena, nivelAcceso));
     }

     public void registrarTester(String nombre, String apellido, String email, String pais, String contrasena, String tipoTester) {
          usuarios.add(new Tester(nombre, apellido, email, pais, contrasena, tipoTester));
     }

     public void registrarUsuarioComun(String nombre, String apellido, String email, String pais, String contrasena) {
          usuarios.add(new UsuarioComun(nombre, apellido, email, pais, contrasena));
     }

     public boolean existeEmail(String email) {
          for (Usuario usuario : usuarios) {
               if (usuario.getEmail().equalsIgnoreCase(email)) {
                    return true;
               }
          }
          return false;
     }

     public Usuario buscarPorEmail(String email) {
          for (Usuario usuario : usuarios) {
               if (usuario.getEmail().equalsIgnoreCase(email)) {
                    return usuario;
               }
          }
          return null;
     }

     public Usuario login(String email, String contrasena) {
          for (Usuario usuario : usuarios) {
               if (usuario.getEmail().equalsIgnoreCase(email)
                       && usuario.getContrasena().equals(contrasena)) {
                    return usuario;
               }
          }
          return null;
     }

     public void listarUsuarios() {
          System.out.println("\n--- LISTA DE USUARIOS ---");

          int contador = 1;
          for (Usuario usuario : usuarios) {
               System.out.println(
                       contador + ". "
                               + usuario.getNombre() + " "
                               + usuario.getApellido()
                               + " | "
                               + usuario.getEmail()
                               + " | "
                               + usuario.mostrarRol()
               );
               contador++;
          }
     }

     public void buscarUsuario() {
          System.out.println("Ingrese email del usuario:");
          String email = scan.nextLine();

          Usuario usuario = buscarPorEmail(email);

          if (usuario != null) {
               System.out.println("\nUsuario encontrado");
               System.out.println("Nombre: " + usuario.getNombre());
               System.out.println("Apellido: " + usuario.getApellido());
               System.out.println("Email: " + usuario.getEmail());
               System.out.println("Pais: " + usuario.getPais());
               System.out.println("Rol: " + usuario.mostrarRol());
          } else {
               System.out.println("Usuario no encontrado");
          }
     }

     // Menú y flujos de consola
     public void mostrarMenu() {
          int opcion;
          do {
               System.out.println("\nMenu Principal");
               System.out.println("1- Login");
               System.out.println("2- Registro");
               System.out.println("3- Salir");

               opcion = Integer.parseInt(scan.nextLine());

               switch (opcion) {
                    case 1:
                         menuLogin();
                         break;
                    case 2:
                         menuRegistro();
                         break;
                    case 3:
                         System.out.println("Saliendo...");
                         break;
                    default:
                         System.out.println("Opcion invalida");
               }
          } while (opcion != 3);
     }

     public void menuUsuario() {
          int opcion;

          do {
               System.out.println("\n--- MENU DE USUARIO ---");
               System.out.println("1- Listar usuarios");
               System.out.println("2- Buscar usuario");
               System.out.println("3- Cerrar sesión");

               opcion = Integer.parseInt(scan.nextLine());

               switch (opcion) {
                    case 1:
                         listarUsuarios();
                         break;
                    case 2:
                         buscarUsuario();
                         break;
                    case 3:
                         System.out.println("Sesión finalizada.");
                         break;
                    default:
                         System.out.println("Opción inválida.");
               }

          } while (opcion != 3);
     }

     // Condiciones lógicas del sistema
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

               menuUsuario();
          } else {
               System.out.println("Email o contraseña incorrectos.");
          }
     }

     private void menuRegistro() {
          System.out.println("Que tipo de usuario desea registrar?");
          System.out.println("1- Administrador");
          System.out.println("2- Tester");
          int tipoOpcion = Integer.parseInt(scan.nextLine());

          while (tipoOpcion != 1 && tipoOpcion != 2) {
               System.out.println("Tipo de usuario invalido. Ingrese 1 para Administrador o 2 para Tester:");
               tipoOpcion = Integer.parseInt(scan.nextLine());
          }

          System.out.println("Ingrese nombre:");
          String nombre = scan.nextLine();
          while (nombre.trim().isEmpty()) {
               System.out.println("El nombre no puede estar vacío, ingrese nuevamente:");
               nombre = scan.nextLine();
          }

          System.out.println("Ingrese apellido:");
          String apellido = scan.nextLine();
          while (apellido.trim().isEmpty()) {
               System.out.println("El apellido no puede estar vacío, ingrese nuevamente:");
               apellido = scan.nextLine();
          }

          System.out.println("Ingrese email:");
          String email = scan.nextLine();
          while (!email.contains("@") || existeEmail(email)) {
               if (existeEmail(email)) {
                    System.out.println("El email ya esta registrado, ingrese otro:");
               } else {
                    System.out.println("Formato email incorrecto, ingrese nuevamente el email:");
               }
               email = scan.nextLine();
          }

          System.out.println("Ingrese pais:");
          String pais = scan.nextLine();
          while (pais.trim().isEmpty()) {
               System.out.println("El pais no puede estar vacío, ingrese nuevamente:");
               pais = scan.nextLine();
          }

          System.out.println("Ingrese contrasena:");
          String contrasena = scan.nextLine();
          while (contrasena.length() < 8) {
               System.out.println("La contrasena debe tener al menos 8 caracteres, ingrese nuevamente:");
               contrasena = scan.nextLine();
          }

          if (tipoOpcion == 1) {
               System.out.println("Ingrese nivel de acceso (Alto o Medio):");
               String nivelAcceso = scan.nextLine();

               while (!nivelAcceso.equalsIgnoreCase("Alto")
                       && !nivelAcceso.equalsIgnoreCase("Medio")) {
                    System.out.println("Ingrese un nivel de acceso válido (Alto o Medio):");
                    nivelAcceso = scan.nextLine();
               }

               registrarAdmin(nombre, apellido, email, pais, contrasena, nivelAcceso);
               System.out.println("Administrador registrado con exito.");

          } else {
               System.out.println("Ingrese tipo de tester (Junior, Senior o Lider):");
               String tipoTester = scan.nextLine();

               while (!tipoTester.equalsIgnoreCase("Junior")
                       && !tipoTester.equalsIgnoreCase("Senior")
                       && !tipoTester.equalsIgnoreCase("Lider")) {
                    System.out.println("Ingrese un tipo de tester válido (Junior, Senior o Lider):");
                    tipoTester = scan.nextLine();
               }

               registrarTester(nombre, apellido, email, pais, contrasena, tipoTester);
               System.out.println("Tester registrado con exito.");
          }
     }
}