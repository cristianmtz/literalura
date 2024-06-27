package com.aluracursos.literalura.repository;


import com.aluracursos.literalura.DTO.AuthorDTO;
import com.aluracursos.literalura.DTO.BookDTO;
import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author,Long> {



    Author findByName(String name);


    Author findByNameIgnoreCaseContaining(String name);

    List<Author> findByBirthYearGreaterThanEqualAndDeathYearLessThanEqual(Integer birthYear, Integer deathYear);

}

