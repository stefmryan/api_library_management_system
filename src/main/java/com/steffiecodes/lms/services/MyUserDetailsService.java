package com.steffiecodes.lms.services;

import com.steffiecodes.lms.MyUserDetails;
import com.steffiecodes.lms.models.AppUser;
import com.steffiecodes.lms.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AppUserRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //spring securities User
        //by default User takes in username and password and list of granted authorities
        //return new User("foo", "foo", new ArrayList<>());
        //get user from a repo

       AppUser user = appUserRepository.findByEmail(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }
}
