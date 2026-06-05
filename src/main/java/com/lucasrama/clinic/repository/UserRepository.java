package com.lucasrama.clinic.repository;

import com.lucasrama.clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // Spring Data JPA crea la consulta SQL automáticamente solo con leer el nombre del método
  Optional<User> findByEmail(String email);
}
