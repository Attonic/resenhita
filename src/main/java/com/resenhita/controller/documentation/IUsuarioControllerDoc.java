package com.resenhita.controller.documentation;

import com.resenhita.dto.UsuarioDto;
import com.resenhita.exception.ConflitoException;
import com.resenhita.exception.RecursoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Usuários", description = "Controla o cadastro edição e busca de usuários")
public interface IUsuarioControllerDoc {

    @Operation(summary = "Retorna todos usuários cadastrados", description = "Busca todos os usuários existentes.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso.",
                content = @Content()
            )
        }
    )
    public ResponseEntity<Page<UsuarioDto>> buscarTodos(Pageable pageable);

    @Operation(summary = "Retorna um usuário por ID", description = "Busca um usuário pelo seu Id.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecursoEncontradoException.class))
            )
        }
    )
    public ResponseEntity<UsuarioDto> buscarPorId(UUID id);

    @Operation(summary = "Persiste um usuário.", description = "Cadastra um usuário no banco.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.", content = @Content()),
            @ApiResponse(responseCode = "409", description = "Usuário já existente.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConflitoException.class))
            )
        }
    )
    public ResponseEntity<UsuarioDto> cadastrar(UsuarioDto usuarioDto);

    @Operation(summary = "Altera um usuário pelo seu Id.", description = "Faz a Alteração de um usuário pelo seu Id.",
        responses = {
            @ApiResponse(responseCode = "202", description = "Alteração feita com sucesso.", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado para o Id",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RecursoEncontradoException.class))
            )
        }
    )
    public ResponseEntity<UsuarioDto> atualizar(UUID id, UsuarioDto usuarioDto);
}
