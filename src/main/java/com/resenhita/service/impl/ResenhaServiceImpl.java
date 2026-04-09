package com.resenhita.service.impl;

import com.resenhita.dto.ResenhaDto;
import com.resenhita.mapper.ResenhaMapper;
import com.resenhita.model.entity.Resenha;
import com.resenhita.repository.ResenhaRepository;
import com.resenhita.repository.UsuarioRepository;
import com.resenhita.service.ResenhaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResenhaServiceImpl implements ResenhaService {

    private final UsuarioRepository usuarioRepository;
    private final ResenhaRepository resenhaRepository;
    private final ResenhaMapper resenhaMapper;

    @Override
    public ResenhaDto saveResenha(ResenhaDto resenhaDto) {
        var usuario = usuarioRepository.findById(resenhaDto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        YearMonth mesAtual =  YearMonth.now();
        LocalDateTime inicioDoMes = mesAtual.atDay(1).atStartOfDay();
        LocalDateTime fimDoMes = mesAtual.atEndOfMonth().atTime(LocalTime.MAX);

        long limiteTotal = resenhaRepository.limiteDeResenhaPorUsuario(
                resenhaDto.getIdUsuario(),
                inicioDoMes,
                fimDoMes
        );

        if (limiteTotal >= 15) {
            throw new IllegalArgumentException("Você atingiu o limite máximo de 15 resenhas na sua conta.");
        }

        var resenha = resenhaMapper.deDtoParaEntidade(resenhaDto);

        resenha.setUsuario(usuario);

        var resenhaEntidadeSave = resenhaRepository.save(resenha);

        return resenhaMapper.deEntidadeParaDto(resenhaEntidadeSave);
    }

    @Override
    public Page<ResenhaDto> findAllResenhas(Pageable pageable) {
        return resenhaRepository.findAll(pageable)
                .map(resenhaMapper::deEntidadeParaDto);
    }

    @Override
    public ResenhaDto updateResenha(ResenhaDto resenhaDto, UUID id) {
        Resenha resenha = resenhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não encontrada."));

        resenhaMapper.atualizarEntidadeDoDto(resenhaDto, resenha);
        Resenha resenhaAtualizado = resenhaRepository.save(resenha);
        return resenhaMapper.deEntidadeParaDto(resenhaAtualizado);
    }

    @Override
    public ResenhaDto findResenhaById(UUID id) {
        var resenha = resenhaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não encontrada."));
        return resenhaMapper.deEntidadeParaDto(resenha);
    }
}
