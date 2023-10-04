package com.chatapp.backend.controller;

import com.chatapp.backend.dto.AuthRequest;
import com.chatapp.backend.dto.UserDTO;
import com.chatapp.backend.exception.UserAlreadyExistsException;
import com.chatapp.backend.service.JwtService;
import com.chatapp.backend.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Welcome this endpoint is not secure", HttpStatus.OK);
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserDTO userDTO) throws UserAlreadyExistsException {
        return service.addUser(userDTO);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile(Principal principal) {
        return "Welcome " + principal.getName();
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(jwtService.generateToken(authRequest.getUsername()), HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }

    @PostMapping("/exists/username")
    public ResponseEntity<Boolean> usernameExists(@RequestBody String input) {
        if (input.length() < 3) {
            throw new IllegalArgumentException("Parameter too short! 3 letters required minimum.");
        }
        return new ResponseEntity<>(service.existsByUsername(input), HttpStatus.OK);
    }

    @PostMapping("/exists/email")
    public ResponseEntity<Boolean> emailExists(@RequestBody String input) {
        return new ResponseEntity<>(service.existsByEmail(input), HttpStatus.OK);
    }
}
