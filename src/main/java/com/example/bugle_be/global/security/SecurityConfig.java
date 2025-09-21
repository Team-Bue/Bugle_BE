package com.example.bugle_be.global.security;

import com.example.bugle_be.global.error.GlobalExceptionFilter;
import com.example.bugle_be.global.security.jwt.JwtFilter;
import com.example.bugle_be.global.security.jwt.JwtTokenProvider;
import com.example.bugle_be.infra.oauth.handler.Oauth2FailureHandler;
import com.example.bugle_be.infra.oauth.handler.Oauth2SuccessHandler;
import com.example.bugle_be.infra.oauth.service.CustomOauth2UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(
        HttpSecurity http, CustomOauth2UserService customUserService,
        Oauth2SuccessHandler successHandler, Oauth2FailureHandler failureHandler
    ) throws Exception {
        return http
            .csrf(CsrfConfigurer::disable)
            .cors(CorsConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .sessionManagement(configurer -> configurer
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userinfo -> userinfo.userService(customUserService))
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .redirectionEndpoint(
                    endpoint -> endpoint
                        .baseUri("/bugle/oauth2/{registrationId}")
                )
            )
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                // auth
                .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/reissue").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/auth/password").permitAll()

                // mail
                .requestMatchers(HttpMethod.POST, "/mail/send").permitAll()
                .requestMatchers(HttpMethod.POST, "/mail/verify").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new GlobalExceptionFilter(objectMapper), JwtFilter.class)
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
