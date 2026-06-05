package com.lucasrama.clinic.repository;

import com.lucasrama.clinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
  Optional<Doctor> findByLicenseNumber(String licenseNumber);
}
