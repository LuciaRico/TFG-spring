package com.ricosoft.ricorepairapi.service;

import com.ricosoft.ricorepairapi.dto.ParteDTO;
import com.ricosoft.ricorepairapi.entity.Parte;
import com.ricosoft.ricorepairapi.entity.Usuario;
import com.ricosoft.ricorepairapi.exception.ResourceNotFoundException;
import com.ricosoft.ricorepairapi.repository.ParteRepository;
import com.ricosoft.ricorepairapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ParteService {

    private final ParteRepository parteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper mapper;

    // Listar partes
    public Iterable<Parte> findAll() {
        return parteRepository.findAll();
    }

    // Buscar parte por su ID
    public Parte findById(Integer id) {
        return parteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parte con id "+ id +" no encontrado"));
    }

    // Listar partes por usuarioId
    public List<Parte> findByUsuarioId(Integer usuarioId) {
    	// Verificar que el usuario existe
        usuarioRepository.findById(usuarioId)
        		.orElseThrow(() -> new ResourceNotFoundException("Usuario con id "+ usuarioId +" no encontrado"));

        // Devuelve lista de partes (aunque la lista esté vacía)
        return parteRepository.findByUsuario_Id(usuarioId);
        
        // No se lanza una excepción si la lista está vacía para evitar el error 404
    }

    // Crear parte
    public Parte create(ParteDTO parteDTO) {

        // Buscar el objeto Usuario por su ID
        Usuario usuario = usuarioRepository.findById(parteDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con id " + parteDTO.getUsuarioId() + " no encontrado"));

        Parte parte = mapper.map(parteDTO, Parte.class);

        // Establecer la entidad Usuario en el parte
        parte.setUsuario(usuario);

        // Asignar la fecha de creación manualmente
        parte.setFechaCreacion(LocalDateTime.now());

        parte.setId(null); // Asegura que el id esté vacío

        // Verificación manual del campo informeEmpresa
        if (parteDTO.getOtrosMateriales() != null) {
            parte.setOtrosMateriales(parteDTO.getOtrosMateriales());
        } else {
            parte.setOtrosMateriales("No");
        }

        // Verificación manual del campo informeEmpresa
        if (parteDTO.getInformeEmpresa() != null) {
            parte.setInformeEmpresa(parteDTO.getInformeEmpresa());
        } else {
            parte.setInformeEmpresa("Informe pendiente");
        }
        return parteRepository.save(parte);
    }

    // Actualizar parte
    public Parte update(Integer id, ParteDTO parteDTO) {
        Parte parteFromDb = findById(id);

        // Buscar el objeto Usuario por su ID
        Usuario usuario = usuarioRepository.findById(parteDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con id " + parteDTO.getUsuarioId() + " no encontrado"));

        // Configuración del mapper para evitar que sobreescriba campos críticos
        mapper.typeMap(ParteDTO.class, Parte.class).addMappings(mapper -> {
            mapper.skip(Parte::setId);  // No sobrescribas el id

        });

        // Mapear los campos del DTO al entity Parte
        mapper.map(parteDTO, parteFromDb);

        // Establecer la entidad Usuario en el parte
        parteFromDb.setUsuario(usuario);

        // Asignar la fecha de creación manualmente
        parteFromDb.setFechaCreacion(LocalDateTime.now());

        return parteRepository.save(parteFromDb);
    }

    // Borrar parte
    public void delete(Integer id) {
        Parte parteFromDb = findById(id);
        parteRepository.delete(parteFromDb);
    }
}
