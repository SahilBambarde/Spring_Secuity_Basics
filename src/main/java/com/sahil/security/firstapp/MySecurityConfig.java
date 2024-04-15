package com.sahil.security.firstapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class MySecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.formLogin();
        // this line make login to form based login

        http.httpBasic();

        //SecurityFilterChain is an interface

        //used to customize the security for application

        //defaults will be overridden with this SecurityFilterChain Bean

        http.httpBasic(Customizer.withDefaults());

//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/hello").authenticated().anyRequest());

//        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/hello").authenticated().anyRequest()
//                .denyAll());

        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/hello").authenticated());

        http.addFilterBefore(new MySecurityFilter(), BasicAuthenticationFilter.class);




        return http.build();
        //default security chain

    }

    //HttpSecurity has several methods

    //Configure User Details Service

//    @Bean
//    UserDetailsService userDetailsService() {
//
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//        //InMemoryUserDetailsManager is the impl of UserDetailsService Interface
//
//        UserDetails user =User.withUsername("tom").password(passwordEncoder().encode("cruise"))
//                .authorities("read").build();
//
//        userDetailsService.createUser(user);
//
//        return userDetailsService;
//    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
