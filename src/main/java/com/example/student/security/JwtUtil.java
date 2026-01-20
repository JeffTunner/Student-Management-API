package com.example.student.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
     private final String SECRET_KEY = "my_super_secret_key_which_is_at_least_32_bytes_long";

     public String extractUsername(String token) {
         return extractAllClaims(token).getSubject();
     }

     public boolean isTokenValid(String token) {
         try {
             extractAllClaims(token);
             return true;
         } catch (Exception e) {
             return false;
         }
     }

     public Claims extractAllClaims(String token) {
         return Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).build().
                 parseClaimsJws(token).getBody();
     }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }
}
