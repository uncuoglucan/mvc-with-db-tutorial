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
                .username("luffy")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails Kuma = User.builder()
                .username("a")
                .password("{noop}a")
                .roles("EMPLOYEE","BADANACI")
                .build();

        UserDetails Roger = User.builder()
                .username("roger")
                .password("{noop}test123")
                .roles("EMPLOYEE","ADMIN","BADANACI")
                .build();

        // Define users, roles, and authorities here
        return new InMemoryUserDetailsManager(Luffy, Kuma, Roger);
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer.requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("BADANACI")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                                    .formLogin(form ->
                                        form.loginPage("/loginPage")
                                            .loginProcessingUrl("/authenticateTheUser")
                                            .permitAll()
                )
                .logout(logout ->
                        logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/accessDenied"));

        return http.build();
    }
}
