package com.lucasrama.clinic.service;

import com.lucasrama.clinic.entity.Patient;
import com.lucasrama.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

  private final PatientRepository patientRepository;

  @Autowired
  public PatientService(PatientRepository patientRepository){
    this.patientRepository = patientRepository;
  }
  public Patient registerPatient(Patient patient){
    return patientRepository.save(patient);
  }
  public List<Patient> getAllPatients(){
    return patientRepository.findAll();
  }

}
