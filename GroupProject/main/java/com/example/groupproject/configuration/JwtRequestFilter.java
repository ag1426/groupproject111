package com.example.groupproject.configuration;

import com.example.groupproject.services.JwtService;
import com.example.groupproject.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return List.of("/products","/products/all").stream().anyMatch(path -> path.equals(request.getServletPath()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

      final String header = request.getHeader("Authorization");

      String jwtToken = null;
      String userName = null;

      if(header != null && header.startsWith("Bearer ")){
         jwtToken = header.substring(7);

         try {
            userName = jwtUtil.getUserNameFromToken(jwtToken);
         } catch (IllegalArgumentException e){
             System.out.println("Unable to get JWT token");
         }catch (ExpiredJwtException e){
             System.out.println("Jwt token is expired");
         }
      } else {
          System.out.println("Header = "+ header);
      }

      if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
          UserDetails userDetails = jwtService.loadUserByUsername(userName);

          if(jwtUtil.validateToken(jwtToken, userDetails)) {

             UsernamePasswordAuthenticationToken namePskAuthToken = new UsernamePasswordAuthenticationToken(
                     userDetails,
                     null,
                     userDetails.getAuthorities() );

             namePskAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

             SecurityContextHolder.getContext().setAuthentication(namePskAuthToken);

          }

      }

      filterChain.doFilter(request, response);

    }

}
