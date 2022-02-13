package com.steffiecodes.lms.filters;

import com.steffiecodes.lms.services.MyUserDetailsService;
import com.steffiecodes.lms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

//intercepts the request and examines the Header of that request
@Component//in springs radar to autowire this
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;


    //examines the request for the jwt in the header
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //if valid, this var should contain "Bearer jwtstring"
        //final String authorizationHeader = request.getHeader("Authorization");
        //System.out.println(authorizationHeader);

        //grabbing cookie and setting token value
        Cookie cookie = WebUtils.getCookie(request, "LibraryCookie");

        if(cookie != null) {
            //System.out.println(cookie);
            //assert cookie != null;
            String token = cookie.getValue();

            String username = null;
            String jwt = token;

            //extracting username to validate token
            username = jwtUtil.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    //creating upat (the default token)
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
