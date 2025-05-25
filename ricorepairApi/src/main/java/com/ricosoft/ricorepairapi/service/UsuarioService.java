package com.ricosoft.ricorepairapi.service;


import com.ricosoft.ricorepairapi.dto.UsuarioDTO;
import com.ricosoft.ricorepairapi.entity.Rol;
import com.ricosoft.ricorepairapi.entity.Usuario;
import com.ricosoft.ricorepairapi.exception.ResourceNotFoundException;
import com.ricosoft.ricorepairapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    //Listar usuarios
    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    //Listar usuario por id
    public Usuario findById(Integer id) {
        return  usuarioRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Usuario con id "+ id +" no encontrado"));
    }

    //Crear usuario
    public Usuario create(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);

        // Encriptamos la contraseña antes de guardarla
        String passwordEncriptada = passwordEncoder.encode(usuarioDTO.getPassword());
        usuario.setPassword(passwordEncriptada); // Asignamos la contraseña encriptada

        usuario.setRol(Rol.USER);

        return  usuarioRepository.save(usuario);
    }

    //Actualizar usuario
    public Usuario update(Integer id, UsuarioDTO usuarioDTO) {
        Usuario usuarioFromDb = findById(id);
        // Si se quiere actualizar la contraseña, la encriptamos
        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            String passwordEncriptada = passwordEncoder.encode(usuarioDTO.getPassword());
            usuarioFromDb.setPassword(passwordEncriptada);
        }
        // Mapear otros campos del DTO al usuario
        if (usuarioDTO.getName() != null) {
            usuarioFromDb.setName(usuarioDTO.getName());
        }
        if (usuarioDTO.getSurname() != null) {
            usuarioFromDb.setSurname(usuarioDTO.getSurname());
        }
        if (usuarioDTO.getEmail() != null) {
            usuarioFromDb.setEmail(usuarioDTO.getEmail());
        }
        if (usuarioDTO.getTlf() != null) {
            usuarioFromDb.setTlf(usuarioDTO.getTlf());
        }

        return  usuarioRepository.save(usuarioFromDb);
    }

    //Borrar usuario
    public void delete(Integer id) {
        Usuario usuarioFromDb = findById(id);
        usuarioRepository.delete(usuarioFromDb);
    }
}
