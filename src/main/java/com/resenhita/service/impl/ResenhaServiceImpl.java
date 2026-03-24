package com.resenhita.service.impl;

import com.resenhita.dto.ResenhaDto;
import com.resenhita.mapper.ResenhaMapper;
import com.resenhita.repository.ResenhaRepository;
import com.resenhita.repository.UsuarioRepository;
import com.resenhita.service.ResenhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class ResenhaServiceImpl implements ResenhaService {

    private final UsuarioRepository usuarioRepository;
    private final ResenhaRepository resenhaRepository;
    private final ResenhaMapper resenhaMapper;

    @Override
    public ResenhaDto saveResenha(ResenhaDto resenhaDto) {
        usuarioRepository.findById(resenhaDto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        YearMonth mesAtual =  YearMonth.now();
        LocalDateTime inicioDoMes = mesAtual.atDay(1).atStartOfDay();
        LocalDateTime fimDoMes = mesAtual.atEndOfMonth().atTime(LocalTime.MAX);

        long limiteTotal = resenhaRepository.limiteDeResenhaPorUsuario(
                resenhaDto.getIdUsuario(),
                inicioDoMes,
                fimDoMes
        );

        if (limiteTotal <= 15) {
            throw new IllegalArgumentException("Você atingiu o limite máximo de 15 resenhas na sua conta.");
        }

        var resenha = resenhaMapper.deDtoParaEntidade(resenhaDto);
        var resenhaEntidadeSave = resenhaRepository.save(resenha);

        return resenhaMapper.deEntidadeParaDto(resenhaEntidadeSave);
    }

    @Override
    public Page<ResenhaDto> findAllResenhas(Pageable pageable) {
        return resenhaRepository.findAll(pageable)
                .map(resenhaMapper::deEntidadeParaDto);
    }
}
