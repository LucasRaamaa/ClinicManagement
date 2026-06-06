package com.lucasrama.clinic.service;

import com.lucasrama.clinic.entity.Doctor;
import com.lucasrama.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

  private final DoctorRepository doctorRepository;

  @Autowired
  public DoctorService(DoctorRepository doctorRepository){
    this.doctorRepository = doctorRepository;
  }
  public Doctor registerDoctor(Doctor doctor){
    return doctorRepository.save(doctor);
  }
  public List<Doctor> getAllDoctor(){
    return doctorRepository.findAll();
  }

}
