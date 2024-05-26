package com.example.UberProject.AuthService.controller;

import com.example.UberProject.AuthService.Dto.PassengerDto;
import com.example.UberProject.AuthService.Dto.PassengerSignUpDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {

    @PostMapping("/signUp/passenger")
    public ResponseEntity<PassengerDto> signUpPassenger(@RequestBody PassengerSignUpDto passengerSignUpDto)
    {

        return null;
    }
}
