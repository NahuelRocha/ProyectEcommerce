package com.ecommerce.auth;

import com.ecommerce.config.jwtService.JwtService;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {

        Optional<User> findEmail = repository.findByEmail(request.getEmail());
        Optional<User> existingUsername = repository.findByUsername(request.getUsername());

        if (existingUsername.isEmpty() && findEmail.isEmpty()) {

            var user = User.builder()
                    .username(request.getUsername())
                    .firstName(request.getFirstname())
                    .lastName(request.getLastname())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .role(Role.USER)
                    .password(passwordEncoder.encode(request.getPassword()))
                    .purchase(new ArrayList<>())
                    .build();

            repository.save(user);

            var jwtToken = jwtService.generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();

        } else if (existingUsername.isEmpty()){

            throw new IllegalArgumentException("The email already exists in the database");

        } else throw new IllegalArgumentException("The username already exists in the database");

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public String promoteEmployeeToAdmin(String username){

        User findUser = repository.findByUsername(username)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        if (findUser.getRole().equals(Role.ADMIN)){
            return "The user is already an administrator";
        }

        findUser.setRole(Role.ADMIN);

        repository.save(findUser);

        return "SUCCESS";
    }

}
