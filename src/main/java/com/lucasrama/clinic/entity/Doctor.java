package com.lucasrama.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Relación 1:1 con la tabla Users
  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
  private User user;

  @NotBlank(message = "El nombre no puede estar vacío")
  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @NotBlank(message = "El apellido no puede estar vacío")
  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  @NotBlank(message = "La especialidad es obligatoria")
  @Column(nullable = false, length = 100)
  private String specialty;

  @NotBlank(message = "El número de licencia/matrícula es obligatorio")
  @Column(name = "license_number", nullable = false, unique = true, length = 50)
  private String licenseNumber;
}
