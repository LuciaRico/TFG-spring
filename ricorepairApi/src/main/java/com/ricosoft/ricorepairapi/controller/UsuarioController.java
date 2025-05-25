package com.ricosoft.ricorepairapi.controller;

import com.ricosoft.ricorepairapi.dto.UsuarioDTO;
import com.ricosoft.ricorepairapi.entity.Usuario;
import com.ricosoft.ricorepairapi.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsuarioController {

    private final UsuarioService usuarioService;

    //Listar todos
    @GetMapping
    public Iterable<Usuario> list() {
        return usuarioService.findAll();
    }

    //Listar por id
    @GetMapping("{id}")
    public Usuario get(@PathVariable Integer id) {
        return  usuarioService.findById(id);
    }

    //Crear
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@Validated @RequestBody UsuarioDTO usuarioDTO) {
        return  usuarioService.create(usuarioDTO);
    }

    //Actualizar
    @PutMapping("{id}")
    public Usuario update(@PathVariable Integer id,
                          @Validated @RequestBody UsuarioDTO usuarioDTO) {
        return  usuarioService.update(id, usuarioDTO);
    }

    //Borrar
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        usuarioService.delete(id);
    }
}
