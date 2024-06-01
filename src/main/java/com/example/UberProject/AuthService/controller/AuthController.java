package com.example.UberProject.AuthService.controller;

import com.example.UberProject.AuthService.Dto.AuthRequestDto;
import com.example.UberProject.AuthService.Dto.AuthResponseDto;
import com.example.UberProject.AuthService.Dto.PassengerDto;
import com.example.UberProject.AuthService.Dto.PassengerSignUpDto;
import com.example.UberProject.AuthService.service.AuthService;
import com.example.UberProject.AuthService.service.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${cookie.expiry}")
    private Integer cookieExpiry;

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private AuthController(AuthService authService, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authService= authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signUp/passenger")
    public ResponseEntity<PassengerDto> signUpPassenger(@RequestBody PassengerSignUpDto passengerSignUpDto)
    {
        PassengerDto passengerDto = authService.signUpPassenger(passengerSignUpDto);
        return new ResponseEntity<>(passengerDto, HttpStatus.OK);
        }

    @PostMapping("/signIn/passenger")
    public ResponseEntity<?> signInPassenger(@RequestBody AuthRequestDto authRequestDto,
                                             HttpServletResponse httpServletResponse)
    {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(),authRequestDto.getPassword()));
        if(authentication.isAuthenticated()) {
            String jwtToken = jwtService.CreateToken(authRequestDto.getEmail());
            ResponseCookie cookie = ResponseCookie.from("JWTtoken",jwtToken)
                    .httpOnly(true).secure(false).maxAge(cookieExpiry)
                    .path("/").build();
            httpServletResponse.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
            return new ResponseEntity<>(AuthResponseDto.builder().success(true).build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>("Auth not successful",HttpStatus.NOT_FOUND);
    }

}