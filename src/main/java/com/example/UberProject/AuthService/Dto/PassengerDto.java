package com.example.UberProject.AuthService.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PassengerDto {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String createdAt;
}
