package com.example.UberProject.AuthService.service;
import com.example.UberProject.AuthService.Dto.PassengerDto;
import com.example.UberProject.AuthService.Dto.PassengerSignUpDto;
import com.example.UberProject.AuthService.Repository.PassengerRepository;
import com.example.UberProject.AuthService.model.Passenger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PassengerRepository passengerRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PassengerDto signupPassenger(PassengerSignUpDto passengerSignupRequestDto) {
        Passenger passenger = Passenger.builder()
                .email(passengerSignupRequestDto.getEmail())
                .name(passengerSignupRequestDto.getName())
                .password(bCryptPasswordEncoder.encode(passengerSignupRequestDto.getPassword())) // TODO: Encrypt the password
                .phoneNumber(passengerSignupRequestDto.getPhoneNumber())
                .build();

        Passenger newPassenger = passengerRepository.save(passenger);

        return PassengerDto.from(newPassenger);
    }
}