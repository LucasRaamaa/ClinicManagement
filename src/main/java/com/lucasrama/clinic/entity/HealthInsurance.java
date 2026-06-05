package com.lucasrama.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "health_insurances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthInsurance {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El nombre de la obra social no puede estar vacío")
  @Size(max = 150, message = "El nombre no puede exceder los 150 caracteres")
  @Column(nullable = false, length = 150)
  private String name;

  // El teléfono no es obligatorio pero si se envía, validamos que no exceda la longitud permitida.
  @Size(max = 50, message = "El teléfono de contacto no puede exceder los 50 caracteres")
  @Column(name = "contact_phone", length = 50)
  private String contactPhone;
}
