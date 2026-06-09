package com.CommerceFinder_API.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desativa o CSRF (necessário para conseguir fazer requisições POST no Postman sem token)
                .csrf(AbstractHttpConfigurer::disable)

                // Define quem pode acessar o quê
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Libera totalmente o banco H2
                        .requestMatchers("/estabelecimentos/**").permitAll() // Libera o seu CRUD
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .anyRequest().permitAll() // Libera qualquer outra rota por enquanto
                )

                // Correção crucial para o console do H2 conseguir renderizar os frames na tela
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

}
