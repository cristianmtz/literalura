package com.aluracursos.literalura.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;




@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        @JsonAlias("id") Integer id,
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<AuthorDTO> authors,
        @JsonAlias("subjects") List<String> subjects,
        @JsonAlias("bookshelves") List<String> bookshelves,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("copyright") Boolean copyright,
        @JsonAlias("download_count") Double downloadCount)
        {

                @Override
                public String toString() {
                        return
                                "-----------Book-----------------" + "\n" +
                                "Title='" + title + "\n" +
                                "Languages=" + languages + "\n" +
                                "Copyright=" + copyright +  "\n" +
                                "Download Count=" + downloadCount +"\n" ;
                }
        }
