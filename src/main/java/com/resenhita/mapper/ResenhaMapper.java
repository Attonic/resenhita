package com.resenhita.mapper;

import com.resenhita.dto.ResenhaDto;
import com.resenhita.model.entity.Resenha;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResenhaMapper {

    Resenha deDtoParaEntidade(ResenhaDto resenhaDto);

    ResenhaDto deEntidadeParaDto(Resenha resenha);
}
