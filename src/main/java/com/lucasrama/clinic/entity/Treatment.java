package com.lucasrama.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "treatments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Relación N:1 con MedicalRecord
  @ManyToOne
  @JoinColumn(name = "medical_record_id", referencedColumnName = "id", nullable = false)
  private MedicalRecord medicalRecord;

  @NotNull(message = "La fecha de la consulta/tratamiento es obligatoria")
  @Column(name = "treatment_date", nullable = false)
  private LocalDate treatmentDate;

  @NotBlank(message = "El diagnóstico no puede estar vacío")
  @Size(max = 255, message = "El diagnóstico no puede superar los 255 caracteres")
  @Column(nullable = false, length = 255)
  private String diagnosis;

  @Column(columnDefinition = "TEXT")
  private String prescription;

  @Column(columnDefinition = "TEXT")
  private String notes;
}
