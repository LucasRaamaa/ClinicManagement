package com.lucasrama.clinic.service;

import com.lucasrama.clinic.entity.Appointment;
import com.lucasrama.clinic.entity.AppointmentStatus;
import com.lucasrama.clinic.entity.Doctor;
import com.lucasrama.clinic.entity.Patient;
import com.lucasrama.clinic.repository.AppointmentRepository;
import com.lucasrama.clinic.repository.DoctorRepository;
import com.lucasrama.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final PatientRepository patientRepository;
  private final DoctorRepository doctorRepository;
  private final EmailService emailService;

  @Autowired
  public AppointmentService(AppointmentRepository appointmentRepository,
      PatientRepository patientRepository,
      EmailService emailService,
      DoctorRepository doctorRepository){
    this.appointmentRepository = appointmentRepository;
    this.patientRepository = patientRepository;
    this.doctorRepository = doctorRepository;
    this.emailService = emailService;
  }

  public Appointment scheduleAppointment(Appointment appointment){

    Patient patient = patientRepository.findById(appointment.getPatient().getId())
        .orElseThrow(() -> new IllegalArgumentException("Error: El paciente especificado no existe."));

    Doctor doctor = doctorRepository.findById(appointment.getDoctor().getId())
        .orElseThrow(() -> new IllegalArgumentException("Error: El médico especificado no existe."));

    // evitamos superposicion de turnos
    List<Appointment> dailyAppointments = appointmentRepository.findByDoctorIdAndAppointmentDate(
        appointment.getDoctor().getId(),
        appointment.getAppointmentDate()
    );

    //la hora esta ocupada?
    for (Appointment existingAppt : dailyAppointments) {
      if (existingAppt.getStatus() != AppointmentStatus.CANCELADO &&
          existingAppt.getAppointmentTime().equals(appointment.getAppointmentTime())) {
        throw new IllegalArgumentException("Error: El médico ya tiene un turno reservado a las "
            + appointment.getAppointmentTime());
      }
    }

    appointment.setStatus(AppointmentStatus.PENDIENTE);

    Appointment savedAppointment = appointmentRepository.save(appointment);

    emailService.sendAppointmentConfirmation(
        patient.getUser().getEmail(),
        patient.getFirstName(),
        doctor.getLastName(),
        savedAppointment.getAppointmentDate().toString(),
        savedAppointment.getAppointmentTime().toString()
    );

    return savedAppointment;
  }

  public List<Appointment> getAppointmentsByPatient(Long patientId) {
    return appointmentRepository.findByPatientId(patientId);
  }
}