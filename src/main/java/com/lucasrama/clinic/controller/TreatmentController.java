package com.lucasrama.clinic.controller;

import com.lucasrama.clinic.entity.Treatment;
import com.lucasrama.clinic.service.TreatmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

  private final TreatmentService treatmentService;

  @Autowired
  public TreatmentController(TreatmentService treatmentService) {
    this.treatmentService = treatmentService;
  }

  @PostMapping
  public ResponseEntity<Treatment> createTreatment(@Valid @RequestBody Treatment treatment) {
    Treatment saved = treatmentService.createTreatment(treatment);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @GetMapping("/medical-record/{medicalRecordId}")
  public ResponseEntity<List<Treatment>> getTreatmentsByMedicalRecordId(@PathVariable Long medicalRecordId) {
    return new ResponseEntity<>(treatmentService.getTreatmentsByMedicalRecordId(medicalRecordId), HttpStatus.OK);
  }

}
