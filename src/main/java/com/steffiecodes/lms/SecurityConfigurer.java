package com.steffiecodes.lms;

import com.steffiecodes.lms.filters.JwtRequestFilter;
import com.steffiecodes.lms.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    //using configure method to set values to authenticationmanagerbuilder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //providing amb a custom userdetailservice to authenticate
        //this compares what username, password, granted authorities
        //is coming in vs what is already stored in the db
        auth.userDetailsService(myUserDetailService);
    }

    //spring security expects user to already be authenticated before running authentication
    //(which is probably why I was able to call login but not be authenticated)
    //this method will ignore being authenticated before calling authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //everyone can call /authentic any other requests need to be authenticated first
                .authorizeRequests().antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()
                //tells spring security
                //not to create a session
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        ///adds jwtrequestfilter to this configure method, method comes before upaf filter is called
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //doesn't do any hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("GET", "POST", "OPTIONS", "PUT")
//                        .allowedOrigins("http://localhost:3000");
//            }
//        };
//    }


}