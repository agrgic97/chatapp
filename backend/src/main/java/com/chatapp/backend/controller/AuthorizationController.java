package com.chatapp.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@EnableMethodSecurity
public class AuthorizationController {
    @GetMapping("/admin/resource")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> sayHelloAdmin() {
        return ResponseEntity.ok("Hello Admin!");
    }

    @GetMapping("/user/resource")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> sayHelloUser(Principal principal) {
        return ResponseEntity.ok("Hello " + principal.toString() + "!");
    }
}