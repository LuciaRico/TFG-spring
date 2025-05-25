package com.ricosoft.ricorepairapi.repository;

import com.ricosoft.ricorepairapi.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    // Buscar un usuario por email
    Optional<Usuario> findByEmail(String email);
}
