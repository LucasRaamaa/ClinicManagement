package com.lucasrama.clinic.service;

import com.lucasrama.clinic.entity.Appointment;
import com.lucasrama.clinic.entity.AppointmentStatus;
import com.lucasrama.clinic.repository.AppointmentRepository;
import com.lucasrama.clinic.repository.DoctorRepository;
import com.lucasrama.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final PatientRepository patientRepository;
  private final DoctorRepository doctorRepository;

  @Autowired
  public AppointmentService(AppointmentRepository appointmentRepository,
      PatientRepository patientRepository,
      DoctorRepository doctorRepository){
    this.appointmentRepository = appointmentRepository;
    this.patientRepository = patientRepository;
    this.doctorRepository = doctorRepository;
  }

  // Programa un turno evita superposiciones
  public Appointment scheduleAppointment(Appointment appointment){

    // paciente existe?
    if (!patientRepository.existsById(appointment.getPatient().getId())) {
      throw new IllegalArgumentException("Error: El paciente especificado no existe.");
    }
    //doctor existe ?
    if (!doctorRepository.existsById(appointment.getDoctor().getId())) {
      throw new IllegalArgumentException("Error: El médico especificado no existe.");
    }
    // evitamos superposicion de turnos
    List<Appointment> dailyAppointments = appointmentRepository.findByDoctorIdAndAppointmentDate(
        appointment.getDoctor().getId(),
        appointment.getAppointmentDate()
    );

    //la hora esta ocupada?
    for (Appointment existingAppt : dailyAppointments) {
      // Si el estado no es CANCELADO y la hora coincide, rechazamos la reserva
      if (existingAppt.getStatus() != AppointmentStatus.CANCELADO &&
          existingAppt.getAppointmentTime().equals(appointment.getAppointmentTime())) {
        throw new IllegalArgumentException("Error: El médico ya tiene un turno reservado a las "
            + appointment.getAppointmentTime());
      }
    }
    appointment.setStatus(AppointmentStatus.PENDIENTE);
    return appointmentRepository.save(appointment);
  }

  //listamos todos los turnos de un paciente
  public List<Appointment> getAppointmentsByPatient(Long patientId) {
    return appointmentRepository.findByPatientId(patientId);
  }
}
