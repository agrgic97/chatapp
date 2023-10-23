package com.chatapp.backend.service.interfaces;

import com.chatapp.backend.dao.request.SignInRequest;
import com.chatapp.backend.dao.request.SignUpRequest;
import com.chatapp.backend.dao.response.JwtAuthenticationResponse;

import javax.security.sasl.AuthenticationException;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request) throws AuthenticationException;
}
