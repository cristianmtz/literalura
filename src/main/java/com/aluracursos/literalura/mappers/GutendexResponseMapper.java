package com.aluracursos.literalura.mappers;


import com.aluracursos.literalura.DTO.GutendexResponseDTO;
import com.aluracursos.literalura.models.GutendexResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface GutendexResponseMapper {


    GutendexResponseMapper mapper = Mappers.getMapper(GutendexResponseMapper.class);

    GutendexResponse bookDtoToBook(GutendexResponseDTO gutendexResponseDTO);

    GutendexResponseDTO bookToBookDto(GutendexResponse gutendexResponse);
}
