package projectJava;
// Clase abstracta que representa los datos y comportamientos comunes de todos los tipos de usuarios del sistema (Administradores y Testers).
public abstract class Usuario {

     private String nombre;
     private String apellido;
     private String email;
     private String pais;
     private String contrasena;

     // Constructor que inicializa los atributos básicos de un usuario.
     public Usuario(String nombre, String apellido, String email, String pais, String contrasena) {
          this.nombre = nombre;
          this.apellido = apellido;
          this.email = email;
          this.pais = pais;
          this.contrasena = contrasena;
     }
// Metodo abstracto que deberá ser implementado por las clases hijas para indicar el rol correspondiente de cada usuario.
     public abstract String mostrarRol();

     // GETTERS Y SETTERS

     // Devuelve el nombre del usuario.
     public String getNombre() {
          return nombre;
     }

     // Modifica el nombre del usuario.
     public void setNombre(String nombre) {
          this.nombre = nombre;
     }

     // Devuelve el apellido del usuario.
     public String getApellido() {
          return apellido;
     }

     // Modifica el apellido del usuario.
     public void setApellido(String apellido) {
          this.apellido = apellido;
     }

     // Devuelve el email del usuario.
     public String getEmail() {
          return email;
     }

     // Modifica el email del usuario.
     public void setEmail(String email) {
          this.email = email;
     }

     // Devuelve el país del usuario.
     public String getPais() {
          return pais;
     }

     // Modifica el país del usuario.
     public void setPais(String pais) {
          this.pais = pais;
     }

     // Devuelve la contraseña del usuario.
     public String getContrasena() {
          return contrasena;
     }

     // Modifica la contraseña del usuario.
     public void setContrasena(String contrasena){
          this.contrasena = contrasena;
     }
}