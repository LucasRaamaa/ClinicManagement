package com.lucasrama.clinic.controller;

import com.lucasrama.clinic.entity.Appointment;
import com.lucasrama.clinic.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

  private final AppointmentService appointmentService;

  @Autowired
  public AppointmentController(AppointmentService appointmentService){
    this.appointmentService = appointmentService;
  }
  // post /api/appointments, nuevo turno validando que no se superponga
  @PostMapping
  public ResponseEntity<Appointment> scheduleAppointment(@Valid @RequestBody Appointment appointment){
    Appointment savedAppointment = appointmentService.scheduleAppointment(appointment);
    return new ResponseEntity<>(savedAppointment, HttpStatus.CREATED);
  }

  //GET /api/appointments/patient/{patientId} obtenemos turnos especificos de pacientes
  @GetMapping("/patient/{patientId}")
  public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable Long patientId) {
    return new ResponseEntity<>(appointmentService.getAppointmentsByPatient(patientId), HttpStatus.OK);
  }

}
