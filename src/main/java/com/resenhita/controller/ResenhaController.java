package com.resenhita.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.resenhita.dto.ResenhaDto;
import com.resenhita.service.ResenhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resenhas")
public class ResenhaController {

    private final ResenhaService resenhaService;

    @PostMapping
    public ResponseEntity<ResenhaDto> saveResenha(
            @RequestBody
            @Validated(ResenhaDto.ResenhaView.ResenhaPost.class)
            @JsonView(ResenhaDto.ResenhaView.ResenhaPost.class)
            ResenhaDto resenhaDto) {

        var resenha = resenhaService.saveResenha(resenhaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resenha);
    }

    @GetMapping
    public ResponseEntity<Page<ResenhaDto>> findAllResenhas(@PageableDefault(size = 10) Pageable pageable) {
        var resenhas = resenhaService.findAllResenhas(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(resenhas);
    }
}
