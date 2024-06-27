package com.aluracursos.literalura.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorDTO(
        @JsonAlias("name") String name,
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear


) {
    @Override
    public String toString() {
        return
                "---------Author---------------" + "\n" +
                "Name='" + name + '\'' + "\n" +
                "Birth Year=" + birthYear + "\n" +
                "Death Year=" + deathYear +  "\n";
    }
}
