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
          usuarios.add(new Admin("Carlos", "Garcia", "carlos@admin.com", "Argentina", "admin1234"));
          usuarios.add(new Admin("Laura", "Martinez", "laura@admin.com", "Paraguay", "admin5678"));
          usuarios.add(new Admin("Juan", "Perez", "juan@tester.com", "Brasil", "admin1993"));
          usuarios.add(new Tester("Joaquin", "Pereira", "joaquin@tester.com", "Uruguay", "tester456", "Senior"));
          usuarios.add(new Tester("Rodrigo", "Gonzalez", "rodrigo@tester.com", "Bolivia", "tester789", "Lider"));
          usuarios.add(new Tester("Rodrigo", "Gonzalez", "jose@tester.com", "Chile", "", "Junior"));
     }

     // Métodos públicos
     public void registrarAdmin(String nombre, String apellido, String email, String pais, String contrasena) {
          usuarios.add( new Admin(nombre, apellido, email, pais, contrasena));
     }

     public void registrarTester(String nombre, String apellido, String email, String pais, String contrasena, String tipoTester) {
          usuarios.add(new Tester(nombre, apellido, email, pais, contrasena, tipoTester));
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

               if (usuario instanceof Admin && usuario.getEmail().equalsIgnoreCase(email) && usuario.getContrasena().equals(contrasena)) {

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
                               + " | "
                               + usuario.getApellido()
                               + " | "
                               + usuario.getEmail()
                               + " | "
                               + usuario.getPais()
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
               System.out.println("1- Registrar Tester");
               System.out.println("2- Listar usuarios");
               System.out.println("3- Buscar usuario");
               System.out.println("4- Cerrar sesión");

               opcion = Integer.parseInt(scan.nextLine());

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

          System.out.println("Ingrese email:");
          String email = scan.nextLine();

          while (!email.contains("@") || existeEmail(email)) {

               if (existeEmail(email)) {
                    System.out.println("El email ya está registrado:");
               } else {
                    System.out.println("Formato de email incorrecto:");
               }

               email = scan.nextLine();
          }

          System.out.println("Ingrese país:");
          String pais = scan.nextLine();

          while (pais.trim().isEmpty()) {
               System.out.println("El país no puede estar vacío:");
               pais = scan.nextLine();
          }

          System.out.println("Ingrese contraseña:");
          String contrasena = scan.nextLine();

          while (contrasena.length() < 8) {
               System.out.println("La contraseña debe tener al menos 8 caracteres:");
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

          registrarTester(nombre, apellido, email, pais, contrasena, tipoTester);

          System.out.println("\nTester registrado correctamente.");
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

          System.out.println("Ingrese email:");
          String email = scan.nextLine();
          while (!email.contains("@") || existeEmail(email)) {

               if (existeEmail(email)) {
                    System.out.println("El email ya está registrado:");
               } else {
                    System.out.println("Formato de email inválido:");
               }

               email = scan.nextLine();
          }

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

               if (contrasena.length() < 8) {
                    System.out.println("La contraseña debe tener al menos 8 caracteres.");
               }

          } while (contrasena.length() < 8);

          String repetirContrasena;

          do {

               System.out.println("Repita la contraseña:");
               repetirContrasena = scan.nextLine();

               if (!contrasena.equals(repetirContrasena)) {
                    System.out.println("Las contraseñas no coinciden.");
               }

          } while (!contrasena.equals(repetirContrasena));

          registrarAdmin(nombre, apellido, email, pais, contrasena);

          System.out.println("\nAdministrador registrado correctamente.");
          menuUsuario();


     }
   }
