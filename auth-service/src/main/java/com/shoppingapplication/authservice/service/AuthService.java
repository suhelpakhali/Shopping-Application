package com.shoppingapplication.authservice.service;

import com.shoppingapplication.authservice.entity.UserCredentials;
import com.shoppingapplication.authservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String saveUser(UserCredentials credentials){
        credentials.setPassword(passwordEncoder.encode(credentials.getPassword()));
        userCredentialRepository.save(credentials);
        return "User is added successfully";
    }
}
