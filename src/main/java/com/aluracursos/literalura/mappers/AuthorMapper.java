package com.aluracursos.literalura.mappers;


import com.aluracursos.literalura.DTO.AuthorDTO;
import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper mapper = Mappers.getMapper(AuthorMapper.class);

    Author authorDtoToAuthor(AuthorDTO authorDTO);

    AuthorDTO authorToAuthorDto(Author author);

}
