**ComercioVentas API**

Esta es una API diseñada para la gestión de ventas, productos, clientes y comprobantes. Utiliza Spring Boot como framework principal y sigue las mejores prácticas de desarrollo.

**Contenido**

1. [Instrucciones de Instalación](#instrucciones-de-instalaci%C3%B3n)
2. [Uso de la API](#uso-de-la-api)
3. [Documentación de Endpoints](#documentaci%C3%B3n-de-endpoints)
4. [Manejo de Errores](#manejo-de-errores)
5. [Estructura del Proyecto](#estructura-del-proyecto)

**Instrucciones de Instalación**

**Requisitos Previos**

- Java 17 o superior
- Maven 3.6.0 o superior
- Base de datos MySQL (Workbench)
- Postman para pruebas (opcional pero recomendado)

**Paso 1: Configuración del Entorno**

Clona este repositorio:

git clone <https://github.com/FTAMBURRO/ComercioVentasCHFinal/tree/main/ComercioVentasDC/ComercioVentas>

cd ComercioVentas

Configura la base de datos en src/main/resources/application.properties.

Asegúrate de que las credenciales de la base de datos sean correctas.

spring.datasource.url=jdbc:mysql://localhost:3306/tu_basededatos

spring.datasource.username=tu_usuario

spring.datasource.password=tu_contraseña

**Paso 2: Crear la Base de Datos**

Ejecuta el script SQL para crear las tablas necesarias. Está disponible en src/main/resources/ScriptsDb.sql.

mysql -u tu_usuario -p < src/main/resources/ScriptsDb.sql

**Paso 3: Levantar la Aplicación**

Compila y ejecuta la aplicación:

mvn clean install

mvn spring-boot:run

**Uso de la API**

Una vez que la aplicación esté en funcionamiento, puedes usar herramientas como Postman para interactuar con la API. También puedes utilizar Swagger para una interfaz visual de la API.

**Documentación de Endpoints**

**Productos**

**Obtener todos los productos**

- **GET** /api/productos
- **Respuesta Exitosa (200)**: Lista de productos disponibles.

**Crear un nuevo producto**

- **POST** /api/productos
- **Body**:

{

"nombre": "Producto B",

"descripcion": "Descripción del Producto B",

"precio": 150.0,

"stock": 60

}

**Respuesta Exitosa (201)**: Producto creado.

**Obtener un producto por ID**

- **GET** /api/productos/{id}
- **Respuesta Exitosa (200)**: Datos del producto solicitado.
- **Error (404)**: Producto no encontrado.

**Actualizar un producto existente**

- **PUT** /api/productos/{id}
- **Respuesta Exitosa (200)**: Producto actualizado.
- **Error (404)**: Producto no encontrado.

**Eliminar un producto**

- **DELETE** /api/productos/{id}
- **Respuesta Exitosa (204)**: Producto eliminado.
- **Error (404)**: Producto no encontrado.

**Manejo de Errores**

**Códigos de Respuesta**

- **200 OK**: Operación exitosa.
- **201 Created**: Recurso creado exitosamente.
- **204 No Content**: Operación exitosa, sin contenido de respuesta.
- **400 Bad Request**: Error en la solicitud del cliente.
- **404 Not Found**: Recurso no encontrado.
- **500 Internal Server Error**: Error en el servidor, revisar logs para más detalles.

**Estructura del Proyecto**

- **src/main/java**: Código fuente principal.
- **src/main/resources**: Archivos de configuración y scripts SQL.
- **src/test/java**: Tests unitarios y de integración.
- **pom.xml**: Dependencias y configuración del proyecto.

**Créditos**

Proyecto desarrollado como parte del curso de Java con Spring Boot.
