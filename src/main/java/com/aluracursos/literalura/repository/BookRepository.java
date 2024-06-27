package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {



    Optional<Book> findByTitle(String title);


    //Utilizando native query, permite buscar en la base de datos y ordenar los libros basado en el numero de descargas
    @Query(value = "SELECT * FROM book ORDER BY download_count DESC LIMIT 5", nativeQuery = true)
    List<Book> top5Books();


    @Query(value = "SELECT * FROM book WHERE :language = ANY(languages)", nativeQuery = true)
    List<Book> findBooksByLanguage(@Param("language") String language);


}
