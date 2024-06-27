package com.aluracursos.literalura.mappers;

import com.aluracursos.literalura.DTO.BookDTO;
import com.aluracursos.literalura.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper mapper = Mappers.getMapper(BookMapper.class);

    Book bookDtoToBook(BookDTO bookDTOS);

    BookDTO bookToBookDto(Book book);




}
