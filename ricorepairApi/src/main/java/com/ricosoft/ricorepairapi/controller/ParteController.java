package com.ricosoft.ricorepairapi.controller;

import com.ricosoft.ricorepairapi.dto.ParteDTO;
import com.ricosoft.ricorepairapi.entity.Intervencion;
import com.ricosoft.ricorepairapi.entity.Parte;
import com.ricosoft.ricorepairapi.service.ParteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/partes")
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class ParteController {

    private final ParteService parteService;

    // Listar partes
    @GetMapping
    public Iterable<Parte> list() {
        return parteService.findAll();
    }

    // Listar parte por ID
    @GetMapping("{id}")
    public Parte get(@PathVariable Integer id) {
        return parteService.findById(id);
    }

    // Listar partes por usuarioId
    @GetMapping("/users/{usuarioId}")
    public List<Parte> getPartesByUsuarioId(@PathVariable Integer usuarioId) {
        return parteService.findByUsuarioId(usuarioId);
    }

    // Crear parte
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping	
    public Parte create(@Validated @RequestBody ParteDTO parteDTO) {
        return parteService.create(parteDTO);
    }

    // Actualizar parte
    @PutMapping("{id}")
    public Parte update(@PathVariable Integer id,
                        @Validated @RequestBody ParteDTO parteDTO) {
        return parteService.update(id, parteDTO);
    }

    // Borrar parte
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        parteService.delete(id);
    }

}
