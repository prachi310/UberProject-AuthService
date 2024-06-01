package com.example.UberProject.AuthService.service;

import com.example.UberProject.AuthService.Repository.PassengerRepository;
import com.example.UberProject.AuthService.helpers.AuthPassengerDetail;
import com.example.UberProject.AuthService.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Passenger> passenger = passengerRepository.findPassengerByEmail(email);
        if(passenger.isPresent())
        {
            return new AuthPassengerDetail(passenger.get());
        }else {
            throw new UsernameNotFoundException("can not find passenger by given email");
        }
    }
}
