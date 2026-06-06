package com.lucasrama.clinic.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import org.springframework.cglib.core.Local;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
  private int status;
  private String message;
  private LocalDateTime timestamp;

}
