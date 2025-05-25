package com.ricosoft.ricorepairapi.repository;

import com.ricosoft.ricorepairapi.entity.Intervencion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervencionRepository extends CrudRepository<Intervencion, Integer> {
    // Método para encontrar intervenciones por parteId
    List<Intervencion> findByParte_Id(Integer parteId);
}
