# Authorization Server

Este proyecto está diseñado para ser un servidor de autorización que maneja la autenticación y autorización usando OAuth2. Utiliza Spring Boot para su configuración y gestión, lo que permite una fácil integración con aplicaciones Spring. Las dependencias indican que se soportan bases de datos en memoria para pruebas (H2) y PostgreSQL para producción. Además, se incluyen herramientas de desarrollo y pruebas para asegurar un desarrollo eficiente y una alta calidad del software.

## Requisitos

- Java 21
- Maven 3.6+
- Docker (opcional, para ejecutar con Docker Compose)

### Configuración de la Base de Datos

El proyecto está configurado para usar una base de datos H2 en memoria por defecto. Para cambiar a PostgreSQL, actualiza la configuración en `src/main/resources/application.yml`:

Licencia
Este proyecto está licenciado bajo la Licencia MIT - ver el archivo LICENSE para más detalles.









