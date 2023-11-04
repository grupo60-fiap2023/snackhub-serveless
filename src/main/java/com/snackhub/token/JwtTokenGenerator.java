package com.snackhub.token;

import com.snackhub.domain.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtTokenGenerator {

    public String generateToken(Customer customer) {
        Map<String, Object> claims = Map.of("id", customer.getId(), "cpf", customer.getCpf());
        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + 3600000); // Token válido por 1 hora

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
