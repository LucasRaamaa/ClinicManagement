package com.lucasrama.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Relación 1:1 con la tabla Users
  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
  private User user;

  // Relación N:1 con la tabla Health_Insurances
  @ManyToOne
  @JoinColumn(name = "health_insurance_id", referencedColumnName = "id")
  private HealthInsurance healthInsurance;

  @NotBlank(message = "El nombre no puede estar vacío")
  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @NotBlank(message = "El apellido no puede estar vacío")
  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  @NotBlank(message = "El DNI es obligatorio")
  @Column(nullable = false, unique = true, length = 20)
  private String dni;

  @Column(length = 50)
  private String phone;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "affiliate_number", length = 100)
  private String affiliateNumber;
}
