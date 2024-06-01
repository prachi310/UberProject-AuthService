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

    public AuthService(PassengerRepository passengerRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.passengerRepository=passengerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PassengerDto signUpPassenger(PassengerSignUpDto passengerSignUpDto)
    {
        Passenger passenger =Passenger.builder().
                email(passengerSignUpDto.getEmail()).
                name(passengerSignUpDto.getName()).
                phoneNumber(passengerSignUpDto.getPhoneNumber())
                .password(bCryptPasswordEncoder.encode(passengerSignUpDto.getPassword()))
                .build();

       Passenger newPassenger = passengerRepository.save(passenger);
       return (PassengerDto.from(newPassenger));

    }
}
