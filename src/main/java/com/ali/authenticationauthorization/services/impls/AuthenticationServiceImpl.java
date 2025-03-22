package com.ali.authenticationauthorization.services.impls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ali.authenticationauthorization.dtos.LoginDto;
import com.ali.authenticationauthorization.dtos.LoginResponseDto;
import com.ali.authenticationauthorization.dtos.RegisterDto;
import com.ali.authenticationauthorization.models.User;
import com.ali.authenticationauthorization.repositories.UserRepository;
import com.ali.authenticationauthorization.services.interfaces.AuthenticationService;
import com.ali.authenticationauthorization.services.interfaces.JwtService;
import com.ali.authenticationauthorization.utils.enums.RoleCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    @Override
    public User register(RegisterDto registerDto) {
        User user = User.builder().firstName(registerDto.getFirstName()).lastName(registerDto.getLastName())
                .email(registerDto.getEmail()).password(passwordEncoder.encode(registerDto.getPassword()))
                .role(RoleCode.USER).build();
        return userRepository.save(user);
    }

    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String jwt = jwtService.generateToken(user);
        LoginResponseDto loginResponse = LoginResponseDto
                .builder()
                .token(jwt)
                .expiresIn(jwtExpiration).build();
        return loginResponse;
    }
}
