package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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

    // configuration of authentication provider for spring security

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // object of user detail service
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailsService);
        // object of password encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // configuration

        // configured which urls will remain public and which urls will be secured
        httpSecurity.authorizeHttpRequests(authorize -> {
            // we could have applied filters individually on each route like this
            // authorize.requestMatchers("/home", "/register", "/login").permitAll();

            // or we can apply filters on all specified routes like this
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        // form default login
        // if we need to change anything related to form login, we'll come here
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
