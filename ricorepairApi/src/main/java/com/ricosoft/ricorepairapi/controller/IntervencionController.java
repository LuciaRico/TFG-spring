package com.ricosoft.ricorepairapi.controller;

import com.ricosoft.ricorepairapi.dto.IntervencionDTO;
import com.ricosoft.ricorepairapi.entity.Intervencion;
import com.ricosoft.ricorepairapi.service.IntervencionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/historial")
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class IntervencionController {

    private final IntervencionService intervencionService;

    // Listar intervenciones
    @GetMapping
    public Iterable<Intervencion> list() {
        return intervencionService.findAll();
    }

    // Listar intervencion por ID
    @GetMapping("{id}")
    public Intervencion get(@PathVariable Integer id) {
        return intervencionService.findById(id);
    }

    // Listar intervenciones por parteId
    @GetMapping("/partes/{parteId}")
    public List<Intervencion> getIntervencionesByParteId(@PathVariable Integer parteId) {
        return intervencionService.findByParteId(parteId);
    }

    // Crear intervencion
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Intervencion create(@Validated @RequestBody IntervencionDTO intervencionDTO) {
        return intervencionService.create(intervencionDTO);
    }

    // Actualizar intervencion
    @PutMapping("{id}")
    public Intervencion update(@PathVariable Integer id,
                               @Validated @RequestBody IntervencionDTO intervencionDTO) {
        return intervencionService.update(id, intervencionDTO);
    }

    // Borrar intervencion
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        intervencionService.delete(id);
    }


}
