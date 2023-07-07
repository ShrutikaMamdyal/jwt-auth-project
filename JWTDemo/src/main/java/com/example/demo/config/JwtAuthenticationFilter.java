package com.example.demo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.utils.JWTUtill;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	JWTUtill jwtUtill;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String requestHeaderToken = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		
		if(requestHeaderToken != null && requestHeaderToken.startsWith("Bearer")) {
			jwtToken = requestHeaderToken.substring(7);
			
			try {
				username = this.jwtUtill.getUsernameFromToken(jwtToken);
			}catch (Exception e) {
				// TODO: handle exception
			}
			UserDetails userDetails= this.customUserDetailsService.loadUserByUsername(username);
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				System.out.println("Token is not validated");
			}
			
		}
		filterChain.doFilter(request, response);
	}
	
}
