package com.bang.authservice.security;

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
            user.getRoles().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }
}