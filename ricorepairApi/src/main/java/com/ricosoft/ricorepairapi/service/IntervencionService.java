package com.ricosoft.ricorepairapi.service;

import com.ricosoft.ricorepairapi.dto.IntervencionDTO;
import com.ricosoft.ricorepairapi.entity.Intervencion;
import com.ricosoft.ricorepairapi.entity.Parte;
import com.ricosoft.ricorepairapi.exception.ResourceNotFoundException;
import com.ricosoft.ricorepairapi.repository.IntervencionRepository;
import com.ricosoft.ricorepairapi.repository.ParteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class IntervencionService {

    private IntervencionRepository intervencionRepository;
    private ParteRepository parteRepository;
    private final ModelMapper mapper;

    // Listar intervenciones
    public Iterable<Intervencion> findAll() {
        return intervencionRepository.findAll();
    }

    // Buscar intervencion por su ID
    public Intervencion findById(Integer id) {
        return intervencionRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Intervención con id "+id+" no encontrada")
                );
    }

    // Listar intervenciones por parteId
    public List<Intervencion> findByParteId(Integer parteId) {
        List<Intervencion> intervenciones = intervencionRepository.findByParte_Id(parteId);
        if (intervenciones.isEmpty()) {
            throw new ResourceNotFoundException("Intervenciones no encontradas"); // Lanza una excepción si no se encuentran intervenciones
        }
        return intervenciones;
    }


    // Crear intervencion
    public Intervencion create(IntervencionDTO intervencionDTO) {

        // Buscar el objeto Parte por su ID
        Parte parte = parteRepository.findById(intervencionDTO.getParteId())
                .orElseThrow(() -> new ResourceNotFoundException("Parte con id " + intervencionDTO.getParteId() + " no encontrado"));

        Intervencion intervencion = mapper.map(intervencionDTO, Intervencion.class);

        // Establecer la entidad Parte en la intervención
        intervencion.setParte(parte);

        intervencion.setId(null); // Asegura que el id esté vacío

        // Verificación manual del campo descripcion
        if (intervencionDTO.getDescripcion() != null) {
            intervencion.setDescripcion(intervencionDTO.getDescripcion());
        } else {
            intervencion.setDescripcion("Sin descripcion");
        }

        return intervencionRepository.save(intervencion);
    }

    // Actualizar intervencion
    public Intervencion update(Integer id, IntervencionDTO intervencionDTO) {
        Intervencion intervencionFromDb = findById(id);

        // Buscar el objeto Parte por su ID
        Parte parte = parteRepository.findById(intervencionDTO.getParteId())
                .orElseThrow(() -> new ResourceNotFoundException("Parte con id " + intervencionDTO.getParteId() + " no encontrado"));

        // Mapear los demás campos del DTO al entity Intervencion
        mapper.map(intervencionDTO, intervencionFromDb);

        // Establecer la entidad Parte en la intervención
        intervencionFromDb.setParte(parte);

        return intervencionRepository.save(intervencionFromDb);
    }

    // Borrar intervencion
    public void delete(Integer id) {
        Intervencion intervencionFromDb = findById(id);
        intervencionRepository.delete(intervencionFromDb);
    }


}
