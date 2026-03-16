package com.example;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

import java.util.Date;

@Component
public class JwtUtil {

	
//	  @Value("${jwt.secret}")
//	    private String secret;
//
//	    @Value("${jwt.expiration}")
//	    private long expiration;
	
	 private final Key key = Keys.hmacShaKeyFor(
	            "MySuperSecretKeyForEmployeeManagementSystemProject2026!".getBytes()
	    );
	

	    public String generateToken(String username) {
	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
	                .signWith(key, SignatureAlgorithm.HS256)
	                .compact();
	    }

	   public Claims validateToken(String token) {
	    	 return Jwts.parserBuilder()
	                 .setSigningKey(key)
	                 .build()
	                 .parseClaimsJws(token)
	                 .getBody();
	    }
}
