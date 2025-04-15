package com.humber.WebProject.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "iuhavbsdliuyvqidufyqvkdu6i75fgoduybasiyud5rtfciqhbdkasyd";

    // Use recommended secure algorithm
    private static final MacAlgorithm ALGORITHM = Jwts.SIG.HS256;

    // Convert SECRET_KEY to a signing key
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ✅ Generate JWT Token using new API
    public String generateToken(String email, String firstName) {
        return Jwts.builder()
                .claim("email", email) // ✅ Optional, already in subject, but useful
                .claim("firstName", firstName) // ✅ Adding first name claim
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiry
                .signWith(getSigningKey(), ALGORITHM)
                .compact();
    }

    // ✅ Validate JWT Token using new API
    public Claims extractClaims(String token) {
        JwtParser parser = Jwts.parser()  // ✅ FIXED: Use Jwts.parser()
                .verifyWith(getSigningKey())  // ✅ FIXED: Using verifyWith() instead of setSigningKey()
                .build();

        Jwt<?, Claims> jwt = parser.parseSignedClaims(token); // ✅ FIXED: Use parseSignedClaims()
        return jwt.getPayload(); // ✅ FIXED: Use getPayload() instead of getBody()
    }

    // ✅ Check if token is expired
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // ✅ Get email from token
    public String getEmailFromToken(String token) {
        return extractClaims(token).getSubject();
    }
}
