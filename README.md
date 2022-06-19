# BLOG API REST
API REST for a generic blog web app.

## Data Model
![ER Diagram](er-model.png)

## Stress Test Result
![RESTfull Stress chart](restfull-stress-results.png)

## Cómo usar
La aplicación se puede iniciar con el siguiente comando: `docker-compose up`

### Documentación Swagger
Los endpoints ofrecidos por la aplicación se pueden revisar en
el siguiente link: [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/index.html)

### Adminer
La visualización de los datos se puede realizar mediante la consola de Adminer disponible
en el siguiente link: [http://localhost](http://localhost).

Llenar el formulario de login con los siguientes datos:
- Motor de base de datos: PostgreSQL
- Servidor: posrgres
- Usuario: admin
- Contraseña: Passw0rd!
- Base de datos: blogapi
