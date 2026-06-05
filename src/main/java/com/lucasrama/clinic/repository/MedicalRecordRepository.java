package com.lucasrama.clinic.repository;

import com.lucasrama.clinic.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
  // Metodo clave para buscar la historia clínica directamente por el ID del paciente
  Optional<MedicalRecord> findByPatientId(Long patientId);
}