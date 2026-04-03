# DOSW-Library

Este sistema es una API REST desarrollada con Spring Boot para la gestión integral de una biblioteca universitaria. Su funcionamiento se basa en la administración de tres entidades principales: Usuarios, Libros y Préstamos. El flujo comienza con el registro de usuarios o de libros, luego el usuario puede hacer un prestamo,  La robustez del proyecto reside en su capa de validaciones y manejo de excepciones, que impide acciones inválidas como prestar un libro sin stock o registrar datos incompletos y responde con mensajes de error claros y estandarizados, garantizando así la integridad de la información en todo momento.

## Diagrama General
<img width="884" height="209" alt="image" src="https://github.com/user-attachments/assets/9bef1617-61b7-4a32-a902-edb238ad6f93" />

Se utiliza un modelo de componentes para visualizar cómo se dividen las responsabilidades del sistema y cómo interactúan entre sí a través de interfaces definidas.
Interfaz de Usuario, representa el punto de entrada del sistema donde el usuario final interactúa con la API para gestionar libros, usuarios y préstamos, esta a su vez para funcionar necesita de Backend API; que es  el núcleo procesador que contiene toda la lógica de negocio, los servicios de validación y el manejo de excepciones que garantizan el correcto funcionamiento de la biblioteca, por ultimo esta necesita la Base de Datos, la cual se encarga del almacenamiento, es donde se guardan los registros de inventario y el historial de transacciones.



## Diagrama especifico

## Diagrama Clases


## Covertura de las pruebas con Jacoco 
<img width="1733" height="493" alt="image" src="https://github.com/user-attachments/assets/24b894e2-2cf3-4c21-924e-ef5b7271ec2b" />


## Video del demo en Swagger 
https://youtu.be/uvXhNBVr9IE?si=wKeUfBRbuZLsMHq9

