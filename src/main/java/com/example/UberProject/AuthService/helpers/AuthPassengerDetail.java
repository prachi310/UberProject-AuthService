package com.example.UberProject.AuthService.helpers;

import com.example.UberProject_EntityService.models.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

//we need this class because spring security works on user Detail polymorphic type for auth
public class AuthPassengerDetail extends Passenger implements UserDetails {

    private final String userName;
    private String password;

    public AuthPassengerDetail(Passenger passenger) {
        this.userName =passenger.getEmail();
        this.password=passenger.getPassword();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
