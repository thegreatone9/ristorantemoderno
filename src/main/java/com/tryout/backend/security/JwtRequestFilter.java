package com.tryout.backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tryout.backend.services.RistoranteUserServiceImpl;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private RistoranteUserServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");

		String username = null;
		String jwt = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			System.out.println("jwt received: " + jwt);
			username = jwtUtil.extractUsername(jwt);
			System.out.println("corresponding username: " + username);
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) System.out.println("jwt matched...now proceeding");
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			System.out.println("jwtUtil ABOUT to be validated...wait for more regarding:  " + userDetails.getUsername());
			if (jwtUtil.validateToken(jwt, userDetails)) {
				System.out.println("jwtUtil validated!");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else {
				System.out.println("oops...");
			}
		}
		chain.doFilter(request, response);
	}

}
