# DOSW-Library

Este sistema es una API REST desarrollada con Spring Boot para la gestión integral de una biblioteca universitaria. Su funcionamiento se basa en la administración de tres entidades principales: Usuarios, Libros y Préstamos. El flujo comienza con el registro de usuarios o de libros, luego el usuario puede hacer un prestamo,  La robustez del proyecto reside en su capa de validaciones y manejo de excepciones, que impide acciones inválidas como prestar un libro sin stock o registrar datos incompletos y responde con mensajes de error claros y estandarizados, garantizando así la integridad de la información en todo momento.

## Diagrama General
<img width="884" height="209" alt="image" src="https://github.com/user-attachments/assets/9bef1617-61b7-4a32-a902-edb238ad6f93" />

Se utiliza un modelo de componentes para visualizar cómo se dividen las responsabilidades del sistema y cómo interactúan entre sí a través de interfaces definidas.
Interfaz de Usuario, representa el punto de entrada del sistema donde el usuario final interactúa con la API para gestionar libros, usuarios y préstamos, esta a su vez para funcionar necesita de Backend API; que es  el núcleo procesador que contiene toda la lógica de negocio, los servicios de validación y el manejo de excepciones que garantizan el correcto funcionamiento de la biblioteca, por ultimo esta necesita la Base de Datos, la cual se encarga del almacenamiento, es donde se guardan los registros de inventario y el historial de transacciones.



## Diagrama especifico
<img width="1130" height="386" alt="image" src="https://github.com/user-attachments/assets/84954378-e4d7-4631-b7af-e3fa1033d5c3" />

Este diagrama de especifico muestra cómo está organizada la lógica del proyecto de la biblioteca siguiendo una estructura de capas para cada funcionalidad. En primer lugar, los Mappers se encargan de transformar los datos que vienen de afuera para que los Controladores puedan recibirlos correctamente. Estos Controladores actúan como la puerta de entrada que se conecta con los Services, donde realmente ocurre el procesamiento de la información. Finalmente, los Services se apoyan en los Validators para verificar que todas las reglas de negocio se cumplan, como revisar si un libro está disponible o si los datos del usuario son válidos, asegurando que solo la información correcta llegue a procesarse en el sistema.


## Diagrama Clases
<img width="441" height="321" alt="Biblioteca-Página-3 drawio" src="https://github.com/user-attachments/assets/f71637e0-c816-4315-98a9-dfce3a76d983" />


Este diagrama de clases expone el modelo de dominio del sistema, donde la entidad Loan actúa como el núcleo de la lógica al asociar un User con un Book. Se observa una estructura clara donde el préstamo registra la fecha de salida y se apoya en una enumeración de Status para controlar de forma estricta si el ejemplar está activo o ya ha sido retornado.

## Covertura de las pruebas con Jacoco 
<img width="1733" height="493" alt="image" src="https://github.com/user-attachments/assets/24b894e2-2cf3-4c21-924e-ef5b7271ec2b" />

## Análisis estatico
<img width="1120" height="273" alt="image" src="https://github.com/user-attachments/assets/d1f53205-3c27-4851-8b83-360d2b335898" />

## Video del demo en Swagger 
https://youtu.be/uvXhNBVr9IE?si=wKeUfBRbuZLsMHq9

## Diagrama Entidad-Relación
<img width="511" height="471" alt="Biblioteca-Página-4 drawio" src="https://github.com/user-attachments/assets/aef0c06c-722f-4b98-b35c-59581b7976fc" />


El diagrama de entidad relación presenta la estructura de la base de datos en tercera forma normal, enfocándose en la gestión de inventario y seguridad mediante tres tablas principales. La tabla Loan funciona como el punto de unión central, utilizando llaves foráneas para conectar a un único User y a un único Book con cada registro de préstamo, lo que establece relaciones de uno a muchos donde un usuario puede realizar múltiples solicitudes y un libro puede aparecer en diversos préstamos a lo largo del tiempo.

## Video de la conexión con la base de datos 
https://youtu.be/xMdPkjSlrc8

## JWT en postman
https://youtu.be/IWEpHUQmmPw

## Diagrama No relacional 
<img width="821" height="561" alt="Untitled Diagram (3)-Page-1 drawio" src="https://github.com/user-attachments/assets/df1fc9ec-7361-4db1-9a3f-6e8f2569cfdb" />


