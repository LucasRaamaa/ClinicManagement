package com.lucasrama.clinic.service;

import com.lucasrama.clinic.entity.User;
import com.lucasrama.clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  //inyectamos dependencias mediante constructor
  @Autowired
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  //Registro de usuario: creamos un nuevo usuario
  public User registerUser(User user){
    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

    if(existingUser.isPresent()){
      throw new IllegalArgumentException("Error: el mail " + user.getEmail()+  " ya esta registrado.");
    }
    // aca agregamos encriptar la contraseña + adelante
    return userRepository.save(user);
  }

  //Obtenemos todos los usuarios
  public List<User> getAllUsers(){
    return userRepository.findAll();
  }

  // filtramos por ID
  public Optional<User> getUserById(Long id){
    return userRepository.findById(id);
  }
}
