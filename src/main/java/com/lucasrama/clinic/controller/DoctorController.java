package com.lucasrama.clinic.controller;

import com.lucasrama.clinic.entity.Doctor;
import com.lucasrama.clinic.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {


  private final DoctorService doctorService;
  @Autowired
  public DoctorController(DoctorService doctorService){
    this.doctorService = doctorService;
  }


  @PreAuthorize("hasAuthority('ADMIN')")
  @PostMapping
  public ResponseEntity<Doctor> registerDoctor(@Valid @RequestBody Doctor doctor){
    Doctor saveDoctor = doctorService.registerDoctor(doctor);
    return new ResponseEntity<>(saveDoctor, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Doctor>> getAllDoctors(){
    return new ResponseEntity<>(doctorService.getAllDoctor(), HttpStatus.OK);
  }

}
