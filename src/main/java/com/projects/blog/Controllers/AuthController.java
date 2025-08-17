package com.projects.blog.Controllers;


import com.projects.blog.dtos.AuthResponse;
import com.projects.blog.dtos.LoginRequest;
import com.projects.blog.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {


    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
     UserDetails userDetails = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

     String tokenValue = authenticationService.generateJwtToken(userDetails);
     AuthResponse authResponse = AuthResponse.builder().token(tokenValue).expiresIn(86400).build();

     return ResponseEntity.ok(authResponse);
    }


}
