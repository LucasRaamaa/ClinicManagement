package com.lucasrama.clinic.controller;

import com.lucasrama.clinic.entity.User;
import com.lucasrama.clinic.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }
  //POST /api/users -> registra un nuevo usuario en el sistema
  @PostMapping
  public ResponseEntity<User> registerUser(@Valid @RequestBody User user){
    User savedUser = userService.registerUser(user);
    return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  /* GET /api/users -> lista todo los usuarios */
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

}
