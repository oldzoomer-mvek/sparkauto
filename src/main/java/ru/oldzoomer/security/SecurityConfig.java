package ru.oldzoomer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    UserDetailsService users() {
        return new InMemoryUserDetailsManager(
            new User("admin", "{noop}admin",
            List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/frontend/**",
                                "/VAADIN/**",
                                "/*.ico",
                                "/*.png",
                                "/*.jpg",
                                "/*.css",
                                "/*.js",
                                "/error",
                                "/public/**"
                        ).permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
