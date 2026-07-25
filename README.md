Funcionalidades:
- Inicio de sesión
Permite acceder al sistema utilizando únicamente un usuario de tipo Administrador. Para iniciar sesión, el usuario debe ingresar un email y una contraseña válidos. El sistema verifica que las credenciales correspondan a un administrador registrado; en caso contrario, informa el error y permite intentar nuevamente sin finalizar la aplicación.

Datos solicitados:

Email.
Contraseña.

- Registrar usuario administrador
Permite registrar nuevos usuarios administradores dentro del sistema. Durante el proceso se valida que todos los datos obligatorios sean completados correctamente, que el email tenga un formato válido, que no exista otro usuario registrado con el mismo email y que la contraseña cumpla con la longitud mínima establecida. Además, se solicita confirmar la contraseña antes de completar el registro.

Datos solicitados:

Nombre.
Apellido.
Email.
País de nacimiento.
Contraseña.
Confirmación de contraseña.

- Alta de usuario Tester
Permite registrar usuarios de tipo Tester. El sistema solicita toda la información necesaria y valida que el email sea válido, que no exista previamente en el sistema, que la contraseña cumpla con la longitud mínima requerida y que el tipo de tester corresponda a una de las opciones permitidas (Junior, Senior o Líder).

Datos solicitados:

Nombre.
Apellido.
Email.
País.
Contraseña.
Tipo de Tester (Junior, Senior o Líder).

- Listar usuarios
Permite visualizar el listado completo de usuarios registrados en el sistema, mostrando la información principal de cada uno, como nombre, apellido, email, país y rol. Desde esta misma opción también es posible acceder a la funcionalidad de eliminación de usuarios Tester.

La información mostrada incluye:

Nombre.
Apellido.
Email.
País.
Rol.

- Buscar usuario
Permite localizar un usuario mediante su dirección de correo electrónico. Si el usuario existe, el sistema muestra toda la información registrada (nombre, apellido, email, país y rol). En caso de no encontrar coincidencias, informa el error mediante una excepción personalizada sin interrumpir la ejecución del programa.

Dato solicitado:

Email.

- Reiniciar contraseña
Permite restablecer la contraseña de un usuario de tipo Administrador. El sistema solicita el email del usuario, la nueva contraseña y su confirmación. Antes de aplicar el cambio, verifica que el usuario exista, que pertenezca al tipo Administrador, que la nueva contraseña cumpla con la longitud mínima requerida y que ambas contraseñas coincidan. Una vez completado el formulario, el usuario puede confirmar la operación o cancelarla mediante la opción Atrás.

Datos solicitados:

Email.
Nueva contraseña.
Confirmación de contraseña.

- Eliminar usuario
Permite eliminar únicamente usuarios de tipo Tester. El sistema solicita el email del usuario, verifica que exista y comprueba que no corresponda a un Administrador. Antes de realizar la eliminación definitiva, solicita una confirmación para evitar eliminaciones accidentales.

Dato solicitado:

Email del Tester.

- Cerrar sesión
Permite finalizar la sesión del administrador autenticado y regresar al menú principal del sistema, desde donde es posible iniciar sesión nuevamente con otro usuario administrador o acceder a las demás opciones disponibles.

- Salir del sistema
Permite finalizar la ejecución de la aplicación de forma controlada, cerrando el programa desde el menú principal.

Diagrama UML actualizado:
![img.png](img.png)
