package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.scm.services.impl.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {

    // user create and login using java with InMemoryService

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     // withDefaultPasswordEncoder() is deprecated, can use only for testing purpose
    //     UserDetails user1 = User.withDefaultPasswordEncoder()
    //                             .username("sidkt")
    //                             .password("sidkt")
    //                             .roles("ADMIN", "USER")
    //                             .build();

    //     UserDetails user2 = User.withDefaultPasswordEncoder()
    //                             .username("user")
    //                             .password("pass")
    //                             // .roles("USER")
    //                             .build();
    //     // we're able to return the object of InMemoryUserDetailsManager instead of UserDetailsService
    //     // because InMemoryUserDetailsManager implements UserDetailsService 
    //     // and is a child of UserDetailsService
    //     return new InMemoryUserDetailsManager(user1, user2);
    // }

    @Autowired
    private SecurityCustomUserDetailsService securityCustomUserDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
