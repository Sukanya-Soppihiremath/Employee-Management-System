package com.example;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend
public class AuthController {

    @Value("${jwt.secret}")
    private String SECRET;

    // ===== LOGIN API =====
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        // 🔐 Temporary hardcoded authentication
        if ("admin".equals(request.getUsername()) &&
            "123456".equals(request.getPassword())) {

            return generateToken(request.getUsername());
        }

        return "Invalid Credentials";
    }

    // ===== TOKEN GENERATION =====
    private String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(getSigningKey())
                .compact();
    }

    // ===== Secure Signing Key =====
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
}