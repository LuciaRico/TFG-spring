package com.ricosoft.ricorepairapi.repository;

import com.ricosoft.ricorepairapi.entity.Parte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParteRepository extends CrudRepository<Parte, Integer> {
    // Accede al campo id dentro del objeto usuario en la entidad Parte.
    List<Parte> findByUsuario_Id(Integer usuarioId);
}
