package com.lucasrama.clinic.repository;

import com.lucasrama.clinic.entity.HealthInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthInsuranceRepository extends JpaRepository<HealthInsurance, Long> {

  // verificar si una obra social ya existe antes de crearla
  Optional<HealthInsurance> findByName(String name);
}
