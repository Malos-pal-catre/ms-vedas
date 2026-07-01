# MS-Vedas

Microservicio encargado de gestionar las vedas de especies marinas decretadas por Sernapesca en la Caleta Lo Abarca.

## Tecnologias
- Java 21
- Spring Boot 4.0.6
- PostgreSQL (Neon)
- Maven

## Puerto
8087

## Endpoints

### Vedas
- POST /api/vedas - Crear nueva veda
- GET /api/vedas - Obtener todas las vedas
- GET /api/vedas/{id} - Obtener veda por ID
- GET /api/vedas/activas - Obtener vedas activas
- GET /api/vedas/especie/{especie} - Obtener vedas por especie
- GET /api/vedas/verificar/{especie} - Verificar si especie esta en veda
- PUT /api/vedas/{id}/suspender - Suspender veda
- PUT /api/vedas/{id}/desactivar - Desactivar veda

## Como correr el proyecto
1. git clone https://github.com/Malos-pal-catre/ms-vedas.git
2. cd ms-vedas
3. ./mvnw spring-boot:run
