package com.lucasrama.clinic.repository;

import com.lucasrama.clinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  // Para ver todos los turnos de un paciente específico
  List<Appointment> findByPatientId(Long patientId);

  // Para ver la agenda de un médico en un día específico (vital para evitar sobreposiciones)
  List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);
}

