package com.steffiecodes.lms.controllers;

import com.steffiecodes.lms.models.AuthenticationRequest;
import com.steffiecodes.lms.services.MyUserDetailsService;
import com.steffiecodes.lms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true", maxAge = 3600)
@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    //autowired keeps giving me a "Consider defining a bean of type
    // 'com.steffiecodes.springsecurityjwt.util.JWTUtil' in your configuration.
    //look into later
    @Autowired
    private JWTUtil jwtTokenUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        Cookie cookie = new Cookie("LibraryCookie", jwt);
        cookie.setMaxAge(60*60*2);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
       return ResponseEntity.ok(authenticationRequest);
    }

    @GetMapping(value = "/signout")
    public ResponseEntity getLogout(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws Exception {

        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContextHolder.clearContext();
        ((SecurityContext) context).setAuthentication(null);
        if (response != null) {
            Cookie cookie = new Cookie("JSESSIONID", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return ResponseEntity.noContent().build();
    }
}
