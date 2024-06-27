package com.aluracursos.literalura.controller;
import com.aluracursos.literalura.DTO.AuthorDTO;
import com.aluracursos.literalura.DTO.BookDTO;
import com.aluracursos.literalura.mappers.BookMapper;
import com.aluracursos.literalura.models.Book;
import com.aluracursos.literalura.service.ApiService;
import com.aluracursos.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController

public class GutendexController {

    private final ApiService apiService;


    private final BookService bookService;


    public GutendexController(ApiService apiService, BookService bookService) {
        this.apiService = apiService;
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public List<BookDTO> fetchBooksByTitle(@RequestParam String title){
        return apiService.fetchBooksByTitleOrAuthor(title);
    }



    //Lista el top 5 de libros, basado en el numero de descargas
    @GetMapping("/top5")
    public List<BookDTO> top5Books(){
        return bookService.top5Books();
    }



    //Permite buscar autores durante un periodo determinado
    @GetMapping("/alive-during-period")
    public List<AuthorDTO> listAuthorsAliveDuringPeriod(@RequestParam Integer start, @RequestParam Integer end){
        return bookService.findAuthorsAliveDuringPeriod(start, end);
    }


    @GetMapping("/by-language")
    public List<BookDTO> listBooksByLanguage(@RequestParam("language") String language) {
        return bookService.findBooksByLanguage(language);

    }
}
