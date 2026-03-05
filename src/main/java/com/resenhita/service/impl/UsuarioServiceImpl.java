package com.resenhita.service.impl;

import com.resenhita.dto.UsuarioDto;
import com.resenhita.mapper.UsuarioMapper;
import com.resenhita.model.entity.Usuario;
import com.resenhita.repository.UsuarioRepository;
import com.resenhita.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDto buscarPorId(UUID id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return usuarioMapper.deEntidadeParaDto(usuario);
    }

    @Override
    public Page<UsuarioDto> buscarTodos(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(usuarioMapper::deEntidadeParaDto);
    }

    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {

        if (usuarioRepository.existsByEmail(usuarioDto.getEmail())){
            throw new DataIntegrityViolationException("Email já existe");
        }

        Usuario usuario = usuarioMapper.deDtoParaEntidade(usuarioDto);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return usuarioMapper.deEntidadeParaDto(usuarioSalvo);
    }

    @Override
    public UsuarioDto atualizar(UsuarioDto usuarioDto, UUID id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encotrado"));

        if (usuarioRepository.existsByEmailAndIdNot(usuarioDto.getEmail(), id)){
            throw new DataIntegrityViolationException("Email já existe");
        }

        usuarioMapper.atualizarEntidadeDoDto(usuarioDto, usuario);
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return usuarioMapper.deEntidadeParaDto(usuarioAtualizado);
    }
}
