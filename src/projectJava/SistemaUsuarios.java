package projectJava;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaUsuarios {

     private ArrayList<Usuario> usuarios;
     private Scanner scan;

     public SistemaUsuarios() {
          usuarios = new ArrayList<Usuario>();
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
          usuarios.add(new Usuario("Pedro", "Ruiz", "pedro@mail.com", "Chile", "pedro1234"));
     }

     // Métodos públicos
     public void registrarAdmin(String nombre, String apellido, String email, String pais, String contrasena, String nivelAcceso) {
          Admin admin = new Admin(nombre, apellido, email, pais, contrasena, nivelAcceso);
          usuarios.add(admin);
     }

     public void registrarTester(String nombre, String apellido, String email, String pais, String contrasena, String tipoTester) {
          Tester tester = new Tester(nombre, apellido, email, pais, contrasena, tipoTester);
          usuarios.add(tester);
     }

     public boolean existeEmail(String email) {
          for (int i = 0; i < usuarios.size(); i++) {
               if (usuarios.get(i).getEmail().equals(email)) {
                    return true;
               }
          }
          return false;
     }

     public Usuario buscarPorEmail(String email) {
          for (int i = 0; i < usuarios.size(); i++) {
               if (usuarios.get(i).getEmail().equals(email)) {
                    return usuarios.get(i);
               }
          }
          return null;
     }

     public Usuario login(String email, String contrasena) {
          for (int i = 0; i < usuarios.size(); i++) {
               if (usuarios.get(i).getEmail().equals(email) && usuarios.get(i).getContrasena().equals(contrasena)) {
                    return usuarios.get(i);
               }
          }
          return null;
     }

     public void listarUsuarios() {
          System.out.println("\n--- LISTA DE USUARIOS ---");
          for (int i = 0; i < usuarios.size(); i++) {
               String tipo = "Usuario";
               if (usuarios.get(i) instanceof Admin) {
                    tipo = "Administrador";
               } else if (usuarios.get(i) instanceof Tester) {
                    tipo = "Tester";
               }
               System.out.println((i + 1) + ". " + usuarios.get(i).getNombre() + " " + usuarios.get(i).getApellido() + " | " + usuarios.get(i).getEmail() + " | " + tipo);
          }
     }

     // Menú y flujos de consola

     public void mostrarMenu() {
          int opcion;
          do {
               System.out.println("\nElija una opcion");
               System.out.println("1- Login");
               System.out.println("2- Registro");
               System.out.println("3- Ver lista de usuarios");
               System.out.println("4- Salir");
               opcion = Integer.parseInt(scan.nextLine());
               switch (opcion) {
                    case 1:
                         menuLogin();
                         break;
                    case 2:
                         menuRegistro();
                         break;
                    case 3:
                         listarUsuarios();
                         break;
                    case 4:
                         System.out.println("Saliendo...");
                         break;
                    default:
                         System.out.println("Opcion invalida");
                         break;
               }
          } while (opcion != 4);
     }

// Condiciones lógicas del sistema

     private void menuLogin() {
          System.out.println("Ingrese email:");
          String emailIngresado = scan.nextLine();
          System.out.println("Ingrese contrasena:");
          String contrasenaIngresada = scan.nextLine();

          Usuario resultado = login(emailIngresado, contrasenaIngresada);

          if (resultado != null) {

               if (resultado instanceof Admin) {
                    //Mostrar nivel de acceso
                    Admin admin = (Admin) resultado;

                    System.out.println("Login exitoso. Bienvenido " + admin.getNombre());
                    System.out.println("Nivel de acceso: " + admin.getNivelAcceso());

               } else if (resultado instanceof Tester) {
                    // Mostrar tipo de tester
                    Tester tester = (Tester) resultado;

                    System.out.println("Login exitoso. Bienvenido " + tester.getNombre());

                    System.out.println("Tipo de tester: " + tester.getTipoTester());

               } else {

                    System.out.println("Login exitoso. Bienvenido " + resultado.getNombre());
               }

          } else {

               System.out.println("Contrasena incorrecta.");
          }
     }

     private void menuRegistro() {
          System.out.println("Que tipo de usuario desea registrar?");
          System.out.println("1- Administrador");
          System.out.println("2- Tester");
          int tipoOpcion = Integer.parseInt(scan.nextLine());

          System.out.println("Ingrese nombre:");
          String nombre = scan.nextLine();
          while (nombre.equals("") || nombre.length() <= 5) {
               System.out.println("No supera la cantidad minima de caracteres, ingrese nuevamente el nombre:");
               nombre = scan.nextLine();
          }

          System.out.println("Ingrese apellido:");
          String apellido = scan.nextLine();
          while (apellido.equals("")) {
               System.out.println("No supera la cantidad minima de caracteres, ingrese nuevamente el apellido:");
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
          while (pais.equals("")) {
               System.out.println("No supera la cantidad minima de caracteres, ingrese nuevamente el pais:");
               pais = scan.nextLine();
          }

          System.out.println("Ingrese contrasena:");
          String contrasena = scan.nextLine();
          while (contrasena.length() < 8) {
               System.out.println("No supera la cantidad minima de caracteres, ingrese nuevamente la contrasena:");
               contrasena = scan.nextLine();
          }

          if (tipoOpcion == 1) {

               System.out.println("Ingrese nivel de acceso:");
               String nivelAcceso = scan.nextLine();

               registrarAdmin(nombre, apellido, email, pais, contrasena, nivelAcceso);
               System.out.println("Administrador registrado con exito.");

          } else if (tipoOpcion == 2) {

               System.out.println("Ingrese tipo de tester (Ej: Junior, Senior ó Lider):");
               String tipoTester = scan.nextLine();

               registrarTester(nombre, apellido, email, pais, contrasena, tipoTester);

               System.out.println("Tester registrado con exito.");

          } else {

               System.out.println("Tipo de usuario invalido.");
          }
     }
}