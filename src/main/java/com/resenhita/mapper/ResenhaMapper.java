package com.resenhita.mapper;

import com.resenhita.dto.ResenhaDto;
import com.resenhita.model.entity.Resenha;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class})
public interface ResenhaMapper {

    Resenha deDtoParaEntidade(ResenhaDto resenhaDto);

    @Mapping(source = "usuario.id", target = "idUsuario")
    ResenhaDto deEntidadeParaDto(Resenha resenha);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    void atualizarEntidadeDoDto(ResenhaDto resenhaDto, @MappingTarget Resenha resenha);
}
