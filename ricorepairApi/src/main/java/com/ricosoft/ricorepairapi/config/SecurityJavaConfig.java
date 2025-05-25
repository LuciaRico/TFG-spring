package com.ricosoft.ricorepairapi.config;

import com.ricosoft.ricorepairapi.entity.Rol;
import com.ricosoft.ricorepairapi.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityJavaConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
                // Autorizaciones de endpoints
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers(HttpMethod.GET).permitAll()
                                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                                .requestMatchers("api/auth/**").permitAll() // Ruta pública
                                .anyRequest().authenticated() // Todas las demás requieren autenticación
                )
                // Configuración de sesión (JSESSIONID cookie) (No las utiliza)
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //No creo cookies de sesión
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));  // Agregar configuración de CORS

        return http.build();
    }

    /**
     * Configuración de CORS.
     *
     * @return configuración cors.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration  = new CorsConfiguration();

        // Permitir credenciales (como cookies) en solicitudes cross-origin
        corsConfiguration .setAllowCredentials(true);

        // Permitir orígenes específicos (localhost:4200)
        corsConfiguration .addAllowedOriginPattern("http://localhost:4200"); // Solo permitir solicitudes mi frontend en desarrollo
        corsConfiguration .addAllowedHeader("*"); // Permitir todos los encabezados
        corsConfiguration .addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //Aplica la configuración CORS a todas las rutas (/**)
        source.registerCorsConfiguration("/**", corsConfiguration ); // Configurar CORS para todas las rutas

        return source;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    //Enviar cookies en solicitudes del mismo origen y en algunas situaciones de origen cruzado.
    // Como cuando un usuario hace clic en un enlace hacia tu aplicación.
    //private final String csrfSameSiteCookie = "Lax";
    //Asegura que las cookies solo se envíen a través de conexiones HTTPS
    //private final boolean csrfSecureCookie = true;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // Configuramos el Csrf Token repository para tener una cookie cross-domain
//        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
//        tokenRepository.setCookieCustomizer(cookie -> {
//            cookie.sameSite(csrfSameSiteCookie); // Define SameSite como "Lax"
//            cookie.secure(csrfSecureCookie); // Marca la cookie como segura
//            cookie.httpOnly(false); // Para que JS pueda leer la cookie
//        });
//
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configuración de CORS
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(tokenRepository) // Configuración del CSRF
//                )
//                // Configuración de sesión (JSESSIONID cookie)
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                )
//                // Autorizaciones de endpoints
//                .authorizeHttpRequests(authRequest -> authRequest
//                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permitir OPTIONS
//                        .requestMatchers(HttpMethod.GET, "/actuator/health").permitAll() // Permitir el health-check
//                        .requestMatchers("/api/users/**").hasAuthority(Rol.USER.name()) // Rutas protegidas por roles
//                        .requestMatchers("/api/admin/**").hasAuthority(Rol.ADMIN.name()) // Rutas protegidas por roles
//                        .anyRequest().authenticated() // Todas las demás requieren autenticación
//                )
//                .httpBasic(withDefaults()); // Autenticación HTTP básica
//
//        return http.build();
//    }



}
