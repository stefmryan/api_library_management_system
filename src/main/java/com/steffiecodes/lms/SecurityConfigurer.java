package com.steffiecodes.lms;

import com.steffiecodes.lms.filters.JwtRequestFilter;
import com.steffiecodes.lms.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
        http.cors().and().csrf().disable();
        http
                ///adds jwtrequestfilter to this configure method, method comes before upaf filter is called
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

                //everyone can call /authentic any other requests need to be authenticated first
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/authenticate").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/library-accounts/**", "/checkout/**").hasAuthority("MANAGER")
                .anyRequest().authenticated()

                //tells spring security
                //not to create a session
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);






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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.HEAD.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        corsConfiguration.setMaxAge(1800L);
        source.registerCorsConfiguration("/**", corsConfiguration); // you restrict your path here
        return source;
    }


}