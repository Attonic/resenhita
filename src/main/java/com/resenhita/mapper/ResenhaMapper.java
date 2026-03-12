package com.resenhita.mapper;

import com.resenhita.dto.ResenhaDto;
import com.resenhita.model.entity.Resenha;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ResenhaMapper {

    Resenha deDtoParaEntidade(ResenhaDto resenhaDto);

    ResenhaDto deEntidadeParaDto(Resenha resenha);

    @Mapping(target = "id", ignore = true)
    void atualizarEntidadeDoDto(ResenhaDto resenhaDto, @MappingTarget Resenha resenha);
}
