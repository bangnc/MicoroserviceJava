package com.bang.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bang.authservice.entity.User;
import org.springframework.util.CollectionUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;


@Service
public class JwtService {


    private final SecretKey key;

    private final long expiration;


    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration) {

        this.key = Keys.hmacShaKeyFor(
                secret.getBytes()
        );

        this.expiration = expiration;
    }



    public String generateToken(User user) {


        return Jwts.builder()

                .subject(user.getUserName())

                .claim("userId", user.getId())

                .claim("email", user.getEmail())
                .claim("scope", buildScope(user))
                .claim("jit", UUID.randomUUID().toString())

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + expiration
                        )
                )

                .signWith(key)

                .compact();
    }



    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }



    public boolean validateToken(String token) throws JwtException {

        Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

        return true;

    }
    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())){
           user.getRoles().forEach(role -> {
               stringJoiner.add("ROLE_" + role.getName());
               if(!CollectionUtils.isEmpty((role.getPermissions()))) {
                   role.getPermissions().forEach(permission -> {
                       stringJoiner.add(permission.getName());
                   });
               }
           });
        }
        return stringJoiner.toString();
    }

    public Claims verifyToken(String token) {

        Jws<Claims> claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

        return claims.getPayload();
    }
}