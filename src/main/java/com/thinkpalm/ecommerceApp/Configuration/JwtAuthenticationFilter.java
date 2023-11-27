package com.thinkpalm.ecommerceApp.Configuration;

import com.thinkpalm.ecommerceApp.Repository.TokenRepo;
import com.thinkpalm.ecommerceApp.Service.JwtService;
import com.thinkpalm.ecommerceApp.Util.AppContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final TokenRepo tokenRepo;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(
          @jakarta.validation.constraints.NotNull HttpServletRequest request,
          @jakarta.validation.constraints.NotNull HttpServletResponse response,
          @NotNull FilterChain filterChain
    )
            throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if ((authHeader==null||!authHeader.startsWith("Bearer "))){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            var isTokenValid=tokenRepo.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            if(jwtService.isTokenValid(jwt,userDetails)&&isTokenValid){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                AppContext.setEmail(userEmail);
            }
        }
        filterChain.doFilter(request,response);

    }
}