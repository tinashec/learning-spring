package com.tuck.shop.service;

import com.tuck.shop.users.entity.Users;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author Tinashe on 26/10/2023
 * see: https://youtu.be/KYNR5js2cXE?si=oIhloYmRMIZ1bz6u dvega
 */

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }


    public String generateToken(Users auth){
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder().
                issuer("self").
                issuedAt(now).
                expiresAt(now.plus(1, ChronoUnit.HOURS)).
                subject(auth.getFirstName()).
                build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}
