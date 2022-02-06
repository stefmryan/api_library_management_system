package com.steffiecodes.lms.controllers;

import com.steffiecodes.lms.models.AuthenticationRequest;
import com.steffiecodes.lms.models.AuthenticationResponse;
import com.steffiecodes.lms.services.MyUserDetailsService;
import com.steffiecodes.lms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
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

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
