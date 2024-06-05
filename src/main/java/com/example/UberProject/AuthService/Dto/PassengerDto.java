package com.example.UberProject.AuthService.Dto;

import com.example.UberProject_EntityService.models.Passenger;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PassengerDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private Date createdAt;

    public static PassengerDto from(Passenger passenger)
    {
        PassengerDto result = PassengerDto.builder()
                .name(passenger.getName())
                .email(passenger.getEmail())
                .phoneNumber(passenger.getPhoneNumber()).
                id(passenger.getId())
                .password(passenger.getPassword())
                .createdAt(passenger.getCreatedAt())
                .build();
        return result;
    }
}
