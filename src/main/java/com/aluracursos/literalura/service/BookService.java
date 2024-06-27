package com.aluracursos.literalura.service;


import com.aluracursos.literalura.DTO.AuthorDTO;
import com.aluracursos.literalura.DTO.BookDTO;
import com.aluracursos.literalura.mappers.AuthorMapper;
import com.aluracursos.literalura.mappers.BookMapper;
import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.models.Book;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;


    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    /*
    Allows saving books to the database,
    checking if the author already exists to avoid duplication.
    */
    @Transactional
    public void saveAllBooks(List<BookDTO> bookDTOS) {


        List<Book> books = bookDTOS.stream()
                .map(BookMapper.mapper::bookDtoToBook)
                .toList();


        for( Book book: books ){
            List<Author> authors = new ArrayList<>();
            for(Author author: book.getAuthors()){
                String authorsName = author.getName();
                Author isAlreadyExist = authorRepository.findByName(authorsName);
                authors.add(Objects.requireNonNullElse(isAlreadyExist, author));
            }
            book.setAuthors(authors);
            bookRepository.save(book);
        }

    }


    @Transactional
    public List<BookDTO> top5Books(){

        //Gets the 5 most downloaded books
        List<Book> books = bookRepository.top5Books();

        return books.stream().map(BookMapper.mapper::bookToBookDto).toList();
    }


    //Receives two parameters and, based on the range, searches for authors alive during those dates.
    @Transactional
    public List<AuthorDTO> findAuthorsAliveDuringPeriod(Integer birthYear, Integer deathYear){

        List<Author> authors = authorRepository.findByBirthYearGreaterThanEqualAndDeathYearLessThanEqual( birthYear, deathYear);

        if(authors.isEmpty()){
            throw new RuntimeException("No authors found during the period  " + birthYear + "-" + deathYear);
        }

        return authors.stream().map(AuthorMapper.mapper::authorToAuthorDto).toList();
    }

    //Allows searching by language
    @Transactional
    public List<BookDTO> findBooksByLanguage(String language) {

        List<Book> books = bookRepository.findBooksByLanguage(language);

        if (books.isEmpty()){
            throw new RuntimeException("No books found for the language: " + language);
        }

        return books.stream().map(BookMapper.mapper::bookToBookDto).toList();

    }


    //Retrieve all the books stored in the database
    @Transactional
    public List<BookDTO> getAllBooks(){
        List<Book> books = bookRepository.findAll();

        return books.stream().map(BookMapper.mapper::bookToBookDto).toList();
    }



    //Retrieve all the authors stored in the database
    @Transactional
    public List<AuthorDTO> getAllAuthors(){

         List<Author> authors = authorRepository.findAll();
         return authors.stream().map(AuthorMapper.mapper::authorToAuthorDto).toList();
    }


    //Allows searching by author name.
    @Transactional
    public AuthorDTO findAuthorByName(String name){

        Author author = authorRepository.findByNameIgnoreCaseContaining(name);

        if (author == null){
            throw new RuntimeException("No author found with the name: " + name);
        }

        return AuthorMapper.mapper.authorToAuthorDto(author);
    }

}