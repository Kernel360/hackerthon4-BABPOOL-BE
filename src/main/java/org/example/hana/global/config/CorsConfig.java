//package org.example.hana.global.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**")
//                        .allowedOrigins("http://localhost:3000")
//                        .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS")
//                        .allowedHeaders("Authorization", "Content-Type")
//                        .exposedHeaders("Access-Control-Allow-Origin", "Origin", "Authorization", "Content-Type")
//                        .allowCredentials(true);
//            }
//        };
//    }
//}
