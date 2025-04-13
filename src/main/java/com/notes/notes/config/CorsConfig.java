package com.notes.notes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permite solicitudes desde el frontend en Vercel
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://frontend-notes-1vkkc57c5-cspo6s-projects.vercel.app");
        config.addAllowedOrigin("http://localhost:5173");

        // Permite todos los métodos HTTP
        config.addAllowedMethod("*");

        // Permite todos los headers
        config.addAllowedHeader("*");

        // Aplica la configuración a todas las rutas
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}