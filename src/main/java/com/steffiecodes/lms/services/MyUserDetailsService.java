package com.steffiecodes.lms.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MyUserDetailsService implements UserDetailsService {
    //takes in a username and loads user from wherever user is saved
    //look up how to find user in postgres db


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //spring securities User
        //by default User takes in username and password and list of granted authorities
        return new User("foo", "foo", new ArrayList<>());
        //get user from a repo
    }
}
