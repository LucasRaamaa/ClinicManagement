package com.lucasrama.clinic.repository;

import com.lucasrama.clinic.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
  // Para obtener todo el historial de visitas médicas de una sola historia clínica
  List<Treatment> findByMedicalRecordId(Long medicalRecordId);
}
