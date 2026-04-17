package com.example.demospringboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import com.example.demospringboot.models.user;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class userController {

    @Autowired
    private com.example.demospringboot.repos.UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping({"/api/users", "/user"})
    public ResponseEntity<Optional<user>> createUser(@RequestBody user newUser) {
        user existinguser = userRepository.findByEmail(newUser.getEmail());
        if (existinguser != null) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.CONFLICT);
        }

        // Hash the password before saving so login works!
        if (newUser.getPasswordHash() != null) {
            String hashedPassword = passwordEncoder.encode(newUser.getPasswordHash());
            newUser.setPasswordHash(hashedPassword);
        }

        user savedUser = userRepository.save(newUser);
        return new ResponseEntity<>(Optional.of(savedUser), HttpStatus.CREATED);
    }

    @GetMapping("/api/users")
    public List<user> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
    }

    @GetMapping("/api/users/{id}")
    public Optional<user> getUserById(@NonNull @PathVariable Long id) {
        return userRepository.findById(id);
    }

    @DeleteMapping("/api/users/{id}")
    public String deleteUserById(@NonNull @PathVariable Long id) {
        userRepository.deleteById(id);
        return "user deleted successfully";
    }

    @GetMapping("/api/users/email/{email}")
    public user getUserByEmail(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }
}
