package com.ricosoft.ricorepairapi.service;

import com.ricosoft.ricorepairapi.controller.AuthController;
import com.ricosoft.ricorepairapi.entity.AuthResponse;
import com.ricosoft.ricorepairapi.entity.Usuario;
import com.ricosoft.ricorepairapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthController.LoginRequest loginRequest) {
        // Autenticación del usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        // Obtener al usuario desde la base de datos
        Usuario user = usuarioRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        // Generar el token JWT
        String token = jwtService.getToken(user);

        // Crear y retornar el objeto AuthResponse con los datos del usuario
        return AuthResponse.builder()
                .token(token)
                .id(user.getId())
                .rol(user.getRol())
                .build();
    }

    /**
     * Valida las credenciales proporcionadas (usuario y contraseña).
     * Si son válidas, devuelve el rol del usuario.
     * @param email    El correo electrónico del usuario
     * @param password La contraseña proporcionada
     * @return La respuesta con el rol del usuario
     */

//    public AuthResponse validaCrendeciales(String email, String password) {
//        // Buscar al usuario en la base de datos por el correo electrónico
//        Usuario usuario = usuarioRepository.findByEmail(email)
//                .orElseThrow(() -> new ResourceNotFoundException("Usuario con correo " + email + " no encontrado"));
//
//        // Comprobar si la contraseña proporcionada coincide con la almacenada (encriptada)
//        if (passwordEncoder.matches(password, usuario.getPassword())) {
//            // Crear el objeto de respuesta con el rol del usuario
//            AuthResponse authResponse = new AuthResponse();
//            authResponse.setRol(usuario.getRol()); // Asignar el rol
//            return authResponse;
//        } else {
//            // Si las credenciales no son válidas, lanzamos una excepción
//            throw new ResourceNotFoundException("Credenciales no válidas");
//        }
//    }
}
