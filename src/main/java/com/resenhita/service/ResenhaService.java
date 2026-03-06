package com.resenhita.service;

import com.resenhita.dto.ResenhaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResenhaService {

    ResenhaDto saveResenha(ResenhaDto resenhaDto);

    Page<ResenhaDto> findAllResenhas(Pageable pageable);
}
