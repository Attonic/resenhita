package com.resenhita.mapper;

import com.resenhita.dto.UsuarioDto;
import com.resenhita.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario deDtoParaEntidade(UsuarioDto usuarioDto);

    UsuarioDto deEntidadeParaDto(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    void atualizarEntidadeDoDto(UsuarioDto usuarioDto, @MappingTarget Usuario usuario);

}
