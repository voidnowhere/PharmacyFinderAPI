package dev.voidnowhere.pharmacymanagementapi.auh;

import dev.voidnowhere.pharmacymanagementapi.config.JwtService;
import dev.voidnowhere.pharmacymanagementapi.entities.User;
import dev.voidnowhere.pharmacymanagementapi.enums.UserRole;
import dev.voidnowhere.pharmacymanagementapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public ResponseEntity<?> register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists!"));
        }
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.PHARMACIST)
                .build();
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(403).build();
        }
        var jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .role(user.getRole().name().substring(0, 1))
                        .token(jwtToken)
                        .build()
        );
    }
}
