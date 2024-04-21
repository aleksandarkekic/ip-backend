package com.example.ip_backend.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    private JwtAuthEntryPoint authEntryPoint;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(JwtAuthEntryPoint authEntryPoint, CustomUserDetailsService customUserDetailsService) {
        this.authEntryPoint = authEntryPoint;
        this.customUserDetailsService = customUserDetailsService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/locations/**").permitAll()
                .requestMatchers("/users/**").permitAll()
                .requestMatchers("/comments/**").hasAnyAuthority(SecurityConsts.USER, SecurityConsts.ADMIN)
                .requestMatchers(HttpMethod.GET,"/photos/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/photos/**").hasAnyAuthority(SecurityConsts.USER, SecurityConsts.ADMIN)
                .requestMatchers("/messages/**").hasAnyAuthority(SecurityConsts.USER, SecurityConsts.ADMIN)
                .requestMatchers(HttpMethod.GET,"/programs/**").permitAll()
                .requestMatchers("/programs/**").hasAnyAuthority(SecurityConsts.USER, SecurityConsts.ADMIN)
                .requestMatchers("/categories/**").permitAll()
                .requestMatchers("/attributes/**").permitAll()
                .requestMatchers("/participate/**").hasAnyAuthority(SecurityConsts.USER, SecurityConsts.ADMIN)
                .requestMatchers("/diaries/**").hasAnyAuthority(SecurityConsts.USER, SecurityConsts.ADMIN)
                .and()
                .httpBasic();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .exceptionHandling(withDefaults())
//                .sessionManagement( withDefaults())
//                .authorizeHttpRequests(
//                        (authorizeHttpRequests)-> authorizeHttpRequests.requestMatchers("/api/auth/**").permitAll())
//                .authorizeHttpRequests(
//                        (authorizeHttpRequests)-> authorizeHttpRequests.requestMatchers("/messages/**").hasAuthority("USER"))
//                .authorizeHttpRequests(
//                        (authorizeHttpRequests)-> authorizeHttpRequests.requestMatchers("/users/**").hasAuthority("USER"))
//                .httpBasic().disable();
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // kada god cuvamo password potrebno ga je cuvati enkodovanog
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}
