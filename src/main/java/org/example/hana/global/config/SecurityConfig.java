package org.example.hana.global.config;

import lombok.RequiredArgsConstructor;
import org.example.hana.global.jwt.JwtFilter;
import org.example.hana.global.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())  // 기본 로그인 창 비활성화
                .cors(configurer -> configurer.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안함 (JWT 기반 인증 시 필요)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**", "/ws/websocket").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/recruitment-posts").permitAll() // GET 요청만 허용
                        .requestMatchers(HttpMethod.GET, "/api/recruitment-posts/**").permitAll()
                        .anyRequest().authenticated()
                ).addFilterBefore(new JwtFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addExposedHeader("*");
        corsConfiguration.setAllowCredentials(true);

//      corsConfiguration.setAllowedOrigins(List.of("https://ide-frontend-wheat.vercel.app/login", "https://ide-frontend-six.vercel.app", "https://ide-frontend-wheat.vercel.app"));
        corsConfiguration.setAllowedOrigins(List.of("https://ide-frontend-wheat.vercel.app/login", "krmp-d2hub-idock.9rum.cc/dev-test/repo_85a78215dc68", "https://ide-frontend-six.vercel.app", "https://ide-frontend-wheat.vercel.app", "http://localhost:3000", "https://k547f55f71a44a.user-app.krampoline.com", "http://localhost:5173"));

        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
