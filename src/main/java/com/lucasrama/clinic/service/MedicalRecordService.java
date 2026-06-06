package com.lucasrama.clinic.service;

import com.lucasrama.clinic.entity.MedicalRecord;
import com.lucasrama.clinic.repository.MedicalRecordRepository;
import com.lucasrama.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MedicalRecordService {

  private final MedicalRecordRepository medicalRecordRepository;
  private final PatientRepository patientRepository;

  @Autowired
  public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository) {
    this.medicalRecordRepository = medicalRecordRepository;
    this.patientRepository = patientRepository;
  }

  // Creamos el historial medico
  public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {

    if (!patientRepository.existsById(medicalRecord.getPatient().getId())) {
      throw new IllegalArgumentException("Error: El paciente especificado no existe.");
    }

    Optional<MedicalRecord> existing = medicalRecordRepository.findByPatientId(medicalRecord.getPatient().getId());
    if (existing.isPresent()) {
      throw new IllegalArgumentException("Error: Este paciente ya tiene una historia clínica asignada.");
    }

    if(medicalRecord.getCreationDate() == null) {
      medicalRecord.setCreationDate(LocalDate.now());
    }

    return medicalRecordRepository.save(medicalRecord);
  }

  // Busca historial por id de paciente
  public Optional<MedicalRecord> getMedicalRecordByPatientId(Long patientId) {
    return medicalRecordRepository.findByPatientId(patientId);
  }

}
