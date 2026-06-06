package com.lucasrama.clinic.service;

import com.lucasrama.clinic.entity.HealthInsurance;
import com.lucasrama.clinic.repository.HealthInsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthInsuranceService {
  private final HealthInsuranceRepository healthInsuranceRepository;

  @Autowired
  public HealthInsuranceService(HealthInsuranceRepository healthInsuranceRepository) {
    this.healthInsuranceRepository = healthInsuranceRepository;
  }

  // Ceamos la O.S
  public HealthInsurance createHealthInsurance(HealthInsurance healthInsurance) {
    Optional<HealthInsurance> existing = healthInsuranceRepository.findByName(healthInsurance.getName());

    if (existing.isPresent()) {
      // ¡Este error ahora será atrapado hermosamente por nuestro GlobalExceptionHandler!
      throw new IllegalArgumentException("Error: Ya existe una obra social registrada con el nombre '" + healthInsurance.getName() + "'.");
    }

    return healthInsuranceRepository.save(healthInsurance);
  }

  //listamos todas las O.S
  public List<HealthInsurance> getAllHealthInsurances() {
    return healthInsuranceRepository.findAll();
  }

}
