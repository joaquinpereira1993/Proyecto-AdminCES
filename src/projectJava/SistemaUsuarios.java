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

     private void cargarUsuariosPrueba() {
          usuarios.add(new Admin("Carlos", "Garcia", "carlos@admin.com", "Argentina", "admin1234", 1));
          usuarios.add(new Admin("Laura", "Martinez", "laura@admin.com", "Mexico", "admin5678", 2));
          usuarios.add(new Tester("Juan", "Perez", "juan@tester.com", "Argentina", "tester123", "Desarrollo"));
          usuarios.add(new Tester("Ana", "Lopez", "ana@tester.com", "Colombia", "tester456", "Staging"));
          usuarios.add(new Usuario("Pedro", "Ruiz", "pedro@mail.com", "Chile", "pedro1234"));
     }

     public void mostrarMenu() {
          int opcion;
          do {
               System.out.println("Elija una opcion");
               System.out.println("1- Login");
               System.out.println("2- Registro");
               System.out.println("3- Salir");
               opcion = Integer.parseInt(scan.nextLine());
               switch (opcion) {
                    case 1:
                         login();
                         break;
                    case 2:
                         registro();
                         break;
                    case 3:
                         System.out.println("Saliendo...");
                         break;
                    default:
                         System.out.println("Opcion invalida");
                         break;
               }
          } while (opcion != 3);
     }

     private void login() {
          System.out.println("Ingrese email:");
          String emailIngresado = scan.nextLine();
          System.out.println("Ingrese contrasena:");
          String contrasenaIngresada = scan.nextLine();

          if (!validarUsuarioExistente(emailIngresado)) {
               System.out.println("No existe el usuario en el sistema.");
               return;
          }

          if (validarCredenciales(emailIngresado, contrasenaIngresada)) {
               for (int i = 0; i < usuarios.size(); i++) {
                    if (usuarios.get(i).getEmail().equals(emailIngresado)) {
                         System.out.println("Usuario logueado con exito. Bienvenido " + usuarios.get(i).getNombre() + " - " + usuarios.get(i).getTipo());
                    }
               }
          } else {
               System.out.println("Contrasena incorrecta.");
          }
     }

     private void registro() {
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
          while (!email.contains("@") || validarUsuarioExistente(email)) {
               if (validarUsuarioExistente(email)) {
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

          Usuario nuevoUsuario = new Usuario(nombre, apellido, email, pais, contrasena);
          usuarios.add(nuevoUsuario);
          System.out.println("Registro exitoso");
     }

     public boolean validarUsuarioExistente(String email) {
          for (int i = 0; i < usuarios.size(); i++) {
               if (usuarios.get(i).getEmail().equals(email)) {
                    return true;
               }
          }
          return false;
     }

     public boolean validarCredenciales(String email, String contrasena) {
          for (int i = 0; i < usuarios.size(); i++) {
               if (usuarios.get(i).getEmail().equals(email) && usuarios.get(i).getContrasena().equals(contrasena)) {
                    return true;
               }
          }
          return false;
     }
}