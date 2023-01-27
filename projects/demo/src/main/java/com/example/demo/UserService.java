package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User login(String userName, String password) {
    return userRepository.findByUserNameAndPassword(userName, password);
  }
}