package com.lucasrama.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //Relacion 1:1 con patient
  @OneToOne
  @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false, unique = true)
  private Patient patient;

  @NotNull(message = "La fecha de creacion es obligatoria")
  @Column(name = "creation_date", nullable = true)
  private LocalDate creationDate;

  @Size(max = 10, message = "El tipo de sangre no puede exceder los 10 caracteres")
  @Column(name = "blood_type", length = 10)
  private String bloodType;

  // text en mysql lo mapeo a un string en java. no utilizo limite de tamaño con @Size
  // ya que el doctor puede escribir largo
  @Column(columnDefinition = "TEXT")
  private String allergies;

}
