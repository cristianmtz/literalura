package com.aluracursos.literalura.service;


import com.aluracursos.literalura.DTO.BookDTO;
import com.aluracursos.literalura.DTO.GutendexResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ApiService {

    private final String baseUrl = "https://gutendex.com/books";

    private final RestTemplate restTemplate;

    private final BookService bookService;

    public ApiService(RestTemplate restTemplate, BookService bookService) {
        this.restTemplate = restTemplate;
        this.bookService = bookService;
    }

    public List<BookDTO> fetchBooksByTitleOrAuthor(String title) throws RuntimeException {
        String url = baseUrl + "?search=" + title.replace(" ", "+");
        GutendexResponseDTO responseDTO = restTemplate.getForObject(url, GutendexResponseDTO.class);



        if(responseDTO != null && responseDTO.results() != null){

            List<BookDTO> bookDTOSList = responseDTO.results().stream()
                    .map(b -> new BookDTO(b.id(), b.title(), b.authors(), b.subjects(), b.bookshelves(), b.languages(), b.copyright(), b.downloadCount()))
                    .collect(Collectors.toList());


            bookService.saveAllBooks(bookDTOSList);
            return bookDTOSList;
        }


        return null;
    }


}