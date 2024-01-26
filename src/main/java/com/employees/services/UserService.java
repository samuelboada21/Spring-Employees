package com.employees.services;

import com.employees.models.Position;
import com.employees.models.UserA;
import com.employees.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 
 * @author SamuelBoada
 */
@Service
public class UserService {

     @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private PositionService positionService;

    public UserA registerUser(UserA user) {
        // Encriptar la contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public UserA getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }
    
    public List<Position> getPositionsForUser(UserA user) {
        return positionService.getPositionsForManager(user);
    }
    
    public UserA findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
}

    
}
