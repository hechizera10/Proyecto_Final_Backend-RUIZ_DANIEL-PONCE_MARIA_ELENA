# PROYECTO INTEGRADOR - CLINICA ODONTOLOGICA
__Backend - Certified Tech Developer - Digital House__

Profesor Jorge Pereyra
## Consigna
### Sistema de reserva de turnos
Se desea implementar un sistema que permita administrar la reserva de turnos para una clínica odontológica. 
Este debe cumplir con los siguientes requerimientos:
- __Administración de datos de odontólogos:__ listar, agregar, modificar y eliminar odontólogos. Registrar apellido, nombre y matrícula de los mismos.
- __Administración de datos de los pacientes:__ listar, agregar, modificar y eliminar pacientes. De cada uno se almacenan: nombre, apellido, domicilio, DNI y fecha de alta.
- __Registrar turno:__ se tiene que poder permitir asignar a un paciente un turno con un odontólogo a una determinada fecha y hora. 
- __Login:__ validar el ingreso al sistema mediante un login con usuario y password. Se debe permitir a cualquier usuario logueado (ROLE_USER) registrar un turno, pero solo a quienes tengan un rol de administración (ROLE_ADMIN) poder gestionar odontólogos y pacientes. Un usuario podrá tener un único rol y los mismos se ingresarán directamente en la base de datos.

## Requerimientos técnicos
La aplicación debe ser desarrollada en capas:
- __Capa de entidades de negocio:__ son las clases Java de nuestro negocio modelado a través del paradigma orientado a objetos.
- __Capa de acceso a datos (Repository):__ son las clases que se encargarán de acceder a la base de datos.
- __Capa de datos (base de datos):__ es la base de datos de nuestro sistema modelado a través de un modelo entidad-relación. Utilizaremos la base H2 por su practicidad. 
- __Capa de negocio:__ son las clases service que se encargan de desacoplar el acceso a datos de la vista.
- __Capa de presentación:__ son las pantallas web que tendremos que desarrollar utilizando el framework de Spring Boot MVC con los controladores y alguna de estas dos opciones: HTML+JavaScript para la vista.
Es importante realizar el manejo de excepciones logueando cualquier excepción que se pueda generar y la realización de test unitarios para garantizar la calidad de los desarrollos.


## Especificaciones de Desarrollo
- __Funcionalidades:__ ABM de Pacientes, ABM de Odontólogos, asignación de turnos, login y consulta de turnos.
- Organización del proyecto y configuraciones.
- Clases de negocio.
- Clases de acceso a datos con la utilización de ORM.
- Clases de servicio.
- Clases controllers.
- Utilización MVC y Spring.
- Invocación de alguna API con JavaScript.
- Uso de buenas prácticas:
  - Logueo con Log4J ante excepciones.
  - Testeo unitario a través del uso de JUnit.

## Buenas Prácticas
- Utilizar Maven para las dependencias de librerías y frameworks.
- Implementar una arquitectura empresarial en capas e inyección de dependencias.
- Utilizar el patrón MVC que proporciona el propio framework de Spring.
- Utilizar un ORM para el acceso a datos y persistencia.
- Mantener un logueo de la aplicación a través de Log4J para facilitar la solución de problemas (troubleshooting) ante errores.
- Mantener siempre la práctica de realizar testeos unitarios para asegurar la calidad del software.


## Autores
- [Ponce María Elena Haydee](https://github.com/hechizera10)
- [Daniel Mauricio Ruiz Sepúlveda](https://github.com/dan-ruiz)
