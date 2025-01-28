# Foro Hub - Challenge - Back End
Esta es mi implementación del challenge back-end Foro Hub de Alura. Está centrado en la creación de una API REST que permite realizar operaciones CRUD sobre tópicos de un foro.

## Descripción
Un foro es un espacio esencial en plataformas de aprendizaje como Alura, donde estudiantes, profesores y moderadores pueden colaborar, resolver dudas y compartir conocimientos. 
Además de implementar las [funcionalidades](#funcionalidades) principales, el proyecto incorpora validaciones de negocio, autenticación/autorización y el uso de una base de datos para la persistencia de la información.

La API permite el inicio de sesión, el cual es necesario para acceder a los demás endpoints. Esto es posible gracias a JWT, estándar que permite que los usuarios inicien sesión y accedan a recursos protegidos sin necesidad de mantener una sesión activa en el servidor.

## Tecnologías
- **Java 17:** como lenguaje de programación principal.
- **Maven** para la gestión de dependencias.
- **Spring Boot** como framework para el desarrollo del back-end.
- **Dependencias**
  - Lombok: reducción de código repetitivo mediante anotaciones.
  - Spring Web: creación de controladores REST.
  - Spring Boot DevTools: facilita el desarrollo con reinicios automáticos.
  - Spring Data JPA: manejo de la persistencia con JPA.
  - Flyway Migration: control de versiones de la base de datos.
  - MySQL Driver: base de datos relacional para la persistencia de datos.
  - Validation: validación de datos de entrada.
  - Spring Security: implementación de autenticación y autorización.
  - JWT Token: para la transmisión segura de información mediante un token.

## Funcionalidades
1) Crear un nuevo tópico
2) Mostrar todos los tópicos
3) Mostrar un tópico específico
4) Actualizar un tópico
5) Eliminar un tópico

## Endpoints de la API
#### Crear un nuevo tópico

```http
  POST /topicos
```

#### Mostrar todos los tópicos

```http
  GET /topicos
```

#### Mostrar un tópico específico

```http
  GET /topicos/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long o Integer`   | **Required**. La Id del item a obtener |

### Actualizar un tópico específico

```http
  PUT /topicos/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long o Integer`   | **Required**. La Id del item a actualizar |

### Eliminar un tópico específico

```http
  DELETE /topicos/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long o Integer`   | **Required**. La Id del item a eliminar |


