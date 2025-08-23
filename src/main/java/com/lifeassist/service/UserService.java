package com.lifeassist.service;

import com.lifeassist.config.JwtService;
import com.lifeassist.dto.AuthResponse;
import com.lifeassist.dto.RegisterRequest;
import com.lifeassist.dto.UserDto;
import com.lifeassist.entity.Role;
import com.lifeassist.entity.User;
import com.lifeassist.exception.EmailAlreadyExistsException;
import com.lifeassist.exception.UserNotFoundException;
import com.lifeassist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Register a new user.
     */
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole() != null ? request.getRole() : Role.CARETAKER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return buildAuthResponse(user, jwtToken);
    }

    /**
     * Authenticate user with email and password.
     */
    public AuthResponse authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        var jwtToken = jwtService.generateToken(user);
        return buildAuthResponse(user, jwtToken);
    }

    /**
     * Fetch all users.
     */
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Fetch user by ID.
     */
    public UserDto getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return convertToDto(user);
    }

    /**
     * Fetch user by Email.
     */
    public UserDto getUserByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return convertToDto(user);
    }

    // 🔹 Helper method to build AuthResponse
    private AuthResponse buildAuthResponse(User user, String token) {
        return AuthResponse.builder()
                .token(token)
                .tokenType("JWT Bearer")
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    // 🔹 Helper method to convert User -> UserDto
    private UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .build();
    }
}
