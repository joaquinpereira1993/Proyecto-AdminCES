Funcionalidades:
- Inicio de sesión: Permite acceder al sistema con un usuario.
  Datos: email, contraseña.
- Registrar usuario admin: Permite registrar un usuario administrador.
  Datos: nombre, apellido, mail, contraseña, pais nacimiento.
- Ver usuarios: Permite ver todos los usuarios registrados.
- Reiniciar contraseña:Permite reestablecer la contraseña de un usuario.
  Datos: email, contraseña
- Alta de cuenta para tester: Registrar usuario tester.
  Datos: Nombre, Apellido, Email, Pais, Contraseña, Tipo de Tester (Jr., Sr., Líder)
- Ver y editar perfil de usuario admin: Permite ver datos del propio usuario y editarlos.
- Eliminar usuario: Permite eliminar un usuario de tester

- Diagrama UML:
  ```mermaid
classDiagram

class Main {
    +main(String[])
}

class Usuario {
    -nombre : String
    -apellido : String
    -email : String
    -pais : String
    -contrasena : String

    +Usuario(nombre, apellido, email, pais, contrasena)
    +getNombre() String
    +getApellido() String
    +getEmail() String
    +getPais() String
    +getContrasena() String
    +setNombre(nombre : String)
    +setApellido(apellido : String)
    +setEmail(email : String)
    +setPais(pais : String)
    +setContrasena(contrasena : String)
}

class SistemaUsuarios {
    -usuarios : Usuario[]
    -scan : Scanner

    +SistemaUsuarios()
    +existeEmail(email : String) boolean
    +buscarPorEmail(email : String) Usuario
    +login(email : String, contrasena : String) Usuario
    +registrarAdmin(nombre, apellido, email, pais, contrasena, nivelAcceso)
    +registrarTester(nombre, apellido, email, pais, contrasena, tipoTester)
    +listarUsuarios()
}

class Admin {
    -nivelAcceso : String

    +Admin(nombre, apellido, email, pais, contrasena, nivelAcceso)
    +getNivelAcceso() String
    +setNivelAcceso(nivelAcceso : String)
}

class Tester {
    -tipoTester : String

    +Tester(nombre, apellido, email, pais, contrasena, tipoTester)
    +getTipoTester() String
    +setTipoTester(tipoTester : String)
}

Main --> Usuario
SistemaUsuarios --> Usuario

Usuario <|-- Admin
Usuario <|-- Tester
```
<img width="1129" height="579" alt="UML AdminCES OK" src="https://github.com/user-attachments/assets/d71c8ff2-8613-4a30-b754-d17cb390025c" />
