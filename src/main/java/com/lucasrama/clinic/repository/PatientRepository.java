package com.lucasrama.clinic.repository;

import com.lucasrama.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
  Optional<Patient> findByDni(String dni);
}