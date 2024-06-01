package com.kimseungjin.block_file_extensions.global.security;


import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {"/register"};
    private final UserDetailsLoadService userDetailsLoadService;

    @Bean
    public SecurityFilterChain configure(final HttpSecurity http) throws Exception {
        return http.httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(formLogin -> formLogin.loginPage("/login").permitAll())
                .logout(
                        logout ->
                                logout.logoutUrl("/logout").logoutSuccessUrl("/login").permitAll())
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(AUTH_WHITELIST)
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated())
                .userDetailsService(userDetailsLoadService)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
