package com.example.bill_management.config;

import com.example.bill_management.services.JwtService;
import com.example.bill_management.services.MyUserDetailService;
import com.nimbusds.jose.JOSEException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        boolean errorToken = false;

        // CHECK VALID TOKEN
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            try {
                username = jwtService.extractUsername(token);
            } catch (ParseException e) {
                errorToken = true;
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = myUserDetailService.loadUserByUsername(username);

            try{
                if (jwtService.validateToken(token, userDetails.getUsername())){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (ParseException | JOSEException e) {
                errorToken = true;
            }
        }
        filterChain.doFilter(request, response);
    }
}
