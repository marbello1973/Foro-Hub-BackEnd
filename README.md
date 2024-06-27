## For Hub API
For Hub API proporciona servicios para la plataforma For Hub, permitiendo la gestión de tópicos entre usuarios.

### Tecnologías Utilizadas
- Java
- MySQL como base de datos
- Autenticación con JWT y BCrypt
- Swagger para documentación de la API
- Configuración mediante archivos .yml
- Insomnia y Postman para probar la API

### URLs Importantes
- Endpoint Autenticacion: [http://localhost:8080/login](http://localhost:8080/login)
- Swagger UI: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
- Endpoint Base: [http://localhost:8080/topico](http://localhost:8080/topico)

### Configuración

### Requisitos Previos
Asegúrate de tener instalados:
- Java Development Kit (JDK) 21 o superior
- MySQL Server
- IDE (IntelliJ IDEA recomendado)
- Insomnia o Postman

#### Clonar el Repositorio
```bash
https://github.com/marbello1973/Foro-Hub-BackEnd.git
cd Foro-Hub-BackEnd
```
### Configuración de la Base de Datos
Crea una base de datos MySQL llamada _forohubdb_
Configura las credenciales de acceso en application.yml
Ejecución del Proyecto
Importa el proyecto en tu IDE.
Ejecuta la aplicación desde tu IDE o mediante Maven:
```
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost/forohub
    username: root
    password: tu_password
```
### Acceso a la API
Accede a Swagger UI para ver la documentación detallada de la API.
> _Swagger UI: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)_

Autenticación y Seguridad
* La API utiliza JWT para la autenticación y BCrypt para el cifrado de contraseñas. 
* Por default te puedes autenticar con el email david@gmail.com y password 123456
* Asegúrate de incluir el token JWT en las cabeceras de tus peticiones protegidas.


### Endpoints Principales

> ![GET](https://img.shields.io/badge/metodo-GET-yellow.svg)
Consultar todos los registros en base de datos
```http
  GET /topicos
```
| Parametros | Typo     | Descripcion               |
| :-------- | :------- | :------------------------- |
| `No` | `GET` | **Requerido** estar autenticado |

> ![GET](https://img.shields.io/badge/metodo-GET-green.svg)
Consultar registros por identificador unico ID
```http
  GET /topico/${id}
```
| Parametros | Typo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `GET` | **Requerido** estar autenticado y pasar el id en la url |

> ![POST](https://img.shields.io/badge/metodo-POST-blue.svg) 
Crear un registro  
```http
  POST /topico
```
| Parametros | Typo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `No`      | `POST` | **Requerido** estar autenticado    |

> ![PUT](https://img.shields.io/badge/metodo-PUT-1abc9c.svg) 
Actualizar un registro
```http
  PUT /topico/${id}
```
| Parametros | Typo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `No`      | `PUT` | **Requerido** estar autenticado, id en el cuerpo del JSON |

> ![DELETE](https://img.shields.io/badge/metodo-DELETE-red.svg)
Deshabilitar un registro
```http
  DELETE /topico/${id}
```
| Parametros | Typo     | Descripcion                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `DELETE` | **Requerido** estar autenticado, id en la url |






  

