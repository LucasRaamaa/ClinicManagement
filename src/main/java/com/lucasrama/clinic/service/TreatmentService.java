package com.lucasrama.clinic.service;

import com.lucasrama.clinic.entity.Treatment;
import com.lucasrama.clinic.repository.MedicalRecordRepository;
import com.lucasrama.clinic.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

  private final TreatmentRepository treatmentRepository;
  private final MedicalRecordRepository medicalRecordRepository;

  @Autowired
  public TreatmentService(TreatmentRepository treatmentRepository, MedicalRecordRepository medicalRecordRepository){
    this.treatmentRepository = treatmentRepository;
    this.medicalRecordRepository = medicalRecordRepository;
  }

  //Creamos el tratamiento
  public Treatment createTreatment(Treatment treatment){
    if (!medicalRecordRepository.existsById(treatment.getMedicalRecord().getId())) {
      throw new IllegalArgumentException("Error: La historia clínica especificada no existe.");
    }
    return treatmentRepository.save(treatment);
  }

  //Historial medico
  public List<Treatment> getTreatmentsByMedicalRecordId(Long medicalRecordId) {
    return treatmentRepository.findByMedicalRecordId(medicalRecordId);
  }

}
