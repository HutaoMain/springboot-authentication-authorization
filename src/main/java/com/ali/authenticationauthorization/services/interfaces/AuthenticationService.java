package com.ali.authenticationauthorization.services.interfaces;

import com.ali.authenticationauthorization.dtos.LoginDto;
import com.ali.authenticationauthorization.dtos.LoginResponseDto;
import com.ali.authenticationauthorization.dtos.RegisterDto;
import com.ali.authenticationauthorization.models.User;

public interface AuthenticationService {
    User register(RegisterDto registerDto);

    LoginResponseDto login(LoginDto request);
}
