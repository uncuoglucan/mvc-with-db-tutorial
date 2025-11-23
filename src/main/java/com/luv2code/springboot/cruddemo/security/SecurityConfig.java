package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails Luffy = User.builder()
                .username("Luffy")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails Kuma = User.builder()
                .username("Kuma")
                .password("{noop}test123")
                .roles("EMPLOYEE","ADMIN")
                .build();

        UserDetails Roger = User.builder()
                .username("Roger")
                .password("{noop}test123")
                .roles("EMPLOYEE","ADMIN","MANAGER")
                .build();

        // Define users, roles, and authorities here
        return new InMemoryUserDetailsManager(Luffy, Kuma, Roger);
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer.anyRequest().authenticated())
                            .formLogin(form ->
                                form.loginPage("/modernkole/loginPage")
                                    .loginProcessingUrl("/modernkole/authenticateTheUser")
                                    .permitAll()
                );

        return http.build();
    }
}
