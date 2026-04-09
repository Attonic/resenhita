package com.resenhita.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.resenhita.enums.CategoriaResenha;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResenhaDto {

    public interface ResenhaView {
        public static interface ResenhaPut{}
        public static interface ResenhaPost{}
    }

    @JsonView({ResenhaView.ResenhaPost.class, ResenhaView.ResenhaPut.class})
    private UUID id;

    @JsonView({ResenhaView.ResenhaPost.class, ResenhaView.ResenhaPut.class})
    @NotBlank(groups = {ResenhaView.ResenhaPost.class, ResenhaView.ResenhaPut.class},
            message = "O título da resenha não pode ser vazio.")
    private String tituloResenha;

    @JsonView({ResenhaView.ResenhaPost.class, ResenhaView.ResenhaPut.class})
    @NotBlank(groups = {ResenhaView.ResenhaPost.class, ResenhaView.ResenhaPut.class},
            message = "O nome da obra não pode ser vazio." )
    private String nomeObra;

    @JsonView({ResenhaView.ResenhaPost.class, ResenhaView.ResenhaPut.class})
    @NotBlank(groups = {ResenhaView.ResenhaPost.class, ResenhaView.ResenhaPut.class},
            message = "O conteúdo não pode ser vazio." )
    private String conteudo;

    @JsonView({ResenhaView.ResenhaPost.class, ResenhaView.ResenhaPut.class})
    @NotNull(groups = {ResenhaView.ResenhaPost.class, ResenhaView.ResenhaPut.class},
            message = "A categoria não pode ser nulo.")
    private CategoriaResenha categoria;

    @JsonView({ResenhaView.ResenhaPost.class})
    @NotNull(groups = {ResenhaView.ResenhaPost.class},
            message = "O ID do usuário não pode ser nulo.")
    private UUID idUsuario;

    private UsuarioDto usuario;
}
