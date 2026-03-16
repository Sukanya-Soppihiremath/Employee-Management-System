package com.example;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            try {

                String token = authHeader.substring(7);

                Claims claims = jwtUtil.validateToken(token);

                String username = claims.getSubject();

                // Set authentication
//                var authentication =
//                        new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
//                                username,
//                                null,
//                                java.util.Collections.emptyList()
//                        );
//
//                org.springframework.security.core.context.SecurityContextHolder
//                        .getContext()
//                        .setAuthentication(authentication);
                String role = claims.get("role", String.class);
                var authentication =
                        new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                java.util.Collections.singletonList(
//                                        new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_EMPLOYEES")
                                		 new SimpleGrantedAuthority(role)
                                )
                        );

                org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);

            } catch (Exception e) {

                // 🔥 If token invalid — just reject request safely
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}