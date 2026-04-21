# E-Commerce Backend API

## 🚀 Tecnologías

- Java 17
- Spring Boot
- Spring Security + JWT
- PostgreSQL
- Docker

## 📦 Funcionalidades

- Autenticación JWT (login/register)
- CRUD de productos
- Creación de órdenes con validación de stock
- Paginación y filtros
- Manejo global de errores

## 🐳 Ejecución con Docker

```bash
mvn clean package -DskipTests
docker-compose up --build
