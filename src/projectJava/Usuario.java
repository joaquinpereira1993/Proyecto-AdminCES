package projectJava;

public abstract class Usuario {

     private String nombre;
     private String apellido;
     private String email;
     private String pais;
     private String contrasena;
     private String tipo;

     public Usuario(String nombre, String apellido, String email, String pais, String contrasena) {
          this.nombre = nombre;
          this.apellido = apellido;
          this.email = email;
          this.pais = pais;
          this.contrasena = contrasena;
          this.tipo = "Usuario";
     }
     public abstract String mostrarRol();

     // GETTERS Y SETTERS
     public String getNombre() {
          return nombre;
     }

     public void setNombre(String nombre) {
          this.nombre = nombre;
     }

     public String getApellido() {
          return apellido;
     }

     public void setApellido(String apellido) {
          this.apellido = apellido;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getPais() {
          return pais;
     }

     public void setPais(String pais) {
          this.pais = pais;
     }

     public String getContrasena() {
          return contrasena;
     }

     public void setContrasena(String contrasena) {
          this.contrasena = contrasena;
     }

     public String getTipo() {
          return tipo;
     }

     protected void setTipo(String tipo) {
          this.tipo = tipo;
     }
}