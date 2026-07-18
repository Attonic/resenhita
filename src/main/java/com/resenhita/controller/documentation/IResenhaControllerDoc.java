package com.resenhita.controller.documentation;


import com.fasterxml.jackson.annotation.JsonView;
import com.resenhita.dto.ResenhaDto;
import com.resenhita.exception.LimiteDeReviewException;
import com.resenhita.exception.NaoAutorizadoException;
import com.resenhita.exception.RecursoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "Resenhas", description = "Controla edição controle e captura das resenhas.")
public interface IResenhaControllerDoc {

    @Operation(summary = "Persiste uma resenha no banco", description = "Salva uma resenha no banco de dados.",
          responses = {
            @ApiResponse(responseCode = "201", description = "Resenha criada com sucesso.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado, usuário não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = NaoAutorizadoException.class))),
            @ApiResponse(responseCode = "422", description = "Limete de Reviews por usuário atingido.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = LimiteDeReviewException.class)))
          }
    )
    ResponseEntity<ResenhaDto> saveResenha(ResenhaDto resenhaDto);


    @Operation(summary = "Retorna uma resenha buscando pelo ID", description = "Busca por ID uma renha especifica.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Resenha encontrada com sucesso.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resenha não encontrada para esse ID",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecursoEncontradoException.class))
            )
        }
    )
    public ResponseEntity<ResenhaDto> findResenhaById(@PathVariable UUID id);

    @Operation(summary = "Retorna uma lista paginada de todas as resenhas", description = "Busca uma lista em paginação de todas as resenhas",
        responses = {
            @ApiResponse(responseCode = "200", description = "Resenhas encontradas com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Nenhuma resenha encontrada",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecursoEncontradoException.class))
            )
        }
    )
    public ResponseEntity<Page<ResenhaDto>> findAllResenhas(@PageableDefault(size = 10) Pageable pageable);

    @Operation(summary = "Altera uma resenha por ID", description = "Faz a alteração de uma resenha por seu ID",
        responses = {
            @ApiResponse(responseCode = "202", description = "Alteração feita com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resenha não encontrada",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecursoEncontradoException.class))
            )
        }
    )
    public ResponseEntity<ResenhaDto> updateResenha( UUID id, ResenhaDto resenhaDto);

}
