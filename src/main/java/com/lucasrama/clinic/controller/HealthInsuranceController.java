package com.lucasrama.clinic.controller;

import com.lucasrama.clinic.entity.HealthInsurance;
import com.lucasrama.clinic.service.HealthInsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/health-insurances")
public class HealthInsuranceController {

  private final HealthInsuranceService healthInsuranceService;

  @Autowired
  public HealthInsuranceController(HealthInsuranceService healthInsuranceService) {
    this.healthInsuranceService = healthInsuranceService;
  }

  @PostMapping
  public ResponseEntity<HealthInsurance> createHealthInsurance(@Valid @RequestBody HealthInsurance healthInsurance) {
    HealthInsurance saved = healthInsuranceService.createHealthInsurance(healthInsurance);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<HealthInsurance>> getAllHealthInsurances() {
    return new ResponseEntity<>(healthInsuranceService.getAllHealthInsurances(), HttpStatus.OK);
  }

}
