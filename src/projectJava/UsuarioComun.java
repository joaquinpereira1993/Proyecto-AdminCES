package projectJava;

public class UsuarioComun extends Usuario {

    public UsuarioComun(String nombre,
                        String apellido,
                        String email,
                        String pais,
                        String contrasena)
    {
        super(nombre, apellido, email, pais, contrasena);
    }

    @Override
    public String mostrarRol() {
        return "Usuario Común";
    }
}