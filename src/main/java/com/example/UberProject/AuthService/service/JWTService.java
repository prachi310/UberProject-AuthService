package com.example.UberProject.AuthService.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt.expiry}")
    public Integer expiry;

    @Value("${jwt.secret}")
    public String secret;

    public String CreateToken(Map<String,Object> payload,String email)
    {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry*1000L);
        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiryDate)
                .subject(email)
                .signWith(getSignedKey())
                .compact();
    }


    public String CreateToken(String email) {
        return CreateToken(new HashMap<>(),email);
    }

    public Claims extractAllPayloads(String token)
    {
        return Jwts.parser()
                .setSigningKey(getSignedKey())
                .build().parseClaimsJws(token)
                .getBody();
    }

    public  <T> T extractClaims(String token , Function<Claims,T> claimResolver)
    {
        final Claims claims = extractAllPayloads(token);
        return claimResolver.apply(claims);
    }

    public Boolean isTokenExpired(String token)
    {
      return extractExpirationDate(token).before(new Date());
    }

    public String extractEmail(String token)
    {
        return extractClaims(token,Claims::getSubject);
    }

    public Date extractExpirationDate(String token)
    {
      return extractClaims(token, Claims::getExpiration);
    }

    public Object extractPayload(String token,String payloadKey)
    {
        Claims claims =extractAllPayloads(token);
        return (Object) claims.get(payloadKey);
    }

    public SecretKey getSignedKey()
    {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Boolean validateToken(String email,String token)
    {
        final  String userEmailFetchedFromToken = extractEmail(token);
        return (userEmailFetchedFromToken.equals(email) && isTokenExpired(token));
    }
}
