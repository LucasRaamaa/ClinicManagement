package com.lucasrama.clinic.controller;

import com.lucasrama.clinic.entity.Role;
import com.lucasrama.clinic.entity.User;
import com.lucasrama.clinic.service.JwtService;
import com.lucasrama.clinic.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;

  public AuthController(UserService userService, JwtService jwtService,
      AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));


    if (user.getRole() == null) {
      user.setRole(Role.PATIENT);
    }

    User savedUser = userService.registerUser(user);

    String jwtToken = jwtService.generateToken(savedUser);
    return ResponseEntity.status(201).body(new AuthResponse(jwtToken));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.email(), request.password())
    );
    UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());

    String jwtToken = jwtService.generateToken(userDetails);

    return ResponseEntity.ok(new AuthResponse(jwtToken));
  }

  public record LoginRequest(String email, String password) {}
  public record AuthResponse(String token) {}
}