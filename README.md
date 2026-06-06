# Plataforma de Gestión de Turnos y Expedientes Clínicos (HealthTech)

El sistema permite administrar usuarios, médicos, pacientes, obras sociales, turnos y un registro detallado de historias clínicas y tratamientos.

Características

* **Gestión de Usuarios y Roles:** Sistema preparado para diferenciar accesos entre Administradores, Médicos y Pacientes.
* **Turnos:** Lógica de negocio anti-choques que previene la superposición de reservas de turnos para un mismo médico en la misma fecha y hora.
* **Núcleo Clínico:** Relación estructurada donde cada paciente posee una única historia clínica general (1:1), la cual almacena un historial infinito de tratamientos y consultas (1:N).
* **Manejo de Errores Global:** Implementación de `@ControllerAdvice` para capturar excepciones de lógica de negocio y validaciones, devolviendo respuestas HTTP 400 claras y estructuradas en formato JSON.
* **Validación de Datos:** Uso estricto de Jakarta Validation (`@NotBlank`, `@Email`, `@Size`, `@NotNull`) desde la capa de entrada (Controladores) hasta las Entidades.

## Tecnologías Utilizadas

* **Lenguaje:** Java 17 / 21
* **Framework Core:** Spring Boot 3.x
* **Acceso a Datos:** Spring Data JPA / Hibernate
* **Base de Datos:** MySQL
* **Herramientas Adicionales:** Lombok (reducción de boilerplate), Postman (Testeo de API).

## Modelo de Datos (Relaciones)

* `User` (1:1) -> `Patient` / `Doctor`
* `Patient` (N:1) -> `HealthInsurance`
* `Doctor` & `Patient` (1:N) -> `Appointment`
* `Patient` (1:1) -> `MedicalRecord`
* `MedicalRecord` (1:N) -> `Treatment`
