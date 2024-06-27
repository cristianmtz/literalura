package com.aluracursos.literalura.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexResponseDTO(
        @JsonAlias("results")  List<BookDTO> results){

}
