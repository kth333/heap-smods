package com.smods.backend.service;

import com.smods.backend.dto.LoginRequest;
import com.smods.backend.dto.UserDTO;
import com.smods.backend.exception.EmailAlreadyExistsException;
import com.smods.backend.exception.InvalidCharacterException;
import com.smods.backend.exception.UsernameAlreadyExistsException;
import com.smods.backend.model.User;
import com.smods.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Pattern NON_ENGLISH_PATTERN = Pattern.compile("[^\\p{ASCII}]");

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Register a new user
    public User registerUser(UserDTO userDTO) {
        validateInput(userDTO);

        // Check if username already exists
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists: " + userDTO.getUsername());
        }

        // Check if email already exists
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + userDTO.getEmail());
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setRole("USER");
        return userRepository.save(user);
    }

    private void validateInput(UserDTO userDTO) {
        if (NON_ENGLISH_PATTERN.matcher(userDTO.getUsername()).find()) {
            throw new InvalidCharacterException("Username contains invalid characters.");
        }

        if (NON_ENGLISH_PATTERN.matcher(userDTO.getEmail()).find()) {
            throw new InvalidCharacterException("Email contains invalid characters.");
        }

        // You can add more validation rules here as necessary
    }

    // Find a user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Login a user
    public boolean loginUser(LoginRequest loginRequest) {
        User user = findByUsername(loginRequest.getUsername());
        return user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
    }

    // Update user details
    public User updateUser(Long id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            if (!userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Find user by ID
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
