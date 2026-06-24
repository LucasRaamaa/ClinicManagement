# Plataforma de Gestión de Turnos y Expedientes Clínicos (HealthTech)

El sistema permite administrar usuarios, médicos, pacientes, obras sociales, turnos y un registro detallado de historias clínicas y tratamientos.

Características

- **Autenticación y Seguridad (JWT):** Sistema de autenticación stateless mediante JSON Web Tokens.
- **Autorización por Roles (RBAC):** Control de acceso granular utilizando `@PreAuthorize` en la capa de controladores para diferenciar los permisos entre `ADMIN`, `DOCTOR` y `PATIENT`.
- **Lógica de Negocio Avanzada (Anti-Choques):** Algoritmo en la capa de servicios que previene de forma automática la superposición de reservas de turnos para un mismo médico en la misma fecha y hora.
- **Estructura del Núcleo Clínico:** Relación arquitectónica donde cada paciente posee una única historia clínica general (1:1), la cual almacena un historial infinito de tratamientos y consultas individuales (1:N).
- **Manejo de Errores Global (`Fail-Fast`):** Implementación de `@RestControllerAdvice` para capturar excepciones de lógica de negocio y validaciones de datos, transformando errores internos en respuestas HTTP 400 limpias, claras y estructuradas en formato JSON.
- **Validación Estricta de Datos:** Uso de Jakarta Validation (`@NotBlank`, `@Email`, `@Size`, `@NotNull`) desde la capa de entrada hasta las entidades persistentes.

## Tecnologías Utilizadas

- **Lenguaje:** Java 21 (LTS)
- **Framework Principal:** Spring Boot 3+
- **Seguridad:** Spring Security & JSON Web Tokens (JWT)
- **Persistencia y ORM:** Spring Data JPA / Hibernate
- **Base de Datos:** MySQL
- **Validaciones:** Jakarta Validation
- **Herramientas:** Lombok (Reducción de código boilerplate) y Postman (Testing de endpoints)

## Modelo de Datos (Relaciones)

La base de datos MySQL está diseñada siguiendo las mejores prácticas de normalización y separación de responsabilidades:

- `User (1:1)` ➔ `Patient` / `Doctor` (Separación de credenciales de seguridad vs perfiles de negocio).
- `Patient (N:1)` ➔ `HealthInsurance` (Múltiples pacientes pueden compartir la misma obra social).
- `Doctor` & `Patient (1:N)` ➔ `Appointment` (Eje central de la agenda de turnos).
- `Patient (1:1)` ➔ `MedicalRecord` (Un expediente único por paciente).
- `MedicalRecord (1:N)` ➔ `Treatment` (Evoluciones históricas del paciente).

## Estructura de paquetes

```text
├── config/              # Tablero de control de seguridad, filtros JWT y Beans core
├── controller/          # Controladores REST (Endpoints expuestos al mundo)
├── entity/              # Clases de dominio y Enums mapeados a MySQL
├── exception/           # Respuestas personalizadas y Manejador de Excepciones Global
├── repository/          # Interfaces JpaRepository para comunicación con la BD
└── service/             # Capa del cerebro (Lógica de negocio modularizada)
```
