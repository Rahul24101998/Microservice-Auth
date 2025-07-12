package com.demo.authentication_server.controller;

import com.demo.authentication_server.model.utils.AuthRequest;
import com.demo.authentication_server.model.utils.UserInput;
import com.demo.authentication_server.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/register")
    public String registerUser(@RequestBody UserInput user) {
        return authService.SaveUser(user);
    }


    @PostMapping("/getToken")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUserName(),
                            authRequest.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                String token = authService.GenerateToken(authRequest);
                return ResponseEntity.ok(token);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }


    @GetMapping("/refreshToken")
    public String refreshToken(@RequestParam String token) {
        return authService.RefreshToken(token);
    }

    @PostMapping("/validateToken")
    public String validateToken(@RequestParam String token) {
        authService.validateToken(token);
        return "Token is valid";
    }

}
