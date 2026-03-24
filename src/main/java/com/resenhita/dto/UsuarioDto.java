package com.resenhita.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDto {

    public interface UsuarioView {
        public static interface UsuarioPut{}
        public static interface UsuarioPost{}
    }

    @JsonView({UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class})
    @NotBlank(groups = {UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class},
    message = "Nome não pode ser vazio.")
    private String nome;

    @JsonView({UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class})
    private String biografia;

    @JsonView({UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class})
    private String linkSocial1;

    @JsonView({UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class})
    private String linkSocial2;

    @JsonView({UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class})
    private String linkSocial3;

    @JsonView({UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class})
    @NotBlank(groups = {UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class},
            message = "Email não pode ser vazio.")
    private String email;

    @JsonView({UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class})
    @NotBlank(groups = {UsuarioView.UsuarioPost.class, UsuarioView.UsuarioPut.class},
            message = "Senha não pode ser vazio.")
    private String senha;
}
