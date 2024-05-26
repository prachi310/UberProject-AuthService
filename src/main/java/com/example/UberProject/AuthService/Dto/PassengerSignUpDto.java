package com.example.UberProject.AuthService.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PassengerSignUpDto {

    private String email;
    private String password;
    private String phoneNumber;
    private String name;

}
