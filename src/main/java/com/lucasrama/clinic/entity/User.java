package com.lucasrama.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El email no puede estar vacío")
  @Email(message = "Debe ser un formato de email válido")
  @Column(nullable = false, unique = true, length = 150)
  private String email;

  @NotBlank(message = "La contraseña no puede estar vacía")
  @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
  @Column(nullable = false)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

}
