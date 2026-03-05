package com.resenhita.service;

import com.resenhita.dto.UsuarioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UsuarioService {

    UsuarioDto buscarPorId(UUID id);

    Page<UsuarioDto> buscarTodos(Pageable pageable);

    UsuarioDto salvar(UsuarioDto usuarioDto);

    UsuarioDto atualizar(UsuarioDto usuarioDto, UUID id);

}
