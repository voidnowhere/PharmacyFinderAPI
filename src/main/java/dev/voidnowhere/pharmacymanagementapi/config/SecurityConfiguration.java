package dev.voidnowhere.pharmacymanagementapi.config;

import dev.voidnowhere.pharmacymanagementapi.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    @Value("${application.security.allowed-origin}")
    private String allowedOrigin;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "api/v1/auth/register").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/auth/authenticate").permitAll()
                .requestMatchers(HttpMethod.GET, "api/v1/cities").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/cities").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.GET, "api/v1/cities/{id}").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "api/v1/cities").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "api/v1/cities/{id}").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.GET, "api/v1/cities/{id}/zones").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/cities/{id}/zones").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.GET, "api/v1/zones/{id}").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.PUT, "api/v1/zones").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.DELETE, "api/v1/zones/{id}").hasAuthority(UserRole.ADMIN.name())
                .requestMatchers(HttpMethod.GET, "api/v1/zones/{id}/pharmacies").permitAll()
                .requestMatchers(HttpMethod.POST, "api/v1/zones/{id}/pharmacies/closest").permitAll()
                .requestMatchers(HttpMethod.GET, "api/v1/pharmacies/my").hasAuthority(UserRole.PHARMACIST.name())
                .requestMatchers(HttpMethod.POST, "api/v1/zones/{id}/pharmacies").hasAuthority(UserRole.PHARMACIST.name())
                .requestMatchers(HttpMethod.PUT, "api/v1/pharmacies").hasAuthority(UserRole.PHARMACIST.name())
                .requestMatchers(HttpMethod.GET, "api/v1/pharmacies/{id}/weekdays").hasAuthority(UserRole.PHARMACIST.name())
                .requestMatchers(HttpMethod.PUT, "api/v1/pharmacy_weekdays/{id}").hasAuthority(UserRole.PHARMACIST.name())
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigin));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}