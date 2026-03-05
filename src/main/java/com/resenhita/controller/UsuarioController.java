package com.resenhita.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.resenhita.dto.UsuarioDto;
import com.resenhita.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> buscarTodos(
            @PageableDefault(size = 10, sort = "nome")
            Pageable pageable
    ){
        var usuarios = usuarioService.buscarTodos(pageable);
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable() UUID id){
        var usuario = usuarioService.buscarPorId(id);
        return  ResponseEntity.ok().body(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(
            @RequestBody
            @Validated(UsuarioDto.UsuarioView.UsuarioPost.class)
            @JsonView(UsuarioDto.UsuarioView.UsuarioPost.class)
            UsuarioDto usuarioDto
    ){

        var usuario = usuarioService.salvar(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> atualizar(
            @PathVariable() UUID id,
            @RequestBody
            @Validated(UsuarioDto.UsuarioView.UsuarioPut.class)
            @JsonView(UsuarioDto.UsuarioView.UsuarioPut.class)
            UsuarioDto usuarioDto
    ){
        var usuario = usuarioService.atualizar(usuarioDto, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
    }
}
