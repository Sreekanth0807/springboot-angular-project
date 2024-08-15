package com.example.UserAuthentication.Security;

import com.example.UserAuthentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenGeneratorImp implements ISecurityTokenGenerator{
    @Override
    public String createToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId", user.getUserId());
        System.out.println("from token "+claims);
        return generateToken(claims,user.getUserId());
        //return generateToken(claims, String.valueOf(user.getUserId()));
    }

    public String generateToken(Map<String, Object> claims, String subject) {
        // Generate the token and set the customer id in the claims
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "mysecret")
                .compact();
    }

    }

