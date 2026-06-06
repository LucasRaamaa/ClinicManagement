package com.lucasrama.clinic.controller;

import com.lucasrama.clinic.entity.Patient;
import com.lucasrama.clinic.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

  private final PatientService patientService;

  @Autowired
  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping
  public ResponseEntity<Patient> registerPatient(@Valid @RequestBody Patient patient) {
    Patient savedPatient = patientService.registerPatient(patient);
    return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Patient>> getAllPatients() {
    return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
  }

}
