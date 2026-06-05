package com.lucasrama.clinic.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Relación N:1 con Patient
  @ManyToOne
  @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
  private Patient patient;

  // Relación N:1 con Doctor
  @ManyToOne
  @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
  private Doctor doctor;

  @NotNull(message = "La fecha del turno es obligatoria")
  @FutureOrPresent(message = "La fecha del turno debe ser hoy o en el futuro")
  @Column(name = "appointment_date", nullable = false)
  private LocalDate appointmentDate;

  @NotNull(message = "La hora del turno es obligatoria")
  @Column(name = "appointment_time", nullable = false)
  private LocalTime appointmentTime;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AppointmentStatus status = AppointmentStatus.PENDIENTE; // Por defecto es PENDIENTE

  @Size(max = 255, message = "El motivo no puede superar los 255 caracteres")
  @Column(length = 255)
  private String reason;
}
