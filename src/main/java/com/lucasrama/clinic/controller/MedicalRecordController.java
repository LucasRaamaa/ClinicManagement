package com.lucasrama.clinic.controller;

import com.lucasrama.clinic.entity.MedicalRecord;
import com.lucasrama.clinic.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

  private final MedicalRecordService medicalRecordService;

  @Autowired
  public MedicalRecordController(MedicalRecordService medicalRecordService) {
    this.medicalRecordService = medicalRecordService;
  }

  @PostMapping
  public ResponseEntity<MedicalRecord> createMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) {
    MedicalRecord saved = medicalRecordService.createMedicalRecord(medicalRecord);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @GetMapping("/patient/{patientId}")
  public ResponseEntity<MedicalRecord> getMedicalRecordByPatientId(@PathVariable Long patientId) {
    return medicalRecordService.getMedicalRecordByPatientId(patientId)
        .map(record -> new ResponseEntity<>(record, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
